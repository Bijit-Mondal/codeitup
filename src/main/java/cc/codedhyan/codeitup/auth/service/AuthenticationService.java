package cc.codedhyan.codeitup.auth.service;

import cc.codedhyan.codeitup.auth.*;
import cc.codedhyan.codeitup.config.JWTService;
import cc.codedhyan.codeitup.mail.service.MailService;
import cc.codedhyan.codeitup.token.model.Token;
import cc.codedhyan.codeitup.token.model.TokenType;
import cc.codedhyan.codeitup.token.repo.TokenRepository;
import cc.codedhyan.codeitup.user.model.Role;
import cc.codedhyan.codeitup.user.model.User;
import cc.codedhyan.codeitup.user.repo.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"otp_cache"})
public class AuthenticationService implements Serializable {
    private final UserRepository userRepository;
    private final CacheManager cacheManager;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    // otp service had to make it in a different file otherwise spring proxy won't happen resulting in no caching
    private final OTPService otpService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class OTPValidationFailedException extends RuntimeException {
        public OTPValidationFailedException(String message) {
            super(message);
        }
    }


    public void register(
            RegisterRequest request
    )throws MessagingException, UserAlreadyExistsException {
        if(userRepository.existsByProfile(request.getProfileName())){
            throw new UserAlreadyExistsException("Profile name already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("Email already exists");
        }
        var user = User.builder()
                .email(request.getEmail())
                .isVerified(false)
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .profile(request.getProfileName())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    public AuthenticationResponse authenticate(
            AuthenticationRequest request
    ) throws BadCredentialsException {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid email/password or maybe email is not verified");
        }

        var user = userRepository.getByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUser_id());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    
    public void sentOTP(GetOTPRequest request) throws MessagingException {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User with email not found"));
        sendValidationEmail(user);
    }

    public void otpValidate(
            OTPRequest request
    ) throws OTPValidationFailedException {
        String email = request.getEmail();
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String cachedOTP = cacheManager.getCache("otp_cache").get(email, String.class);
        logger.info("Cached OTP for {}: {}", request.getEmail(), cachedOTP);
        if(cachedOTP == null){
            throw new OTPValidationFailedException("OTP not found in database, try resending otp");
        }
        if(!cachedOTP.equals(request.getOtp())){
            throw new OTPValidationFailedException("Invalid OTP");
        }
        user.setVerified(true);
        userRepository.save(user);
    }




    private void sendValidationEmail(User user) throws MessagingException {
        var otp = otpService.getOTP(user);
        logger.info("OTP for user {} is {}", user.getEmail(), otp);
        mailService.sendEmail(user.getEmail(), "OTP for email verification", "Your OTP is " + otp);
    }


}

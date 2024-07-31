package cc.codedhyan.codeitup.auth.controller;

import cc.codedhyan.codeitup.auth.AuthenticationRequest;
import cc.codedhyan.codeitup.auth.GetOTPRequest;
import cc.codedhyan.codeitup.auth.OTPRequest;
import cc.codedhyan.codeitup.auth.RegisterRequest;
import cc.codedhyan.codeitup.auth.service.AuthenticationService;
import cc.codedhyan.codeitup.exception.ApiInternalServerErrorException;
import cc.codedhyan.codeitup.exception.ApiRequestExceptionBadRequest;
import cc.codedhyan.codeitup.exception.ApiRequestExceptionConflict;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody @Valid RegisterRequest request
    ){
        try{
            authenticationService.register(request);
            return ResponseEntity.accepted().build();
        } catch (AuthenticationService.UserAlreadyExistsException e) {
            logger.error("User already exists", e);
            throw new ApiRequestExceptionConflict(e.getMessage(),e);
        } catch (MessagingException e) {
            logger.error("Error sending mail", e);
            throw new ApiInternalServerErrorException(e.getMessage(),e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        try{
            return ResponseEntity.ok(authenticationService.authenticate(request));
        } catch ( BadCredentialsException e) {
            logger.error("Bad credentials", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/sent-otp")
    public ResponseEntity<?> sentOTP(
            @RequestBody @Valid GetOTPRequest request
    ) {
        try{
            authenticationService.sentOTP(request);
            return ResponseEntity.accepted().build();
        } catch ( UsernameNotFoundException e) {
            logger.error("Bad credentials", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (MessagingException e) {
            logger.error("Error sending mail", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(
            @RequestBody @Valid OTPRequest request
    ) {
        try{
            authenticationService.otpValidate(request);
            return ResponseEntity.accepted().build();
        } catch (AuthenticationService.OTPValidationFailedException e) {
            logger.error("OTP validation failed", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}

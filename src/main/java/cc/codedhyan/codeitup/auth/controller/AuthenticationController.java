package cc.codedhyan.codeitup.auth.controller;

import cc.codedhyan.codeitup.auth.OTPRequest;
import cc.codedhyan.codeitup.auth.RegisterRequest;
import cc.codedhyan.codeitup.auth.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ) throws MessagingException, IOException {
        try{
            authenticationService.register(request);
            return ResponseEntity.accepted().build();
        } catch (AuthenticationService.UserAlreadyExistsException e) {
            logger.error("User already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
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

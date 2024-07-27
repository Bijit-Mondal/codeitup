package cc.codedhyan.codeitup.auth.service;

import cc.codedhyan.codeitup.user.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@CacheConfig(cacheNames = {"otp_cache"})
public class OTPService {

    @CachePut(value = "otp_cache", key = "#user.email")
    public String getOTP(User user){
        return otpGenerate();
    }

    private String otpGenerate(){
        String character = "0123456789";
        StringBuilder otp = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i=0;i<4;i++){
            otp.append(character.charAt(random.nextInt(character.length())));
        }
        return otp.toString();
    }
}

package com.example.authentication.service;

import com.example.authentication.Model.entity.Token;
import com.example.authentication.Model.entity.User;
import com.example.authentication.exception.ServiceException;
import com.example.authentication.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ResetPasswordService {
    private final UserService userService ;
    private final TokenService tokenService ;
    private final EmailService emailService ;
    private final BCryptPasswordEncoder passwordEncoder ;
    public String generateResetPasswordToken(String email) {
        User user = (User)userService.loadUserByUsername(email) ;
        String token = UUID.randomUUID().toString() ;
        tokenService.buildToken(user , token);
        String link = "http://localhost:8081/password/reset-password/confirm?token="+token ;
        emailService.send(user.getEmail() , link);
        return token ;
    }
    @Transactional(rollbackFor = ServiceException.class)
    public String resetPassword(String token, String password ) throws ServiceException {
        Token confirmedToken = tokenService.getToken(token).orElseThrow(() ->
                new ServiceException("Token Not Found")
        ) ;
        if (confirmedToken.getConfirmedAt() != null) {
            throw new ServiceException("token was expired") ;
        }
        LocalDateTime expiredAt = confirmedToken.getExpiresAt() ;
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ServiceException("token was expired") ;
        }
        tokenService.setConfirmedAt(token) ;
        User user = confirmedToken.getUser() ;
        String encodedPassword = passwordEncoder.encode(user.getPassword()) ;
        userService.changePassword(user.getUsername() , encodedPassword);
        return "success!" ;
    }

}

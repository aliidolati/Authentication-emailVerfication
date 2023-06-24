package com.example.authentication.service;

import com.example.authentication.Model.entity.Token;
import com.example.authentication.Model.entity.User;
import com.example.authentication.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository repository ;
    public void save(Token token) {
        repository.save(token) ;
    }

    public void buildToken(User user,String tokenName){
        Token token = Token.builder()
                .token(tokenName)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user).build() ;
        repository.save(token) ;
    }
    public Optional<Token> getToken(String token) {
        return repository.findByToken(token) ;
    }

    public int setConfirmedAt(String token) {
        return repository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}

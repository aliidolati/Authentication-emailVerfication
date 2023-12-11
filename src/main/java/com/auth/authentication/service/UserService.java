package com.example.authentication.service;


import com.example.authentication.Model.entity.Token;
import com.example.authentication.Model.entity.User;
import com.example.authentication.exception.ServiceException;
import com.example.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService extends AbstractService<User,UserRepository> implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder ;
    private final TokenService tokenService ;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByEmail(username).orElseThrow( ()->
                new UsernameNotFoundException("Email not found")
        ) ;
    }

    @Transactional(rollbackFor = ServiceException.class)
    public String signUp(User user) throws ServiceException {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new ServiceException("Email is already is exist , please login!") ;
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setSecurePassword(encodedPassword);
        repository.save(user) ;
        String token = UUID.randomUUID().toString();
        tokenService.buildToken(user,token);
        return token ;
    }
    public void enableUser(String email){
        repository.enableUser(email);
    }
    public void changePassword(String username , String password) {
        repository.changePassword(username , password);
    }
}

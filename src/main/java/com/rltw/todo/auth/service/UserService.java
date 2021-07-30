package com.rltw.todo.auth.service;

import com.rltw.todo.auth.dto.AuthPayload;
import com.rltw.todo.auth.model.User;
import com.rltw.todo.auth.repository.AuthUserRepository;
import com.rltw.todo.auth.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserService {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public Optional<User> findById(long id){
        return authUserRepository.findById(id);
    }

    public User createUser(AuthPayload payload){
        User user = payload.toUser();

        String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
        user.setPassword(hashedPassword);

        return authUserRepository.save(user);
    }
    

    public boolean doesUsernameExist(String username){
        return authUserRepository.existsByUsername(username);
    }

    public String encodeJWT(long userId){
        return jwtUtil.createToken(userId);
    }

    public User getUser(AuthPayload payload) throws RuntimeException{
        Optional<User> userOptional = authUserRepository.getUserByUsername(payload.getUsername());
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        if(!BCrypt.checkpw(payload.getPassword(),user.getPassword())){
            throw new RuntimeException("Password didn't match");
        }

        return user;
    }
}

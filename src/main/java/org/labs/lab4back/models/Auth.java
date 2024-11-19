package org.labs.lab4back.models;

import org.labs.lab4back.entities.User;
import org.labs.lab4back.enums.Role;
import org.labs.lab4back.repositories.IUserRepository;
import org.labs.lab4back.utilsbeans.Encrypt;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Service
@SessionScope
public class Auth implements Serializable {
    @Autowired
    private PointCollection collection;
    @Autowired
    private Encrypt encrypt;
    private final IUserRepository userRepository;
    private User user;

    public Auth(@Autowired IUserRepository userRepository) {
        user = null;
        this.userRepository = userRepository;
    }

    public boolean doAuth(String username, String password) {
        var user = userRepository.findByUsernameAndPassword(username, encrypt.encryptSHA512(password));
        if (user.isEmpty()) {
            return false;
        } else {
            this.user = user.get();
            collection.refresh();
            return true;
        }
    }
    public boolean register(String username, String password) {
        var user = new User(username, encrypt.encryptSHA512(password), Role.ROLE_USER);
        if(userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public User getUser() {
        return user;
    }
}

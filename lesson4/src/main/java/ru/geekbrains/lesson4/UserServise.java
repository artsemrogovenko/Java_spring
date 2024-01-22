package ru.geekbrains.lesson4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServise {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        userRepository.save(new User(null,"Walle","uopo@hoh.u"));
        userRepository.save(new User(null,"John","uopo@hoh.u"));
        userRepository.save(new User(null,"Henry","uopo@hoh.u"));
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

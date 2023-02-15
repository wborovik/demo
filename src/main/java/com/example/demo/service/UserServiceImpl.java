package com.example.demo.service;

import com.example.demo.model.Quote;
import com.example.demo.model.User;
import com.example.demo.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.of(userRepository.findById(id)).get();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserById(Long id, User user) {
        Optional<User> userData = getUserById(id);

        if (userData.isPresent()) {
            User userUpdate = userData.get();
//            userUpdate.setId(user.getId());
            userUpdate.setName(user.getName());
            userUpdate.setLogin(user.getLogin());
            userUpdate.setEmail(user.getEmail());
//            userUpdate.setDateCreateUser(user.getDateCreateUser());
            userRepository.save(userUpdate);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addQuote(Long id, User user, Quote quote) {
        Optional<User> userData = getUserById(id);

        if (userData.isPresent()) {
            User userUpdate = userData.get();
            userUpdate.addQuote(quote);
        }
    }
}

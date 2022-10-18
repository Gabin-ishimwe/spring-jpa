package com.gabinishimwe.springdatajpa.services;

import com.gabinishimwe.springdatajpa.entities.*;
import com.gabinishimwe.springdatajpa.repositories.PasswordResetTokenRepository;
import com.gabinishimwe.springdatajpa.repositories.UserRepository;
import com.gabinishimwe.springdatajpa.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;


    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    UserService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }
    public User createUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
//        user.setRole("USER");
        userRepository.save(user);
        return user;
    }

    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(username);
        System.out.println(user);
        if(user == null) {
            System.out.println("User not found");
        } else {
            System.out.println("User found in the database");

        }
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        assert user != null;
        user.getRole().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(), authorities);
    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken == null) {
            return "Invalid";
        }
        User user = verificationToken.getUser();

        System.out.println(user);

        Calendar calendar = Calendar.getInstance();

        if(verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            System.out.println("Has expired");
            verificationTokenRepository.delete(verificationToken);
            return "Token has expired";
        }

        user.setEnabled(true);
        userRepository.save(user);

        return "valid";
    }

    public VerificationToken generateNewVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    public User findUserByEmail(PasswordModel passwordModel) {
        return userRepository.findByEmail(passwordModel.getEmailId());
    }

    public void createPasswordResetToken(String token, User user) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

        if(passwordResetToken == null) {
            return "Invalid";
        }
        User user = passwordResetToken.getUser();

        System.out.println(user);

        Calendar calendar = Calendar.getInstance();

        if(passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            System.out.println("Has expired");
            passwordResetTokenRepository.delete(passwordResetToken);
            return "Token has expired";
        }

        return "valid";
    }

    public User getUserByPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token).getUser();
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}

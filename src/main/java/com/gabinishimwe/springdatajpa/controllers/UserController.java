package com.gabinishimwe.springdatajpa.controllers;

import com.gabinishimwe.springdatajpa.Event.RegistrationEvent;
import com.gabinishimwe.springdatajpa.entities.*;
import com.gabinishimwe.springdatajpa.repositories.RoleRepository;
import com.gabinishimwe.springdatajpa.repositories.UserRepository;
import com.gabinishimwe.springdatajpa.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher publisher;
    @PostMapping(path = "/register")
    public String userRegister(@RequestBody UserModel userModel, HttpServletRequest request) {
        User user = userService.createUser(userModel);
        publisher.publishEvent(new RegistrationEvent(
                user,
                applicationUrl(request)
        ));
        return "user registered";
    }

    @PostMapping(path = "/role")
    public String saveRole(@RequestBody Role role) {
        roleRepository.save(role);
        return "role created";
    }

    @PostMapping(path = "/assign-role")
    public User assignRole(@RequestBody AssignRoleDto assignRoleDto) {
        User findUser = userRepository.findById(assignRoleDto.getUserId()).orElseThrow(() -> new IllegalStateException("User doesn't exists"));
        Role findRole = roleRepository.findById(assignRoleDto.getRoleId()).orElseThrow(() -> new IllegalStateException("Role doesn't exist"));
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(findRole);
        findUser.setRole(roles);
        userRepository.save(findUser);
        return findUser;
    }

    @GetMapping(path = "/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return "User verified successfully";
        }
        return "User not verified";
    }

    @GetMapping(path = "/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String token, HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(token);

        User user = verificationToken.getUser();
        resendVerificationEmail(user, applicationUrl(request), verificationToken);
        return "Verification Link send again-----";
    }

    private void resendVerificationEmail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();
        // ... faking sending email
        log.info("Another logger to verify email " + url);
    }

    @PostMapping(path = "/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordModel);
        String url = "";
        if(user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetToken(token, user);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }
        return null;
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/savePassword?token=" + token;
        // ... faking sending email
        log.info("click to reset your password" + url);
        return url;
    }

    @PostMapping(path = "/savePassword")
    public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {
        String result = userService.validatePasswordResetToken(token);

        if(!result.equalsIgnoreCase("valid")) {
            return "Invalid Token";
        }

        Optional<User> user = Optional.ofNullable(userService.getUserByPasswordResetToken(token));
        if(user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password reset Successful";
        } else {
            return "Invalid Token";
        }

    }

    @PostMapping(path = "/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel) {
        User user = userService.findUserByEmail(passwordModel);
        if (!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())) {
            return "Invalid Old Password";
        }
        // ... save new password
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password changed successful";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}

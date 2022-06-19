package ru.lanya.magiccooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanya.magiccooking.entity.Response;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.magiccooking.service.UserService;

@RestController
@PreAuthorize("permitAll()")
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping("sign-up")
    public Response signUp(@RequestBody User user){
        return userService.signUp(user);
    }
}

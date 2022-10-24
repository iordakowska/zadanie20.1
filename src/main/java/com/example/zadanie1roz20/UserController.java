package com.example.zadanie1roz20;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/add")
    @ResponseBody
    public String users(@RequestParam String firstName,@RequestParam String lastName,@RequestParam Integer age) {
        User user = new User(firstName, lastName, age);
            if (firstName != "") {
                userRepository.add(user);
                return "redirect/success.html";
            } else {
                return "redirect/err.html";
            }

    }

    @GetMapping("/users")
    @ResponseBody
    public String list() {
        List<User> userList = userRepository.getUserList();
        String result = "";

        for (User user1 : userList) {
            result += user1.getFirstName() + " " + user1.getLastName() + ":   " + user1.getAge() + "<br/>";
        }
        return result;
    }

    @GetMapping("/add")
    @ResponseBody
    public String addUser(@RequestParam("imie") String firstName,@RequestParam("nazwisko") String lastName,@RequestParam("wiek") Integer age) {
        User user = new User(firstName , lastName, age);
        if (firstName != null) {
            userRepository.add(user);
            return "redirect/success.html";
        } else {
            return "redirect/err.html";
        }
    }
}

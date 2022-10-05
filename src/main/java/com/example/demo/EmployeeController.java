package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final TransporRepository transporRepository;

    @Autowired
    public EmployeeController(EmployeeRepository userService, TransporRepository transporRepository) {
        this.employeeRepository = userService;
        this.transporRepository = transporRepository;
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", employeeRepository.findAll());
        return "main";
    }

    @GetMapping("/signup")
    public String showSignUpForm(employee user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid employee user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        employeeRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        employee user = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @GetMapping("/transports/{id}")
    public String showOwnedTransports(@PathVariable("id") long id, Model model) {
        model.addAttribute("transportsvalues", transporRepository.findByemployee_idEquals(id));

        return "user-transports";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,  employee user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        employeeRepository.save(user);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        employee user = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        employeeRepository.delete(user);
        return "redirect:/index";
    }
}
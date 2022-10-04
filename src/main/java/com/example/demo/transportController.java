package com.example.demo;

import org.attoparser.dom.INestableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.TreeMap;

@Controller
public class transportController {
    private final TransporRepository transporRepository;
    private final EmployeeRepository employeeRepository;


    @Autowired
    public transportController(TransporRepository transporRepository,
                               EmployeeRepository employeeRepository){
        this.transporRepository   = transporRepository;
        this.employeeRepository = employeeRepository;
    }




    @GetMapping("/addtransportpage")
    public String showForm(Model model){
        model.addAttribute("transport", new transport());
        List<employee>  employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);

        return "add-transport";
    }

    @PostMapping("/addtransport")
    public String addTransport(@Valid transport transport, BindingResult result, Model model){
        if (result.hasErrors()) {
            System.out.print(result.getModel().toString());
            List<employee>  employees = employeeRepository.findAll();
            model.addAttribute("employees", employees);
            return "add-transport";
        }
        transporRepository.save(transport);
        return "redirect:/index";
    }



}

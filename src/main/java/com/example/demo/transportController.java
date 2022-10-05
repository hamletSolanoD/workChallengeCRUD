package com.example.demo;

import org.attoparser.dom.INestableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Controller
public class transportController {
    private final TransporRepository transporRepository;
    private final EmployeeRepository employeeRepository;


    @Autowired
    public transportController(TransporRepository transporRepository,
                               EmployeeRepository employeeRepository) {
        this.transporRepository = transporRepository;
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/addtransportpage")
    public String showAddForm(Model model) {
        model.addAttribute("transport", new transport());
        List<employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);

        return "add-transport";
    }

    @PostMapping("/addtransport")
    public String addTransport(@Valid transport transport, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.print(result.getModel().toString());
            List<employee> employees = employeeRepository.findAll();
            model.addAttribute("employees", employees);
            return "add-transport";
        }
        transporRepository.save(transport);
        return "redirect:/index";
    }

    @GetMapping("/edittransportpage/{id}")
    public String showEditTransportForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("transporttoedit", transporRepository.findById(id));
        model.addAttribute("transportid", id);

        return "edit-transport";
    }

    @PostMapping("/edittransport/{id}")
    public String showEditTransportForm(@PathVariable("id") long id,
                                        @RequestParam(value = "latitude", required = false) double latitude,
                                        @RequestParam(value = "longitude", required = false) double longitude, Model model
    ) {
        transport transport = transporRepository.findById(id).get();
        transport.setLatitude(latitude);
        transport.setLongitude(longitude);
        transporRepository.save(transport);

        return "redirect:/index";
    }

    @GetMapping("/deletetransport/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        transport transport = transporRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid transport Id:" + id));
        transporRepository.delete(transport);
        return "redirect:/index";
    }


}

package com.nekisse.controllers;

import com.nekisse.service.BankAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class Cont {

    final BankAccountService bankAccountService;

    public Cont(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @RequestMapping("/test")
    @ResponseBody
    String home() {
        return "Happy Coding!";
    }


    @RequestMapping("")
    String nome2() {

//        bankAccountService.lll(filename11);
        bankAccountService.lll();
//        System.out.println("members = " + members);
        return "string";
    }


    @RequestMapping("/findName/{name}")
    @ResponseBody
    public String findName(@PathVariable String name, Model model) {
//        model.addAttribute("attr", bankAccountService.findNameDto(name).toString());

        return bankAccountService.findHistoryOfOneDepositor(name).toString();

    }
}
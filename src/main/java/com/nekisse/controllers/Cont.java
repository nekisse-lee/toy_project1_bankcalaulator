package com.nekisse.controllers;

import com.nekisse.domain.dto.BankDto;
import com.nekisse.domain.dto.FindUserRequestDto;
import com.nekisse.service.BankAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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


//    @GetMapping({"", "/find/{name}", "/find"})
    @GetMapping({""})
    public String nome2(@PathVariable(required = false, name = "userName") FindUserRequestDto name, Model model, @RequestParam(name = "fileNamee",required = false) String fileNamee) {
        System.out.println("reqqqq = " + fileNamee);

//        bankAccountService.lll(filename11);
        bankAccountService.excelFileReadAndSave();
        model.addAttribute("depositors", bankAccountService.findHistoryOfNameOfDepositor(name));
//        System.out.println("members = " + members);
        return "home";
    }

    @GetMapping("/test")
    public String test(@RequestParam String fileName) {
        System.out.println("Controller="  +fileName);
        return "home";
    }


/*
    @GetMapping({"/find/{name}", "/find"})
//    @RequestMapping({"/find/{name}","/find"})
    public String findName(@PathVariable(required = false, name = "name") String name, Model model) {

        model.addAttribute("depositors", bankAccountService.findHistoryOfNameOfDepositor(name));
//        return bankAccountService.findHistoryOfNameOfDepositor(name).toString();
        return "home";
//        return String.valueOf(bankAccountService.findHistoryOfNameOfDepositor(name));
    }
*/
@GetMapping({"/find"})
//    @RequestMapping({"/find/{name}","/find"})
public String findName( FindUserRequestDto name, Model model) {

    model.addAttribute("depositors", bankAccountService.findHistoryOfNameOfDepositor(name));
//        return bankAccountService.findHistoryOfNameOfDepositor(name).toString();
    return "home";
//        return String.valueOf(bankAccountService.findHistoryOfNameOfDepositor(name));
}

}
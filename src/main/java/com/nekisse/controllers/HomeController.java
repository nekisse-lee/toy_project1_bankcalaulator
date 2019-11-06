package com.nekisse.controllers;

import com.nekisse.domain.dto.FindUserRequestDto;
import com.nekisse.service.BankAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final BankAccountService bankAccountService;

    public HomeController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @RequestMapping("/test")
    @ResponseBody
    String home() {
        return "Happy Coding!";
    }


//    @GetMapping({"", "/find/{name}", "/find"})
    @GetMapping({""})
    public String nome2(@PathVariable(required = false, name = "userName") FindUserRequestDto name, Model model, @RequestParam(name = "fileName",required = false) String fileName) {
        System.out.println("reqqqq = " + fileName);
//        bankAccountService.excelFileReadAndSave(fileName);
//        model.addAttribute("depositors", bankAccountService.findHistoryOfNameOfDepositor(name));
//        System.out.println("members = " + members);
        return "home";
    }

    @GetMapping("/test")
    public String test(@RequestParam String fileName) {
        System.out.println("Controller="  +fileName);
        return "home";
    }


@GetMapping({"/find"})
//    @RequestMapping({"/find/{name}","/find"})
public String findName( FindUserRequestDto name, Model model) {

    model.addAttribute("depositors", bankAccountService.findHistoryOfNameOfDepositor(name));
//        return bankAccountService.findHistoryOfNameOfDepositor(name).toString();
    return "home";
//        return String.valueOf(bankAccountService.findHistoryOfNameOfDepositor(name));
}


@PostMapping("/deleteData")
public String deleteData() {
    bankAccountService.deleteAllData();
    return "home";
}

}
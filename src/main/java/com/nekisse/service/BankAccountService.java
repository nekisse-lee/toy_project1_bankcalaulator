package com.nekisse.service;

import com.nekisse.domain.BankAccount;
import com.nekisse.domain.BankAccountRepository;
import com.nekisse.domain.dto.BankDto;
import com.nekisse.read.LocalRead;
import com.nekisse.read.ExcelReadOption;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void lll() {
        ExcelReadOption ro = new ExcelReadOption();
//        ClassPathResource classPathResource = new ClassPathResource("/static/UPLOAD_FILES/KB_거래내역조회(459601-01-580016_20191020160628).xls");
//        String path1 = classPathResource.getPath();
//        Path path = Paths.get(path1);
//
//        String s = path.toString();
//        System.out.println("s = " + s);
        ro.setFilePath("/Users/nekisse/Documents/intellij_workspace/my_calculator/src/main/resources/static/UPLOAD_FILES/KB_거래내역조회(459601-01-580016_20191020160628).xls");
        ro.setOutputColumns("A", "C", "E", "F", "G");
//        List<Member> members = new ArrayList<>();

        List<Map<String, String>> result = LocalRead.read(ro);
        for (int i = 1; i < result.size(); i++) {
//            String beforeName = result.get(i).get("C");
            Map<String, String> row = result.get(i);
//            for (String s : row.keySet()) {
//                System.out.println("s = " + s);
//            }
            List<String> outputColumns = ro.getOutputColumns();
            BankDto bankDto = new BankDto();
            saveRepository(row, outputColumns, bankDto);
//            members.add(new Member(beforeName));
        }
//        System.out.println("members = " + members);
    }



    private void saveRepository(Map<String, String> row, List<String> outputColumns, BankDto bankDto) {
        for (String outputColumn : outputColumns) {
            switch (outputColumn) {
                case "A":
                    bankDto.setTradingDate(row.get(outputColumn));
                    break;
                case "C":
                    bankDto.setDepositor(row.get(outputColumn));
                    break;
                case "E":
                    bankDto.setWithdrawalAmount(row.get(outputColumn));
                    break;
                case "F":
                    bankDto.setDepositAmount(row.get(outputColumn));
                    break;
                case "G":
                    bankDto.setTotalAmount(row.get(outputColumn));
                    break;
            }

        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setTradingDate(bankDto.getTradingDate());
        bankAccount.setDepositor(bankDto.getDepositor());
        bankAccount.setWithdrawalAmount(bankDto.getWithdrawalAmount());
        bankAccount.setDepositAmount(bankDto.getDepositAmount());
        bankAccount.setTotalAmount(bankDto.getTotalAmount());
        bankAccountRepository.save(bankAccount);
    }


    public List<BankDto> findHistoryOfOneDepositor(String name) {
        List<BankAccount> all = bankAccountRepository.findAll();
        for (BankAccount bankAccount : all) {
            System.out.println(bankAccount.getDepositor());

        }

        List<BankAccount> byDepositor = bankAccountRepository.findByDepositor(name);
        List<BankDto> newBankAccounts = new ArrayList<>();
        for (BankAccount bankAccount : byDepositor) {
            BankDto bankDto = new BankDto();

            bankDto.setTradingDate(bankAccount.getTradingDate());
            bankDto.setDepositor(bankAccount.getDepositor());
            bankDto.setDepositAmount(bankAccount.getDepositAmount());
            bankDto.setTotalAmount(bankAccount.getTotalAmount());
            bankDto.setWithdrawalAmount(bankAccount.getWithdrawalAmount());
            newBankAccounts.add(bankDto);
        }
        System.out.println(newBankAccounts.toString());


        return newBankAccounts;
    }
}

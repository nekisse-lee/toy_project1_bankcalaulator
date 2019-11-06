package com.nekisse.service;

import com.nekisse.domain.BankAccount;
import com.nekisse.domain.BankAccountRepository;
import com.nekisse.domain.dto.BankDto;
import com.nekisse.domain.dto.FindUserRequestDto;
import com.nekisse.domain.dto.responsedto.BankDataResponse;
import com.nekisse.service.read.ExcelReadOption;
import com.nekisse.service.read.ReadExcelFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void excelFileReadAndSave(String fileName) {

        ExcelReadOption excelReadOption = new ExcelReadOption();
//        ClassPathResource classPathResource = new ClassPathResource("/static/UPLOAD_FILES/KB_거래내역조회(459601-01-580016_20191020160628).xls");
//        String path1 = classPathResource.getPath();
//        Path path = Paths.get(path1);
//
//        String s = path.toString();
//        System.out.println("s = " + s);
//        ro.setFilePath("/Users/nekisse/Documents/intellij_workspace/my_calculator/src/main/resources/static/UPLOAD_FILES/KB_거래내역조회(459601-01-580016_20191020160628).xls");
        excelReadOption.setFilePath("uploads/" + fileName);
        excelReadOption.setOutputColumns("A", "C", "E", "F", "G");
//        List<Member> members = new ArrayList<>();

        List<Map<String, String>> result = ReadExcelFile.read(excelReadOption);
        for (int i = 1; i < result.size(); i++) {
//            String beforeName = result.get(i).get("C");
            Map<String, String> row = result.get(i);
//            for (String s : row.keySet()) {
//                System.out.println("s = " + s);
//            }
            List<String> outputColumns = excelReadOption.getOutputColumns();
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


    public List<BankDto> findHistoryOfNameOfDepositor(FindUserRequestDto name) {
        List<BankDto> newBankDtos = new ArrayList<>();
        if (name == null) {
            return findAll(newBankDtos);
        }
        List<BankAccount> all = bankAccountRepository.findAll();
        for (BankAccount bankAccount : all) {
            System.out.println(bankAccount.getDepositor());
        }

        List<BankAccount> byDepositor = bankAccountRepository.findByDepositor(name.getDepositor());

        for (BankAccount bankAccount : byDepositor) {
            BankDto bankDto = new BankDto();
            bankDto.setTradingDate(bankAccount.getTradingDate());
            bankDto.setDepositor(bankAccount.getDepositor());
            bankDto.setDepositAmount(bankAccount.getDepositAmount());
            bankDto.setTotalAmount(bankAccount.getTotalAmount());
            bankDto.setWithdrawalAmount(bankAccount.getWithdrawalAmount());
            newBankDtos.add(bankDto);
        }
        System.out.println(newBankDtos.toString());


        return newBankDtos;
    }

    private List<BankDto> findAll(List<BankDto> newBankAccounts) {
        List<BankAccount> all = bankAccountRepository.findAll();

        for (BankAccount bankAccount : all) {
            newBankAccounts.add(BankDto.builder()
                    .depositor(bankAccount.getDepositor())
                    .tradingDate(bankAccount.getTradingDate())
                    .depositAmount(bankAccount.getDepositAmount())
                    .withdrawalAmount(bankAccount.getWithdrawalAmount())
                    .totalAmount(bankAccount.getTotalAmount())
                    .build());
        }
        return newBankAccounts;
    }

    public void deleteAllData() {
        bankAccountRepository.deleteAll();
    }

    public List<BankDto> findAll() {
        List<BankDto> bankDtos = new ArrayList<>();

        List<BankAccount> all = bankAccountRepository.findAll();
        for (BankAccount bankAccount : all) {
            bankDtos.add(BankDto.builder()
                    .depositor(bankAccount.getDepositor())
                    .tradingDate(bankAccount.getTradingDate())
                    .depositAmount(bankAccount.getDepositAmount())
                    .withdrawalAmount(bankAccount.getWithdrawalAmount())
                    .totalAmount(bankAccount.getTotalAmount())
                    .build());
        }
        return bankDtos;
    }

    public BankDataResponse getBankDataResponse() {
        List<BankDto> bankDtos = new ArrayList<>();

        List<BankAccount> all = bankAccountRepository.findAll();
        for (BankAccount bankAccount : all) {
            bankDtos.add(BankDto.builder()
                    .depositor(bankAccount.getDepositor())
                    .tradingDate(bankAccount.getTradingDate())
                    .depositAmount(bankAccount.getDepositAmount())
                    .withdrawalAmount(bankAccount.getWithdrawalAmount())
                    .totalAmount(bankAccount.getTotalAmount())
                    .build());
        }
        return new BankDataResponse(bankDtos);

    }
}

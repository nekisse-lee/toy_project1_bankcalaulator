package com.nekisse.domain.dto.responsedto;

import com.nekisse.domain.dto.BankDto;

import java.util.List;

public class BankDataResponse {

    private List<BankDto> bankDtoList;

    private int totalAmount;

    private String calValue;

    public BankDataResponse(List<BankDto> bankDtoList, int totalAmount) {
        this.bankDtoList = bankDtoList;
        this.totalAmount = totalAmount;
    }

    public BankDataResponse(List<BankDto> bankDtoList) {
        this.bankDtoList = bankDtoList;
    }

    public BankDataResponse(List<BankDto> bankDtoList, String calValue) {
        this.bankDtoList = bankDtoList;
        this.calValue = calValue;
    }

    public String getCalValue() {
        return calValue;
    }

    public List<BankDto> getBankDtoList() {
        return bankDtoList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}

package com.nekisse.domain.dto;

import lombok.Builder;

@Builder
public class BankDto {

    private String tradingDate;

    private String depositor;

    private String withdrawalAmount;

    private String depositAmount;

    private String totalAmount;

    public BankDto(String tradingDate, String depositor, String withdrawalAmount, String depositAmount, String totalAmount) {
        this.tradingDate = tradingDate;
        this.depositor = depositor;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.totalAmount = totalAmount;
    }

    public BankDto() {
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(String tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "BankDto{" +
                "tradingDate='" + tradingDate + '\'' +
                ", depositor='" + depositor + '\'' +
                ", withdrawalAmount='" + withdrawalAmount + '\'' +
                ", depositAmount='" + depositAmount + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}

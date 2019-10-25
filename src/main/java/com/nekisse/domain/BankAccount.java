package com.nekisse.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String tradingDate;

    private String depositor;

    private String withdrawalAmount;

    private String depositAmount;

    private String totalAmount;

    public BankAccount() {
    }

    public BankAccount(String tradingDate, String depositor, String withdrawalAmount, String depositAmount, String totalAmount) {
        this.tradingDate = tradingDate;
        this.depositor = depositor;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

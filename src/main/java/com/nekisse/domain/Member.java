package com.nekisse.domain;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String name;

    private List<DepositHistoryDto> depositHistories;

    private int totalAmount;

    private List<Integer> month;


    public Member() {
    }

    public Member(String name) {
        this.name = name;
        this.depositHistories = new ArrayList<>();
        this.month = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return getName().equals(member.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", depositHistories=" + depositHistories +
                ", totalAmount=" + totalAmount +
                ", month=" + month +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepositHistoryDto> getDepositHistories() {
        return depositHistories;
    }

    public void setDepositHistories(List<DepositHistoryDto> depositHistories) {
        this.depositHistories = depositHistories;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Integer> getMonth() {
        return month;
    }

    public void setMonth(List<Integer> month) {
        this.month = month;
    }
}

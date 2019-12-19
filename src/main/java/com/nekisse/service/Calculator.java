package com.nekisse.service;

import com.nekisse.domain.dto.BankDto;
import com.nekisse.domain.dto.FindUserRequestDto;
import com.nekisse.domain.dto.responsedto.CalResultResponse;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Calculator {

    private static int MONTHLY_TARGET_MONEY = 10000;

    public static int getMonthlyTargetMoney() {
        return MONTHLY_TARGET_MONEY;
    }

    public static void setMonthlyTargetMoney(String monthlyTargetMoney) {
        MONTHLY_TARGET_MONEY = Integer.parseInt(monthlyTargetMoney);
    }

    public static CalResultResponse calculate(List<BankDto> dtoList, int differenceInTheMonth, FindUserRequestDto requestDto) {
        int userTotalAmount = 0;
        Collections.reverse(dtoList);
        System.out.println("dtoList = " + dtoList);

        List<BankDto> newList = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < dtoList.size(); i++) {
            if (dtoList.get(i).getTradingDate().substring(0, 7).equals(requestDto.getStartDate())) {
                temp = i;
                if (dtoList.get(temp).getDepositAmount() > MONTHLY_TARGET_MONEY) {
                    differenceInTheMonth--;
                    temp++;
                }
                break;
            }
        }

        for (int i = temp; i < dtoList.size(); i++) {

            newList.add(dtoList.get(i));
        }
        System.out.println("newList.size() = " + newList.size());
        System.out.println("newList = " + newList);


        for (BankDto bankDto : newList) {
            userTotalAmount = userTotalAmount + bankDto.getDepositAmount();
        }
//        System.out.println("differenceInTheMonth = " + differenceInTheMonth);
        int targetMoney =
                getTargetMoney(differenceInTheMonth) +
                        ((differenceInTheMonth - newList.size()) * MONTHLY_TARGET_MONEY);
//        System.out.println("targetMoney = " + targetMoney);
//        System.out.println("userTotalAmount = " + userTotalAmount);
        int result = targetMoney - userTotalAmount;
//        System.out.println("내야 할 돈 = targetMoney - userTotalAmount ===== " + (targetMoney - userTotalAmount));
//        String x = "조회기간 내야할 돈 = (월 * 10000) + ((월 - 안낸월의수) * 10000) = " + targetMoney + "\n"
//                + "총 낸돈 = " + userTotalAmount + "\n"
//                + "벌금 = 조회기간 내야할 돈 - 총 낸돈 = !!!!!" + (targetMoney - userTotalAmount) + "!!!!!";
//        System.out.println(x);
        Collections.reverse(dtoList);

//        CalResult calResult = new CalResult(targetMoney, userTotalAmount, result);
//        return x;
        return new CalResultResponse(targetMoney, userTotalAmount, result, MONTHLY_TARGET_MONEY);
    }

    @SuppressWarnings("DuplicatedCode")
    public static CalResultResponse calculate2(List<BankDto> dtoList, int differenceInTheMonth, FindUserRequestDto requestDto) {

        int userTotalAmount = 0;
        Collections.reverse(dtoList);
        System.out.println("dtoList = " + dtoList);

        List<BankDto> newList = new ArrayList<>();

        if (!isCheckStartDate(dtoList, requestDto)) {
            dtoList.add(new BankDto(requestDto.getStartDate(), requestDto.getDepositor(), 0));
        }
        if (!isCheckEndDate(dtoList,requestDto)) {
            dtoList.add(new BankDto(requestDto.getEndDate(), requestDto.getDepositor(), 0));
        }

        dtoList.sort(Comparator.comparing(BankDto::getTradingDate));
        System.out.println("dtoList = " + dtoList);

        int temp = 0;
        for (int i = 0; i < dtoList.size(); i++) {
            if (dtoList.get(i).getTradingDate().substring(0, 7).equals(requestDto.getStartDate())) {
                temp = i;
                if (dtoList.get(temp).getDepositAmount() > MONTHLY_TARGET_MONEY) {
                    differenceInTheMonth--;
                    temp++;
                }
                break;
            }
        }

        for (int i = temp; i < dtoList.size(); i++) {
            newList.add(dtoList.get(i));
        }
        System.out.println("newList.size() = " + newList.size());
        System.out.println("newList = " + newList);


        for (BankDto bankDto : newList) {
            userTotalAmount = userTotalAmount + bankDto.getDepositAmount();
        }
//        System.out.println("differenceInTheMonth = " + differenceInTheMonth);

        List<BankDto> depositList = newList.stream().filter(bankDto -> bankDto.getDepositAmount() >= 10000).collect(Collectors.toList());
//        System.out.println("collect = " + collect.size());

        int targetMoney =
                getTargetMoney(differenceInTheMonth) +
//                        ((differenceInTheMonth - newList.size()) * MONTHLY_TARGET_MONEY);
                        ((differenceInTheMonth - depositList.size()) * MONTHLY_TARGET_MONEY);


//        System.out.println("targetMoney = " + targetMoney);
//        System.out.println("userTotalAmount = " + userTotalAmount);
        int result = targetMoney - userTotalAmount;
//        System.out.println("내야 할 돈 = targetMoney - userTotalAmount ===== " + (targetMoney - userTotalAmount));
//        String x = "조회기간 내야할 돈 = (월 * 10000) + ((월 - 안낸월의수) * 10000) = " + targetMoney + "\n"
//                + "총 낸돈 = " + userTotalAmount + "\n"
//                + "벌금 = 조회기간 내야할 돈 - 총 낸돈 = !!!!!" + (targetMoney - userTotalAmount) + "!!!!!";
//        System.out.println(x);
        Collections.reverse(dtoList);

//        CalResult calResult = new CalResult(targetMoney, userTotalAmount, result);
//        return x;
        return new CalResultResponse(targetMoney, userTotalAmount, result, MONTHLY_TARGET_MONEY);
    }

    private static boolean isCheckStartDate(List<BankDto> dtoList, FindUserRequestDto requestDto) {
        return dtoList.stream().anyMatch(bankDto -> bankDto.getTradingDate().substring(0, 7).equals(requestDto.getStartDate()));
    }
    private static boolean isCheckEndDate(List<BankDto> dtoList, FindUserRequestDto requestDto) {
        return dtoList.stream().anyMatch(bankDto -> bankDto.getTradingDate().substring(0, 7).equals(requestDto.getEndDate()));
    }

    private static int getTargetMoney(int differenceInTheMonth) {
        return differenceInTheMonth * MONTHLY_TARGET_MONEY;
    }

    public static int getDifferenceInTheMonth(FindUserRequestDto requestDto) {
        LocalDate startDate = LocalDate.parse(requestDto.getStartDate() + "-01");
        LocalDate endDate = LocalDate.parse(requestDto.getEndDate() + "-01");
        startDate = startDate.withDayOfMonth(1);
        endDate = endDate.withDayOfMonth(endDate.getMonth().maxLength() - 1);
        return (endDate.getYear() - startDate.getYear()) * 12 + (endDate.getMonthValue() - startDate.getMonthValue() + 1);
    }
}

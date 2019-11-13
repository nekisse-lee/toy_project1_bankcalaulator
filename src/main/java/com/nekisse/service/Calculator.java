package com.nekisse.service;

import com.nekisse.domain.dto.BankDto;
import com.nekisse.domain.dto.FindUserRequestDto;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public static String Cal(List<BankDto> dtoList, int differenceInTheMonth, FindUserRequestDto requestDto) {

        int userTotalAmount = 0;
        Collections.reverse(dtoList);
        System.out.println("dtoList = " + dtoList);

        List<BankDto> newList = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < dtoList.size(); i++) {
            if (dtoList.get(i).getTradingDate().substring(0, 7).equals(requestDto.getStartDate())) {
                temp = i;
                if (dtoList.get(temp).getDepositAmount() > 10000) {
                    differenceInTheMonth--;
                    temp++;
                }
                break;
            }
        }

        for (int i = temp; i < dtoList.size(); i++) {
            if (dtoList.get(i).getDepositAmount() != 0) {

            newList.add(dtoList.get(i));
            }
        }
        System.out.println("newList.size() = " + newList.size());
        System.out.println("newList = " + newList);

//        if (dtoList.size() == newList.size()) {
//            throw new IllegalArgumentException("조회하는 유저의 시작월이 존재하지 않습니다.");
//        }


        for (BankDto bankDto : newList) {
            userTotalAmount = userTotalAmount + bankDto.getDepositAmount();
        }
        System.out.println("differenceInTheMonth = " + differenceInTheMonth);
        int targetMoney = (differenceInTheMonth * 10000) + ((differenceInTheMonth - newList.size()) * 10000);
        System.out.println("targetMoney = " + targetMoney);
        System.out.println("userTotalAmount = " + userTotalAmount);

//        System.out.println("내야 할 돈 = targetMoney - userTotalAmount ===== " + (targetMoney - userTotalAmount));
        String x = "조회기간 내야할 돈 = (월 * 10000) + ((월 - 안낸월의수) * 10000) = " + targetMoney + "\n"
                + "총 낸돈 = " + userTotalAmount + "\n"
                + "벌금 = 조회기간 내야할 돈 - 총 낸돈 = !!!!!" + (targetMoney - userTotalAmount) + "!!!!!";
        System.out.println(x);
        Collections.reverse(dtoList);
        return x;
    }

    public static int getDifferenceInTheMonth(FindUserRequestDto requestDto) {
        LocalDate startDate = LocalDate.parse(requestDto.getStartDate() + "-01");
        LocalDate endDate = LocalDate.parse(requestDto.getEndDate() + "-01");
        startDate = startDate.withDayOfMonth(1);
        endDate = endDate.withDayOfMonth(endDate.getMonth().maxLength() - 1);
        return (endDate.getYear() - startDate.getYear()) * 12 + (endDate.getMonthValue() - startDate.getMonthValue() + 1);
    }
}

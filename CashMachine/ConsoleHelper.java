package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader((new InputStreamReader(System.in)));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
        String currencyCode = ConsoleHelper.readString();
        if (currencyCode.length() == 3) {
            currencyCode = currencyCode.toUpperCase();
            return currencyCode;
        } else {
            throw new InterruptOperationException();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] denomAndCount = ConsoleHelper.readString().split(" ");
        int denomination = Integer.parseInt(denomAndCount[0]);
        int count = Integer.parseInt(denomAndCount[1]);
        if (denomination > 0 && count > 0 && denomAndCount.length == 2) {
            return denomAndCount;
        } else {
            throw new InterruptOperationException();
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        do {
            ConsoleHelper.writeMessage(res.getString("choose.operation"));
            ConsoleHelper.writeMessage(String.format("%s\n%s\n%s\n%s", res.getString("operation.INFO"), res.getString("operation.DEPOSIT"), res.getString("operation.WITHDRAW"), res.getString("operation.EXIT")));
            try {
                int number = Integer.parseInt(ConsoleHelper.readString());
                return Operation.getAllowableOperationByOrdinal(number);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        } while (true);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String str = bis.readLine();
            if (str.toLowerCase().equals("exit")) {
                throw new InterruptOperationException();
            }
            return str;
        } catch (IOException e) {

        }
        return null;
    }

    public static void printExitMessage(){
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}

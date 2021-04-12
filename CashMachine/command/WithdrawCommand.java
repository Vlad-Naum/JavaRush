package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        do {
            String currencyCode = ConsoleHelper.askCurrencyCode();
            CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            int count = 0;
            try {
                count = Integer.parseInt(ConsoleHelper.readString());
                if(count <= 0){
                    System.out.println(res.getString("specify.not.empty.amount"));
                }
                else{
                    if(cm.isAmountAvailable(count)){
                        try {
                            Map<Integer, Integer> map = cm.withdrawAmount(count);
                            for (Map.Entry<Integer, Integer> copy : map.entrySet()) {
                                ConsoleHelper.writeMessage(copy.getKey() + " - " + copy.getValue());
                            }
                            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), count, currencyCode));
                            break;
                        } catch (NotEnoughMoneyException e) {
                            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                        }
                    }
                    else{
                        ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(res.getString("specify.not.empty.amount"));
            }
        } while (true);
    }
}

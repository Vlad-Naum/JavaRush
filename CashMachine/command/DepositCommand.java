package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        Locale.setDefault(Locale.ENGLISH);
        ConsoleHelper.writeMessage(res.getString("before"));
        do {
            try {
                String currencyCode = ConsoleHelper.askCurrencyCode();
                CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
                String[] s = ConsoleHelper.getValidTwoDigits(currencyCode);
                cm.addAmount(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cm.getTotalAmount(), currencyCode));
                break;
            } catch (InterruptOperationException | NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        } while (true);

    }
}

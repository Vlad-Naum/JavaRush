package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

class InfoCommand implements Command {
    private ResourceBundle res =  ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if(currencyManipulator.hasMoney()){
                System.out.println(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
            }
            else{
                ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }
    }
}

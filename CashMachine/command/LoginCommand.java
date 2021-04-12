package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;


public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        do {
            String number, pin;
            do {
                ConsoleHelper.writeMessage(res.getString("specify.data"));
                number = ConsoleHelper.readString();
                pin = ConsoleHelper.readString();
                if(number.length() == 12 && pin.length() == 4){
                    break;
                }
                else{
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                }
            } while (true);

            if(validCreditCards.containsKey(number) && validCreditCards.getString(number).equals(pin)){
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));
                break;
            }
            else{
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), number));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        } while (true);
    }
}

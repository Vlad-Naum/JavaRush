package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public void addAmount(int denomination, int count){
        if(denominations.get(denomination) == null){
            denominations.put(denomination, count);
        }
        else{
            int count2 = denominations.get(denomination) + count;
            denominations.put(denomination, count2);
        }
    }

    public boolean hasMoney(){
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        int sum = expectedAmount;
        HashMap<Integer, Integer> copy = new HashMap<>(denominations);
        ArrayList<Integer> keys = new ArrayList<>(denominations.keySet());
        Collections.sort(keys);
        Collections.reverse(keys);
        TreeMap<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());

        for (Integer key : keys) {
            int value = copy.get(key);
            while (true) {
                if(sum < key || value == 0){
                    copy.put(key, value);
                    break;
                }
                sum -= key;
                value--;
                if(result.containsKey(key)){
                    result.put(key, result.get(key)+1);
                }
                else{
                    result.put(key, 1);
                }
            }
        }

        if (sum > 0)
            throw new NotEnoughMoneyException();
        else {
            this.denominations.clear();
            this.denominations.putAll(copy);
        }
        return result;
    }

    public int getTotalAmount(){
        int sum = 0;
        for(Map.Entry<Integer,Integer> copy : denominations.entrySet()){
            sum += (copy.getValue()*copy.getKey());
        }
        return sum;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}

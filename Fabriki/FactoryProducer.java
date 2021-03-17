package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    enum HumanFactoryType{
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType h){
        if(h.equals(HumanFactoryType.MALE)){
            return new MaleFactory();
        }
        else{
            return new FemaleFactory();
        }
    }
}

package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... provider) {
        if(provider == null || view == null || provider.length == 0){
            throw new IllegalArgumentException();
        }
        else{
            this.view = view;
            this.providers = provider;
        }
    }

    public void selectCity(String city){
        List<Vacancy> list = new ArrayList<>();
        for (Provider provider1 : providers) {
            list.addAll(provider1.getJavaVacancies(city));
        }
        view.update(list);
    }
}

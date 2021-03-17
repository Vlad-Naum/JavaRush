package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startTime = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date startTime = new Date();
        for (Long l : ids) {
            strings.add(shortener.getString(l));
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        long g1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long g2 = getTimeToGetIds(shortener2, origStrings, ids2);

        long g3 = getTimeToGetStrings(shortener1, ids1, new HashSet<>());
        long g4 = getTimeToGetStrings(shortener2, ids2, new HashSet<>());

        Assert.assertTrue(g1 > g2);
        Assert.assertEquals(g3, g4, 30);
    }
}

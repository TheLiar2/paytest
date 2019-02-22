package com.btba.utils;

import java.util.Random;

/**
 * @author xiaokuli
 * @date 2018/12/27 - 11:00
 */
public class KeyUtils {

    public static synchronized String getUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis()+String.valueOf(number);

     }
}

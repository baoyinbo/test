package com.baoyb.gittest.util;

/**
 * Created by baoyb on 2017/4/1.
 */

public class WeatherPmUtils {

    public static String getPmQuality(String pm) {
        int pmInt = NumberUtils.parseInt(pm, 0);
        if (0 <= pmInt && 50 > pmInt) {
            return "0";
        } else if (50 <= pmInt && 100 > pmInt) {
            return "1";
        } else if (100 <= pmInt && 150 > pmInt) {
            return "2";
        } else {
            return "3";
        }
    }
}

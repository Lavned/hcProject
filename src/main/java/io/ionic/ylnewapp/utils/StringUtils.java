package io.ionic.ylnewapp.utils;

import android.util.Log;

/**
 * Created by mogojing on 2018/5/17/0017.
 */

public class StringUtils {

    public static  String sliptStr(String str){
        String strVal = str.substring(0,4);
        String strVal2 = str.substring(str.length()-4,str.length());
        return  strVal+"****" + strVal2;
    }

}

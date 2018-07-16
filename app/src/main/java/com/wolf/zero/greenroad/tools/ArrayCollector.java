package com.wolf.zero.greenroad.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ArrayCollector {
    public static List<String> sList = new ArrayList<>();

    public static void addString(String string) {
        sList.add(string);
    }

    public static ArrayList<String> getList() {
        return (ArrayList<String>) sList;
    }
}

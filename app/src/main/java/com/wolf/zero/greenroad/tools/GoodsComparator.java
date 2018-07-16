package com.wolf.zero.greenroad.tools;


import com.wolf.zero.greenroad.bean.SerializableGoods;

import java.util.Comparator;

/**
 * Created by zerowolf on 2018/2/28.
 */

public class GoodsComparator implements Comparator {


    @Override
    public int compare(Object o, Object t1) {
        int num ;
        SerializableGoods good1 = (SerializableGoods) o;
        SerializableGoods good2 = (SerializableGoods) t1;

        if (good1.getSortId() < good2.getSortId()) {
            num = -1;
        } else if (good1.getSortId() > good2.getSortId()) {
            num = 1;
        } else {
            num = 0;
        }
        return num;
    }
}

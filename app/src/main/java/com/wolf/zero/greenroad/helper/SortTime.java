package com.wolf.zero.greenroad.helper;

import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/7/28.
 */

public class SortTime implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {

        int flag2=0;

        SupportDraftOrSubmit preview1 = (SupportDraftOrSubmit) o1;
        SupportDraftOrSubmit preview2 = (SupportDraftOrSubmit) o2;
        int flag = preview1.getCurrent_time().compareTo(preview2.getCurrent_time());
        //倒序
        if(flag>0){
            flag2=-1;
        }else if(flag<0){
            flag2=1;
        }
        return flag2;

    }


}

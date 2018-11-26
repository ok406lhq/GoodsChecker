package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

public class SupportBanci extends DataSupport {
    private ArrayList<String> Bancis;

    @Override
    public String toString() {
        return "SupportBanci{" +
                "Bancis=" + Bancis +
                '}';
    }

    public ArrayList<String> getBancis() {
        return Bancis;
    }

    public void setBancis(ArrayList<String> bancis) {
        Bancis = bancis;
    }
}

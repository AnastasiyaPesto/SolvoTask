package ru.zentsova.request;

import java.util.Formatter;
import java.util.concurrent.Callable;

public class Request implements Callable<Integer> {

    /** Sign */
    private int sign;
    /** Name of request */
    private String name;

    public Request(int sign, String name) {
        this.sign = sign;
        this.name = name;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer call() {
        return null;
    }

}

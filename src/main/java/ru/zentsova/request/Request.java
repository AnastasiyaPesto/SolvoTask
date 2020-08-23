package ru.zentsova.request;

import java.util.concurrent.Callable;

public class Request implements Callable<Integer> {

    /** Sign */
    private int sign;

    public Request(int sign) {
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    @Override
    public Integer call() throws Exception {
        return null;
    }

}

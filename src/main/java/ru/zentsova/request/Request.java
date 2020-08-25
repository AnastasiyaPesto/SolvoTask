package ru.zentsova.request;

import java.util.concurrent.Callable;

public class Request implements Callable<Integer> {

    /** Признак */
    private int x;
    /** Название запроса */
    private String name;


    protected final static String S_REQUEST_STARTED_FMT = "Обработка запроса \"%s\" запущена; X = %s";
    protected final static String S_REQUEST_FINISHED_FMT = "Обработка запроса \"%s\" завершена; X = %s";

    public Request(int x, String name) {
        this.x = x;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer call() {
        return null;
    }

}

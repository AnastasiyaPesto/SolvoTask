package ru.zentsova;

import ru.zentsova.request.ARequest;
import ru.zentsova.request.BRequest;
import ru.zentsova.request.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManager {

    private static final int MAX_COUNT_COMMON_THREAD_POOL = 10;
    private final ExecutorService executorARequest;
    private final ExecutorService executorBRequest;

    public TaskManager() {
        this.executorARequest = Executors.newFixedThreadPool(MAX_COUNT_COMMON_THREAD_POOL / 2);
        this.executorBRequest = Executors.newFixedThreadPool(MAX_COUNT_COMMON_THREAD_POOL / 2);
    }

    public void run(Request request) {
        if (request != null) {
            if (request instanceof ARequest) {
                executorARequest.submit(request);
            } else if (request instanceof BRequest) {
                executorBRequest.submit(request);
            }
        }
    }

}

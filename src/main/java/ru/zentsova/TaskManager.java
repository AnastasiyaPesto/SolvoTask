package ru.zentsova;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.zentsova.request.ARequest;
import ru.zentsova.request.BRequest;
import ru.zentsova.request.Request;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskManager {

    private final ExecutorService executorARequest;
    private final ExecutorService executorBRequest;
    private final Map<Integer, Future<Integer>> futuresARequest = new HashMap<>();
    private final Map<Integer, Future<Integer>> futuresBRequest = new HashMap<>();
    private final List<Integer> existingARequests = new LinkedList<>();
    private final List<Integer> existingBRequests = new LinkedList<>();

    private static final Logger log = LogManager.getLogger(TaskManager.class);
    private static final int MAX_COUNT_COMMON_THREAD_POOL = 10;

    public TaskManager() {
        this.executorARequest = Executors.newFixedThreadPool(MAX_COUNT_COMMON_THREAD_POOL / 2);
        this.executorBRequest = Executors.newFixedThreadPool(MAX_COUNT_COMMON_THREAD_POOL / 2);
    }

    public void run(final Request request) {
        try {
            if (existingARequests.contains(request.getSign())) {
                existingARequests.remove((Integer) request.getSign());
                futuresARequest.remove(request.getSign()).get();
            } else if (existingBRequests.contains(request.getSign())) {
                existingBRequests.remove((Integer) request.getSign());
                futuresBRequest.remove(request.getSign()).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        submitRequest(request);
    }


    private void submitRequest(final Request request) {
        if (request != null) {
            if (request instanceof ARequest) {
                existingARequests.add(request.getSign());
                futuresARequest.put(request.getSign(), executorARequest.submit(request));
            } else if (request instanceof BRequest) {
                existingBRequests.add(request.getSign());
                futuresBRequest.put(request.getSign(), executorBRequest.submit(request));
            }
        }
    }

}

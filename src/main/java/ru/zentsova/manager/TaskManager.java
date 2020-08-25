package ru.zentsova.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.zentsova.request.ARequest;
import ru.zentsova.request.BRequest;
import ru.zentsova.request.Request;

import java.util.*;
import java.util.concurrent.*;

public class TaskManager {

    private static final Logger log = LogManager.getLogger(TaskManager.class);

    private final ExecutorService executorARequest;
    private final ExecutorService executorBRequest;
    private final Map<Integer, Future<Integer>> futuresARequest = new HashMap<>();
    private final Map<Integer, Future<Integer>> futuresBRequest = new HashMap<>();
    /** Список из признаков запросов, которые не завершены (для A-запросов) */
    private final List<Integer> existingARequests = new LinkedList<>();
    /** Список из признаков запросов, которые не завершены (для B-запросов) */
    private final List<Integer> existingBRequests = new LinkedList<>();
    /** Общее количество параллельных потоков */
    private static final int MAX_COUNT_COMMON_THREAD_POOL = 10;

    public TaskManager() {
        this.executorARequest = Executors.newFixedThreadPool(MAX_COUNT_COMMON_THREAD_POOL / 2);
        this.executorBRequest = Executors.newFixedThreadPool(MAX_COUNT_COMMON_THREAD_POOL / 2);
    }

    /**
     * Запустить обработку
     * @param request запрос, поступающий из вне
     */
    public void run(final Request request) {
        try {
            if (existingARequests.contains(request.getX())) {
                existingARequests.remove((Integer) request.getX());
                // Если запросы имеют одинаковый признак, то вызов метода get() блокирует текущий поток,
                // и ожидает завершения вызываемого объекта
                futuresARequest.remove(request.getX()).get();
            } else if (existingBRequests.contains(request.getX())) {
                existingBRequests.remove((Integer) request.getX());
                // Если запросы имеют одинаковый признак, то вызов метода get() блокирует текущий поток,
                // и ожидает завершения вызываемого объекта
                futuresBRequest.remove(request.getX()).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        submitRequest(request);
    }

    /**
     * Добавить запрос к исполнителю
     * @param request запрос, поступающий из вне
     */
    private void submitRequest(final Request request) {
        if (request != null) {
            if (request instanceof ARequest) {
                existingARequests.add(request.getX());
                futuresARequest.put(request.getX(), executorARequest.submit(request));
            } else if (request instanceof BRequest) {
                existingBRequests.add(request.getX());
                futuresBRequest.put(request.getX(), executorBRequest.submit(request));
            }
        }
    }

    /** Завершить выполнение в потоках */
    public void awaitTerminationAfterShutdown() {
        executorARequest.shutdown();
        executorBRequest.shutdown();
        try {
            if (!executorARequest.awaitTermination(60, TimeUnit.SECONDS)) {
                executorARequest.shutdownNow();
            }
            if (!executorBRequest.awaitTermination(60, TimeUnit.SECONDS)) {
                executorBRequest.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorARequest.shutdownNow();
            executorBRequest.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}

package ru.zentsova;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.zentsova.request.ARequest;
import ru.zentsova.request.BRequest;

public class Solvo {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.run(new ARequest(1));
        taskManager.run(new BRequest(2));
        taskManager.run(new ARequest(3));
        taskManager.run(new BRequest(4));
        taskManager.run(new ARequest(4));
        taskManager.run(new BRequest(5));
    }

}
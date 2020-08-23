package ru.zentsova;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.zentsova.request.Request;


public class Solvo {

    private static final Logger log = LogManager.getLogger(Solvo.class);

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.run(new Request(1));

//        try {
//            System.out.println("attempt to shutdown executor");
//            executor.shutdown();
//            executor.awaitTermination(5, TimeUnit.SECONDS);
//        }
//        catch (InterruptedException e) {
//            System.err.println("tasks interrupted");
//        }
//        finally {
//            if (!executor.isTerminated()) {
//                System.err.println("cancel non-finished tasks");
//            }
//            executor.shutdownNow();
//            System.out.println("shutdown finished");
//        }

    }

}
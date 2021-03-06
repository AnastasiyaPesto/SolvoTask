package ru.zentsova.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.Format;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;

public class BRequest extends Request {

    private static final String S_REQUEST_NAME = "BRequest";
    private static final Logger log = LogManager.getLogger(BRequest.class);

    public BRequest(int x) {
        super(x, S_REQUEST_NAME);
    }

    @Override
    public Integer call() {
        try {
            log.info(String.format(S_REQUEST_STARTED_FMT, getName(), getX()));
            // Иммитация процесса
            TimeUnit.MILLISECONDS.sleep(getX() * 100);
            log.info(String.format(S_REQUEST_FINISHED_FMT, getName(), getX()));
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return getX();
    }

}

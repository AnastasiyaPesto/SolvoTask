package ru.zentsova.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ARequest extends Request {

    private static final String S_REQUEST_NAME = "ARequest";
    private static final Logger log = LogManager.getLogger(ARequest.class);

    public ARequest(int sign) {
        super(sign, S_REQUEST_NAME);
    }

    @Override
    public Integer call() {
        try {
            log.info(String.format("Request type is %s started; Sign is %s", getName(), getSign()));
            TimeUnit.MILLISECONDS.sleep(getSign() * 100);
            log.info(String.format("Request type is %s finished; Sign is %s", getName(), getSign()));
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return getSign();
    }

}

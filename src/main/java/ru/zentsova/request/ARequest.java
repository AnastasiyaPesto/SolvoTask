package ru.zentsova.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ARequest extends Request {

    private static final Logger log = LogManager.getLogger(ARequest.class);

    public ARequest(int sign) {
        super(sign);
    }

    @Override
    public Integer call() throws Exception {
        try {
            log.info("\"A\" request started with sign " + getSign());
            TimeUnit.SECONDS.sleep(1);
            log.info("\"A\" request finished with sign " + getSign());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return getSign();
    }

}

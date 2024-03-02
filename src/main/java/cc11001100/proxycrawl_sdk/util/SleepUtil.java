package cc11001100.proxycrawl_sdk.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author CC11001100
 */
@Slf4j
public class SleepUtil {

    public static void sleepMils(long mils) {
        try {
            TimeUnit.MILLISECONDS.sleep(mils);
        } catch (InterruptedException e) {
            log.error("sleep error", e);
        }
    }

}

package cc11001100.proxycrawl_sdk.api.status;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author CC11001100
 */
@Slf4j
@Data
public class MonitorStatusPageMain extends Thread {

    private int watchIntervalMils;

    public MonitorStatusPageMain() {
        super("monitor-status-page-thread");
    }

    @Override
    public void run() {
        StatusPage statusPage = new StatusPage();
        while(true) {
            try {
                Map<String, String> proxyCrawlStatus = statusPage.getProxyCrawlStatus();
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    public static void main(String[] args) {


    }

}

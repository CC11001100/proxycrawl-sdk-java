package cc11001100.proxycrawl_sdk.api.crawler;

import cc11001100.proxycrawl_sdk.api.crawler_retries.CrawlerListPage;
import cc11001100.proxycrawl_sdk.domain.Crawler;
import cc11001100.proxycrawl_sdk.domain.CrawlerStatus;
import cc11001100.proxycrawl_sdk.domain.GetCrawlerNameSet;
import cc11001100.proxycrawl_sdk.util.SleepUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

/**
 * 用来监视crawler的状态，确保给定crawler的状态都为启动状态
 * <p>
 * <p>
 * 1. 当短时间内出现单个的crawler一直反复重启则触发报警机制
 *
 * @author CC11001100
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class CrawlerStatusMonitorThread extends Thread {

    public static final int DEFAULT_CHECK_INTERVAL_MILS = 1000 * 60;

    private GetCrawlerNameSet getCrawlerNameSet;
    private CrawlerListPage crawlerListPage;
    private int checkIntervalMils = DEFAULT_CHECK_INTERVAL_MILS;

    public CrawlerStatusMonitorThread() {
        super("CrawlerStatusMonitorThread");
        setDaemon(true);
    }

    @Override
    public void run() {

        if (getCrawlerNameSet ==  null) {
            throw new IllegalArgumentException("getCrawlerNameSet must not null");
        }

        if (crawlerListPage == null) {
            crawlerListPage = new CrawlerListPage();
        }

        while (true) {
            try {
                internalRun();
            } catch (Exception e) {
                log.error("internal run error", e);
            } finally {
                SleepUtil.sleepMils(checkIntervalMils);
            }
        }
    }

    private void internalRun() {
        Set<String> needCheckCrawlerNameSet = getCrawlerNameSet.get();
        if (needCheckCrawlerNameSet.isEmpty()) {
            log.info("need check crawler name is empty, so break this check.");
            return;
        }
        List<Crawler> crawlerList = crawlerListPage.getCrawlerList();
        crawlerList.forEach(crawler -> {
            try {
                if (needCheckCrawlerNameSet.contains(crawler.getName())) {
                    check(crawler);
                }
            } catch (Exception e) {
                log.error("check crawler " + crawler.getName() + " error", e);
            }
        });
    }

    private void check(Crawler crawler) {
        if (crawler.getStatus() == CrawlerStatus.UNPAUSED) {
            log.info("crawler {} status is UNPAUSED, it is ok", crawler.getName());
            return;
        }

        // 点一下按钮

        // 回调方法
        
    }

    public static void main(String[] args) {



    }

}

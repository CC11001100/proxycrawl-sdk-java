package cc11001100.proxycrawl_sdk.domain;

import lombok.Data;

/**
 * @author CC11001100
 */
@Data
public class Crawler {

    private long createTimestamp;

    private CrawlerStatus status;

    private String name;
    private Integer pageInCrawler;
    private Double responseTimeSeconds;
    private Integer latencySeconds;

    // 要对crawler操作有一些参数要一并提交
    private String pauseAuthToken;
    private String purgeAuthToken;
    private String deleteAuthToken;

}

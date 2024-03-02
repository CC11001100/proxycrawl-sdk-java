package cc11001100.proxycrawl_sdk.api.crawler_retries;

import cc11001100.proxycrawl_sdk.domain.Crawler;
import cc11001100.proxycrawl_sdk.http.HttpUtil;
import cc11001100.proxycrawl_sdk.util.Env;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Collections;
import java.util.List;

/**
 * @author CC11001100
 */
@Slf4j
public class CrawlerListPage {

    private String token;

    public CrawlerListPage() {
        this(Env.GLOBAL_TOKEN);
    }

    public CrawlerListPage(String token) {
        this.token = token;
    }

    public List<Crawler> getCrawlerList() {
        String url = "https://crawler.proxycrawl.com/users/" + token + "/crawlers";
        String html = HttpUtil.INSTANCE.request(url);
        if (StringUtils.isBlank(html)) {
            log.error("get crawler list page html failed");
            return Collections.emptyList();
        }
        Document doc = Jsoup.parse(html);

        // TODO 解析列表页的爬虫信息

        return Collections.emptyList();
    }

}

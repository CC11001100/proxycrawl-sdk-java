package cc11001100.proxycrawl_sdk.api.crawler;

import cc11001100.proxycrawl_sdk.domain.Crawler;
import cc11001100.proxycrawl_sdk.http.HttpUtil;
import cc11001100.proxycrawl_sdk.parser.DomUtil;
import cc11001100.proxycrawl_sdk.util.Env;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author CC11001100
 */
@Slf4j
public class CrawlerPage {

    private String token;

    public CrawlerPage() {
        this(Env.GLOBAL_TOKEN);
    }

    public CrawlerPage(String token) {
        this.token = token;
    }

    public Crawler getCrawler(String crawlerName) {
        String html = HttpUtil.INSTANCE.request(getCrawlerPageUrl(crawlerName));
        if (StringUtils.isBlank(html)) {
            log.error("get crawler detail page html failed, crawlerName={}", crawlerName);
            return null;
        }
        Document doc = Jsoup.parse(html);

        Crawler crawler = new Crawler();
        crawler.setName(crawlerName);
        crawler.setPageInCrawler(DomUtil.nextNodeAsInteger(doc, ":containsOwn(Pages in Crawler:)"));
        crawler.setResponseTimeSeconds(DomUtil.nextNodeAsDouble(doc, ":containsOwn(Crawler callback average response time (seconds):)"));
        return crawler;
    }

    private String getCrawlerPageUrl(String crawlerName) {
        return "https://crawler.proxycrawl.com/users/" + token + "/crawlers/" + crawlerName + "?token_type=tcp";
    }

    public static void main(String[] args) {
        CrawlerPage crawlerPage = new CrawlerPage();
        System.out.println(crawlerPage.getCrawler("cron-activeAsin-crawler003"));
    }

}

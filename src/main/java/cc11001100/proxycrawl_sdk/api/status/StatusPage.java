package cc11001100.proxycrawl_sdk.api.status;

import cc11001100.proxycrawl_sdk.http.HttpUtil;
import cc11001100.proxycrawl_sdk.parser.DomUtil;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CC11001100
 */
@Slf4j
public class StatusPage {

    public static final String STATUS_PAGE_URL = "https://status.proxycrawl.com/";
    private HttpUtil httpUtil;

    public StatusPage() {
        httpUtil = new HttpUtil();
    }

    /**
     * @return 获取失败时返回empty map
     */
    public Map<String, String> getProxyCrawlStatus() {
        String html = httpUtil.request(STATUS_PAGE_URL);
        if (html == null) {
            log.error("get proxycrawl status page html failed");
            return Collections.emptyMap();
        }
        Document doc = Jsoup.parse(html);
        Elements operationalElements = doc.select(".systems-container .operational");
        if (operationalElements.isEmpty()) {
            log.error("not found operational");
            return Collections.emptyMap();
        }
        return operationalElements
                .stream()
                .map(e -> {
                    Tuple2<String, String> t = Tuple.of(DomUtil.firstOwnText(e, ".system-title"), DomUtil.firstOwnText(e, ".system-status > span"));
                    System.out.println(t);
                    return t;
                })
                .filter(x -> !StringUtils.isAnyBlank(x._1, x._2)).collect(Collectors.toMap(x -> x._1, x -> x._2));
    }

    public static void main(String[] args) {

        StatusPage statusPage = new StatusPage();
        System.out.println(statusPage.getProxyCrawlStatus());

    }

}

package cc11001100.proxycrawl_sdk.http;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * @author CC11001100
 */
@Slf4j
@Data
public class HttpUtil {

    public static final String DEFAULT_USER_AGENT = "proxycrawl-sdk-java-by-cc11001100";

    public static final HttpUtil INSTANCE = new HttpUtil();

    private ConnectionSettings defaultConnectionSettings;
    private ResponseProcessor defaultResponseProcessor;
    private int tryTimes = 10;

    public <T> T request(String url) {
        return request(url, null, null);
    }

    public <T> T request(String url, ConnectionSettings connectionSettings, ResponseProcessor<T> responseProcessor) {
        for (int i = 1; i <= tryTimes; i++) {
            try {
                Connection connect = Jsoup.connect(url)
                        .ignoreHttpErrors(true)
                        .ignoreContentType(true)
                        .timeout(1000 * 60)
                        .userAgent(DEFAULT_USER_AGENT);

                if (defaultConnectionSettings != null) {
                    defaultConnectionSettings.setting(connect);
                }
                if (connectionSettings != null) {
                    connectionSettings.setting(connect);
                }

                Connection.Response response = connect.execute();
                if (responseProcessor != null) {
                    return responseProcessor.process(response);
                }
                if (defaultResponseProcessor != null) {
                    return (T) defaultResponseProcessor.process(response);
                }
                return (T) DefaultResponseProcessor.INSTANCE.process(response);
            } catch (Exception e) {
                log.error("error, url=" + url, e);
            }
        }
        return null;
    }

}

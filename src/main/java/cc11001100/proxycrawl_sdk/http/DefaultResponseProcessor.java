package cc11001100.proxycrawl_sdk.http;

import org.jsoup.Connection;

/**
 * @author CC11001100
 */
public class DefaultResponseProcessor implements ResponseProcessor<String> {

    public static final DefaultResponseProcessor INSTANCE = new DefaultResponseProcessor();

    @Override
    public String process(Connection.Response response) {
        return response != null ? response.body() : null;
    }

}

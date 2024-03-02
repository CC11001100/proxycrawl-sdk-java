package cc11001100.proxycrawl_sdk.http;

import org.jsoup.Connection;

/**
 * @author CC11001100
 */
@FunctionalInterface
public interface ResponseProcessor<T> {

    T process(Connection.Response response);

}

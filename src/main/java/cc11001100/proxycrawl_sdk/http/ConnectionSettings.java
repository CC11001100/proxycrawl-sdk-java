package cc11001100.proxycrawl_sdk.http;

import org.jsoup.Connection;

/**
 * @author CC11001100
 */
@FunctionalInterface
public interface ConnectionSettings {

    void setting(Connection connection);

}

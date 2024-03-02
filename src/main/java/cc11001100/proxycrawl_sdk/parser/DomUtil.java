package cc11001100.proxycrawl_sdk.parser;

import org.jsoup.nodes.Element;

/**
 * @author CC11001100
 */
public class DomUtil {

    public static String firstOwnText(Element root, String cssSelector) {
        Element e = root.selectFirst(cssSelector);
        return e != null ? e.ownText() : null;
    }

    public static Integer nextNodeAsInteger(Element root, String cssSelector) {
        Element e = root.selectFirst(cssSelector);
        if (e == null) {
            return null;
        }
        String s = e.nextSibling().toString();
        try {
            return Integer.valueOf(s.replace(",", "").trim());
        } catch (Exception ignored) {
        }
        return null;
    }


    public static Double nextNodeAsDouble(Element root, String cssSelector) {
        Element e = root.selectFirst(cssSelector);
        if (e == null) {
            return null;
        }
        String s = e.nextSibling().toString();
        try {
            return Double.valueOf(s.replace(",", "").trim());
        } catch (Exception ignored) {
        }
        return null;
    }

}

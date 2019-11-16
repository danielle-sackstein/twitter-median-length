package twitter.median.length;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class includes properties which the application needs from the user, such as keys / tokens for communication with
 * twitter, and maxTweetsCount which is the number of tweets the user wants to search for.
 */
@Component
public class Configuration {

    @Value("${twitter.consumerKey}")
    private String consumerKey;
    public String getConsumerKey() {
        return consumerKey;
    }

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;
    public String getConsumerSecret() {
        return consumerSecret;
    }

    @Value("${twitter.accessToken}")
    private String accessToken;
    public String getAccessToken() {
        return accessToken;
    }

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;
    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    @Value("${maxTweetsCount}")
    private int maxTweetsCount;
    int getMaxTweetsCount() {
        return maxTweetsCount;
    }
}

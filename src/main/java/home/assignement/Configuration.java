package home.assignement;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

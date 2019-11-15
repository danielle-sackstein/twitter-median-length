package home.assignement;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterService {

    private final Twitter twitter;
    private final int maxCount = 1000;

    public TwitterService() {
        var configuration = new ConfigurationBuilder()
                .setOAuthConsumerKey("UblLQLjc3032cVDsHmWv8kJSX")
                .setOAuthConsumerSecret("AVl3TauMVOvVbeOQzaHTJcFijIR0wCJE5i9VDU72o7yl0zI6wz")
                .setOAuthAccessToken("1195009043254919173-lHEF1qfz3SxK588vlDENrqV3i5dFsY")
                .setOAuthAccessTokenSecret("7ouIzhLPAbH1YDe5ooOlh21OaT2vN1U2wxKqbKkTbdSQQ")
                .build();
        twitter = new TwitterFactory(configuration).getInstance();
    }

    public void searchTweets(String query) throws TwitterException {
        List<Status> tweets = new ArrayList<>();

        Query requestQuery = new Query(query);
        while (tweets.size() < maxCount) {
            QueryResult queryResult = twitter.search(requestQuery);
            tweets.addAll(queryResult.getTweets()); // Not ordered

            requestQuery = queryResult.nextQuery();
        }
        tweets = tweets.subList(0, maxCount);
    }
}

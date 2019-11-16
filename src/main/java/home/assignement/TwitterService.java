package home.assignement;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterService {

    private final Twitter twitter;

    public TwitterService(
        String consumerKey,
        String consumerSecret,
        String accessToken,
        String accessTokenSecret
    ) {
        var configuration = new ConfigurationBuilder()
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret)
                .build();
        twitter = new TwitterFactory(configuration).getInstance();
    }

    public int getMedian(String query, int maxTweetCount) throws TwitterException {
        return
            getMedianValue(
                getSortedTweetsLengths(
                    getTweets(query, maxTweetCount)
            ));
    }

    public List<Status> getTweets(String query, int maxTweetCount) throws TwitterException {
        return getTweets(query, maxTweetCount, 100);
    }

    public List<Status> getTweets(String query, int maxTweetCount, int maxRequestCount) throws TwitterException {
        Query requestQuery = new Query(query).count(maxRequestCount);

        List<Status> tweets = new ArrayList<>();

        while (tweets.size() < maxTweetCount) {
            QueryResult queryResult = searchTweets(requestQuery);
            tweets.addAll(queryResult.getTweets());

            if (!queryResult.hasNext()) {
                return tweets;
            }
            requestQuery = queryResult.nextQuery();
        }
        if (tweets.size() > maxTweetCount) {
            return tweets.subList(0, maxTweetCount);
        }

        return tweets;
    }

    private QueryResult searchTweets(Query requestQuery) throws TwitterException {
        return twitter.search(requestQuery);
    }

    public List<Integer> getSortedTweetsLengths(List<Status> tweets) {
        return tweets.stream()
            .map(tweet -> tweet.getText().length())
            .sorted()
            .collect(Collectors.toList());
    }

    public int getMedianValue(List<Integer> values) {
        int size = values.size();
        if (size == 0) {
            return 0;
        }
        int middle = size / 2;

        int hi = values.get(middle);
        if (size % 2 == 0) {
            int lo = values.get(middle - 1);
            return average(lo, hi);
        }
        return hi;
    }

    private int average(int lhs, int rhs) {
        return (lhs + rhs) / 2;
    }
}

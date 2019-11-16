package twitter.median.length;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is is responsible for communicating with twitter.
 * It creates a Twitter object (using tokens and keys) which enables it to communicate with twitter and search for
 * tweets that include a specific term.
 */
public class TwitterService {

    private final Twitter twitter;

    /**
     *  Creates an instance of Twitter4J's Twitter (using authorization credentials) which enables it to communicate with the Twitter Web API
     */
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

    /**
     * This method receives a query, i.e a term from the users GET request, searches the last maxTweetCount tweets,
     * sorts them by their lengths, and calculates the median length.
     * @param query the term which the user wants to look for in twitters tweets
     * @param maxTweetCount the number of tweets the user wants to search for (in our case - 1000)
     * @return The median length of the tweets.
     * @throws TwitterException
     */
    public int getMedian(String query, int maxTweetCount) throws TwitterException {
        return
            getMedianValue(
                getSortedTweetsLengths(
                    getTweets(query, maxTweetCount)
            ));
    }

    /**
     * This method receives a term given by the user, and by using the twitter field, it searches for the last
     * 100 tweets. This is the maximum tweets we can search for at once. The method below enables calling
     * this method with a lower number.
     * @param query the term which the user wants to look for in twitters tweets
     * @param maxTweetCount the number of tweets the user wants to search for (in our case - 1000)
     * @return List of the tweets (Status)
     * @throws TwitterException
     */
    public List<Status> getTweets(String query, int maxTweetCount) throws TwitterException {
        return getTweets(query, maxTweetCount, 100);
    }

    /**
     * This method receives a term given by the user, and by using the twitter field, it searches for the last
     * maxTweetCount tweets
     * @param query the term which the user wants to look for in twitters tweets
     * @param maxTweetCount the number of tweets the user wants to search for (in our case - 1000)
     * @param maxRequestCount The max requests we ask twitter to search for, the maximum is 100 (see
     *                        getTweets(String query, int maxTweetCount) above.
     * @return List of the tweets (Status)
     * @throws TwitterException
     */
    public List<Status> getTweets(String query, int maxTweetCount, int maxRequestCount) throws TwitterException {

        /* Creates a new query object. Every time getTweets() will be called, the method will search for maxRequestCount
         last tweets published with the provided 'query'. */
        Query requestQuery = new Query(query).count(maxRequestCount);

        List<Status> tweets = new ArrayList<>();

        /* Continue adding tweets to the list of tweets until its size is maxTweetCount*/
        while (tweets.size() < maxTweetCount) {
            QueryResult queryResult = twitter.search(requestQuery);
            tweets.addAll(queryResult.getTweets());

            /* Handles the case in which there are no more tweets published with the provided term */
            if (!queryResult.hasNext()) {
                return tweets;
            }
            requestQuery = queryResult.nextQuery();
        }

        /* Handles the case in which the maxTweetCount % 100 != 0, so there are more tweets than asked */
        if (tweets.size() > maxTweetCount) {
            return tweets.subList(0, maxTweetCount);
        }

        return tweets;
    }

    /**
     * Sorts the tweets list by their length.
     * @param tweets tweets that need to be sorted
     * @return Sorted tweets
     */
    public List<Integer> getSortedTweetsLengths(List<Status> tweets) {
        return tweets.stream()
            .map(tweet -> tweet.getText().length())
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * Given a list of values, this method calculates the median.
     * @param values values that the median value of should be found
     * @return the median
     */
    public int getMedianValue(List<Integer> values) {
        int size = values.size();
        if (size == 0) {
            return 0;
        }
        int middle = size / 2;

        int hi = values.get(middle);

        /* Handles the case in which the size of the array is even, and returns the average of the middle values */
        if (size % 2 == 0) {
            int lo = values.get(middle - 1);
            return average(lo, hi);
        }
        return hi;
    }

    /**
     * Calculates the average of two numbers.
     * @param lhs
     * @param rhs
     * @return
     */
    private int average(int lhs, int rhs) {
        return (lhs + rhs) / 2;
    }
}

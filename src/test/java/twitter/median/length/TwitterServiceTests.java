package twitter.median.length;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import twitter4j.TwitterException;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterServiceTests {

    @Autowired
    private Configuration configuration;
    private TwitterService twitterService;

    @Before
    public void setUp() {
        this.twitterService = new TwitterService(
                configuration.getConsumerKey(),
                configuration.getConsumerSecret(),
                configuration.getAccessToken(),
                configuration.getAccessTokenSecret()
        );
    }

    @Test
    public void testMaxCount() throws TwitterException {
        int maxTweetCount = 199;
        var tweets = twitterService.getTweets("Trump", maxTweetCount);

        Assert.assertTrue(tweets.size() <= maxTweetCount);
    }

    @Test
    public void testMaxCountEqual() throws TwitterException {
        int maxTweetCount = 1000;
        var tweets = twitterService.getTweets("Trump", maxTweetCount);

        Assert.assertEquals(tweets.size(), maxTweetCount);
    }

    @Test
    public void testDanielleDoesntExist() throws TwitterException {
        int maxTweetCount = 1000;
        var median = twitterService.getMedian("Danielle Sackstein", maxTweetCount);

        Assert.assertEquals(0, median);
    }

    @Test
    public void testTrumpExists() throws TwitterException {
        int maxTweetCount = 1000;
        var median = twitterService.getMedian("Trump", maxTweetCount);

        Assert.assertNotEquals(0, median);
    }

    @Test
    public void testMedianRange() throws TwitterException {
        int maxTweetCount = 3;
        List<Integer> sortedTweetsLengths = twitterService
            .getSortedTweetsLengths(
                twitterService.getTweets("Trump", maxTweetCount));

        int minTweetLength = sortedTweetsLengths.get(0);
        int maxTweetLength = sortedTweetsLengths.get(sortedTweetsLengths.size() - 1);
        int median = twitterService.getMedianValue(sortedTweetsLengths);
        Assert.assertTrue(median <= maxTweetLength && median >= minTweetLength);
    }

    @Test
    public void testTweetsNotEqual() throws TwitterException {
        int maxTweetCount = 2;
        var tweets = twitterService.getTweets("Trump", maxTweetCount, 1);
        Assert.assertNotEquals(tweets.get(0).getId(), tweets.get(tweets.size() - 1).getId());
    }

}

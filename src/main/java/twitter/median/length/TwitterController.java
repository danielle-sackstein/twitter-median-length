package twitter.median.length;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

/**
 * The REST controller of the program.
 * Creates a TwitterService in the constructor which helps links the program to twitter.
 * The class includes a GET endpoint (GetMedian) which receives a request in the following format: "/median?q=<term>".
 */
@RestController
public class TwitterController {

    private final Configuration configuration;
    private final TwitterService twitterService;

    /**
     * Constructor called automatically by Spring
     * @param configuration Configuration given from Spring
     */
    @Autowired
    public TwitterController(Configuration configuration) {
        this.configuration = configuration;

        this.twitterService = new TwitterService(
            configuration.getConsumerKey(),
            configuration.getConsumerSecret(),
            configuration.getAccessToken(),
            configuration.getAccessTokenSecret()
        );
    }

    /**
     * This method  is a GET end point (GetMedian) which receives a request in the following format: "/median?q=<term>"
     * @param query the term which the user wants to look for in twitters tweets
     * @return the median of the lengths of the maxTweetsCount last tweets.
     * @throws TwitterException
     */
    @GetMapping("/median")
    public int GetMedian(@RequestParam("q") String query) throws TwitterException {
        return twitterService.getMedian(query, configuration.getMaxTweetsCount());
    }
}

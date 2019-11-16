package home.assignement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
public class WebController {

    private final Configuration configuration;
    private final TwitterService twitterService;

    @Autowired
    public WebController(Configuration configuration) {
        this.configuration = configuration;

        this.twitterService = new TwitterService(
            configuration.getConsumerKey(),
            configuration.getConsumerSecret(),
            configuration.getAccessToken(),
            configuration.getAccessTokenSecret()
        );
    }

    @GetMapping("/median")
    public int GetMedian(@RequestParam("q") String query) throws TwitterException {
        return twitterService.getMedian(query, configuration.getMaxTweetsCount());
    }
}

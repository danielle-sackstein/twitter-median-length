package home.assignement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
public class WebController {

    private final TwitterService twitterService = new TwitterService();

    @GetMapping("/median")
    public void GetMedian(@RequestParam("q") String query) throws TwitterException {
        twitterService.searchTweets(query);
    }
}

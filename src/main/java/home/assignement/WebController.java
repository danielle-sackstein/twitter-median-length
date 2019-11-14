package home.assignement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/median")
    public void GetMedian(@RequestParam("q") String query){
        System.out.println(query);
    }
}

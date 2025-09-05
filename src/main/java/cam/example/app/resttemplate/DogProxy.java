package cam.example.app.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DogProxy {

    @Autowired
    RestTemplate restTemplate;

    //    https://dog.ceo/api/breed/hound/images/random/3
    public String makeDogRequest(Integer limit) {
        String uri = "https://dog.ceo/api/breed/hound/images/random/" + limit;
        ResponseEntity<String> exchange = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                String.class
        );
        return exchange.getBody();
    }
}

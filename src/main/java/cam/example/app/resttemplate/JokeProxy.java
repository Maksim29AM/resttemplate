package cam.example.app.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JokeProxy {

    @Autowired
    RestTemplate restTemplate;


    @Value("${joke.service.url}")
    String url;

    public String makeJokeRequest(String category) {
        String uri = url + "/joke/" + category;
        ResponseEntity<String> exchange = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                String.class
        );
        return exchange.getBody();
    }
}

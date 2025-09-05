package cam.example.app.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public JokeResponse makeJokeRequest(String category) throws JsonProcessingException {
        String uri = url + "/joke/" + category;
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                String.class
        );
        String json = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, JokeResponse.class);
    }
}

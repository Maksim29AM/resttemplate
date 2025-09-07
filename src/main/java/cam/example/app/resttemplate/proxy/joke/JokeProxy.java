package cam.example.app.resttemplate.proxy.joke;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class JokeProxy {

    @Autowired
    RestTemplate restTemplate;

    Logger log = getLogger(JokeProxy.class);

    @Value("${joke.service.url}")
    String url;

    public String makeJokeRequest(String category) {
        String uri = url + "/joke/" + category;
        return makeRequest(uri);
    }

    private String makeRequest(String uri) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exceptions) {
            log.error("{} {}", exceptions.getStatusText(), exceptions.getStatusCode().value());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }
}

package cam.example.app.resttemplate;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class SampleShawnMendesServerProxy {

    @Autowired
    RestTemplate restTemplate;

    Logger log = getLogger(SampleShawnMendesServerProxy.class);

    @Value("${sample-shawn-mendes-server.service.url}")
    String url;

    @Value("${sample-shawn-mendes-server.service.port}")
    int port;

    public String makePostRequest() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .port(port)
                .host(url)
                .path("/shawn/songs");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestId", "qwe");
        SampleShawnMendesRequest requestBody = new SampleShawnMendesRequest("MySong!@!@!!");
        HttpEntity<SampleShawnMendesRequest> entity = new HttpEntity<>(requestBody, httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.POST,
                    entity,
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

    public String makeGetRequest() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .port(port)
                .host(url)
                .path("/shawn/songs");

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
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

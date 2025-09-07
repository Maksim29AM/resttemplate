package cam.example.app.resttemplate.proxy.joke;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JokeResponse(String category, String type, String setup, String delivery, String joke) {
}

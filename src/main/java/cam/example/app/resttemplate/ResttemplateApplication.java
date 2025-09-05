package cam.example.app.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ResttemplateApplication {

    @Autowired
    JokeProxy jokeProxy;

    public static void main(String[] args) {
        SpringApplication.run(ResttemplateApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToJoke() throws JsonProcessingException {
        String json = jokeProxy.makeJokeRequest("any");

        if (json != null) {
            JokeResponse jokeResponse = mapJsonToJokeResponse(json);
            System.out.println("Category: " + jokeResponse.category());
            String joke = jokeResponse.type().equals("twopart") ? "Setup: " + jokeResponse.setup() + "\nDelivery: " + jokeResponse.delivery() : "Joke: " + jokeResponse.joke();
            System.out.println(joke);
        }
    }

    private JokeResponse mapJsonToJokeResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, JokeResponse.class);
    }
}

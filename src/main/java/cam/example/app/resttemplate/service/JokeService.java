package cam.example.app.resttemplate.service;

import cam.example.app.resttemplate.proxy.joke.JokeProxy;
import cam.example.app.resttemplate.proxy.joke.JokeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeService {

    @Autowired
    JokeProxy jokeClient;


    public void doSth() throws JsonProcessingException {
        String json = jokeClient.makeJokeRequest("any");
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

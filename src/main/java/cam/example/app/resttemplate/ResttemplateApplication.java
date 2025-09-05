package cam.example.app.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ResttemplateApplication {

    //    @Autowired
//    DogProxy dog;
    @Autowired
    JokeProxy jokeProxy;

    public static void main(String[] args) {
        SpringApplication.run(ResttemplateApplication.class, args);
    }

//    @EventListener(ApplicationStartedEvent.class)
//    public void makeRequestToRandomDog() {
//        String response = dog.makeDogRequest(5);
//        System.out.println(response);
//    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToJoke() throws JsonProcessingException {
        JokeResponse response = jokeProxy.makeJokeRequest("any");

        System.out.println("Category: " + response.category());

        String joke = response.type().equals("twopart") ? "Setup: " + response.setup() + "\nDelivery: " + response.delivery() : "Joke: " + response.joke();
        System.out.println(joke);
    }
}

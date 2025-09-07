package cam.example.app.resttemplate;


import cam.example.app.resttemplate.service.JokeService;
import cam.example.app.resttemplate.service.ShawnMendesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ResttemplateApplication {

    @Autowired
    JokeService jokeService;
    @Autowired
    ShawnMendesService shawnMendesService;


    public static void main(String[] args) {
        SpringApplication.run(ResttemplateApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() throws JsonProcessingException {
        jokeService.doSth();
        shawnMendesService.doSth();
    }
}

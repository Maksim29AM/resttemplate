package cam.example.app.resttemplate.proxy.sampleshawnmendes;

import java.util.List;

public record SampleServerShawnMendesResponse(
        String message,
        List<String> songsName
) {
}

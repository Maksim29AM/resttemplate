package cam.example.app.resttemplate.service;

import cam.example.app.resttemplate.proxy.sampleshawnmendes.SampleServerShawnMendesResponse;
import cam.example.app.resttemplate.proxy.sampleshawnmendes.SampleShawnMendesServerProxy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShawnMendesService {

    @Autowired
    SampleShawnMendesServerProxy sampleShawnMendesServerClient;


    public void doSth() throws JsonProcessingException {
        String postJsonSampleShawnMendesServer = sampleShawnMendesServerClient.makePostRequest();
        String getJsonSampleShawnMendesServer = sampleShawnMendesServerClient.makeGetRequest();
        if (postJsonSampleShawnMendesServer != null) {
            SampleServerShawnMendesResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(postJsonSampleShawnMendesServer);
            System.out.println(sampleShawnMendesResponse);
        }
        if (getJsonSampleShawnMendesServer != null) {
            SampleServerShawnMendesResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(getJsonSampleShawnMendesServer);
            System.out.println(sampleShawnMendesResponse);
        }
        sampleShawnMendesServerClient.makeDeleteRequest("0");
        String getJsonSampleShawnMendesServer2 = sampleShawnMendesServerClient.makeGetRequest();
        if (getJsonSampleShawnMendesServer2 != null) {
            SampleServerShawnMendesResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(getJsonSampleShawnMendesServer2);
            System.out.println(sampleShawnMendesResponse);
        }
    }

    private SampleServerShawnMendesResponse mapJsonToSampleShawnMendesResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, SampleServerShawnMendesResponse.class);
    }
}

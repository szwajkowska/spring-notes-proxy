package notes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class NotesClient {
    private final RestTemplate restTemplate;

    public NotesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Note> getNotes(){
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/notes", Note[].class));
    }

    public ResponseEntity<String> add(BodyRequest body) {
        BodyRequest bodyRequest = new BodyRequest("Prefix Anny: " + body.getText());
        try {
            return restTemplate.postForEntity("http://localhost:8080/notes", bodyRequest, String.class);
        } catch (HttpStatusCodeException exception){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<String>(exception.getResponseBodyAsString(),
                httpHeaders, exception.getStatusCode());
        }
    }

    public Note getNoteById(Long id) {
        try {
            return restTemplate.getForObject("http://localhost:8080/notes/{id}", Note.class, id);
        } catch (HttpStatusCodeException e){
            return null;
        }
    }



}

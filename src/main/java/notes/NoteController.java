package notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private static final Logger log = LoggerFactory.getLogger(NoteController.class);

    private final NotesClient notesClient;

    public NoteController(NotesClient notesClient) {
        this.notesClient = notesClient;
    }

//    @PostMapping
//    public ResponseEntity<Void> addNote(@RequestBody @Valid BodyRequest body) {
//        long id = notesClient.add(body.getText());
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Ania", "Bania");
//        httpHeaders.setLocation(URI.create("/notes/" + id));
//        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
////        return ResponseEntity.created(URI.create("/notes/" + id)).build();
//    }
//
    @GetMapping
    public Collection<Note> showList() {
        return notesClient.getNotes();
    }

    @PostMapping
    public ResponseEntity<String> addNote(@RequestBody BodyRequest body) {
        return notesClient.add(body);

    }
//
    @GetMapping("/{id}")
    public ResponseEntity<Note> showNote(@PathVariable Long id) {

        Note note = notesClient.getNoteById(id);
        if (note == null) {
            ResponseEntity<Note> build = new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
            return build;
        }
        return new ResponseEntity<Note>(note, HttpStatus.OK);

    }

//    Note note = notesRepository.findById(id);
//        if (note == null) {
//        ResponseEntity<Note> build = new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
//        return build;
//    }
//        return new ResponseEntity<Note>(note, HttpStatus.OK);
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id){
//        notesClient.deleteNote(id);
//    }
//
//
//    @PutMapping("/{id}")
//    public void change(@RequestBody BodyRequest body, @PathVariable Long id){
//        notesClient.changeBody(id, body.getText());
//
//
//    }
//


}
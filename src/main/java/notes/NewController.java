package notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewController {


    private final Repository repository;

    private final List<Repository> repositories;

    @Autowired
    //@Qualifier(value = "greenRepository")
    public NewController( Repository repository, List<Repository> repositories) {
        this.repository = repository;
        this.repositories = repositories;
    }

    @GetMapping("/test")
    public String get(){
       return repository.find("");
    }

    @GetMapping("/list")
    public String list(){
       return repositories.stream().map(r -> r.find("")).collect(Collectors.joining(", "));
    }
}

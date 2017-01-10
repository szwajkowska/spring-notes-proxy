package notes;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
@ConditionalOnBean(MyBean.class)
@ConditionalOnProperty(name = "ania.green", havingValue = "false")
public class RedRepository implements Repository {

    public String find(String id) {
        return "red";
    }
}

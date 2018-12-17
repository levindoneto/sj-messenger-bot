package we.poc.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.Map;

@Data
@Builder
@KeySpace("userContexts")
@NoArgsConstructor
@AllArgsConstructor
public class UserContext {
    @Id
    private String id;
    private Map<String, Object> chatContext;
    private String firstName;
}

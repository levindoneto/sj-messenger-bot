package we.poc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we.poc.data.UserContext;
import we.poc.data.repository.UserContextRepository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserContextService {

    @Autowired
    private UserContextRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public UserContext find(String id){
        return this.repository
                   .findById(id)
                   .orElseGet(() -> this.create(id));
    }

    private UserContext create(String id){
        return this.repository.save(UserContext.builder()
                                            .chatContext(new HashMap<>())
                                            .id(id)
                                            .build());
    }

    public void override(String id, Map<String, Object> context) {
        this.repository
            .findById(id)
            .ifPresent(userContext -> {
                userContext.setChatContext(context);
                this.repository.save(userContext);
                try {
                    log.error("UserContextService.override={}", this.objectMapper.writeValueAsString(userContext));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
    }

    public void update(UserContext userContext) {
        this.repository.save(userContext);
    }

}

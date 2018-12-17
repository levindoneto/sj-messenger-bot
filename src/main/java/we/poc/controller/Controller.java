package we.poc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import we.poc.facade.Facade;
import we.poc.models.Callback;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private Facade facade;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/subscribe")
    public final void subscribeAppToPage() {
        this.facade.subscribe();
    }

    @GetMapping("/webhook")
    public final ResponseEntity verification(@RequestParam("hub.mode") String mode,
                                             @RequestParam("hub.verify_token") String verifyToken,
                                             @RequestParam("hub.challenge") String challenge) {
        return this.facade.isValidToken(mode, verifyToken, challenge);
    }

    @ResponseBody
    @PostMapping("/webhook")
    public final ResponseEntity conversation(@RequestBody Callback callback) throws JsonProcessingException {
        log.error("Controller.request={}", this.objectMapper.writeValueAsString(callback));
        try{
            return this.facade.conversation(callback);
        }catch (HttpClientErrorException e){
            log.error("Controller.response={}", e.getResponseBodyAsString());
            log.error("Controller.exception={}", e);
        }
        log.error("");
        log.error("");
        return ResponseEntity.badRequest().build();
    }
}

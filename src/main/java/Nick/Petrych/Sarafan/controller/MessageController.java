package Nick.Petrych.Sarafan.controller;

import Nick.Petrych.Sarafan.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "first message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "third message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("text", "fourth message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "5");
            put("text", "fifth message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "6");
            put("text", "sixth message");
        }});

    }};

    /**
     * GetMapping use method Get
     */
    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
        /**
         * Return collection "messages"
         */
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMassage(id);
    }

    private Map<String, String> getMassage(@PathVariable String id) {
        return messages.stream()
                .filter(messages -> messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    /**
     * create message and put it in collection
     */
    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMassage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);
        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMassage(id);
        messages.remove(message);
    }
}

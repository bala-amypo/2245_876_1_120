import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;

@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {

    @Autowired
    ApiKeyService ser;

    @PostMapping
    public ApiKey createApiKey(@RequestBody ApiKey key) {
        return ser.createApiKey(key);
    }

    @PutMapping("/{id}")
    public ApiKey updateApiKey(@PathVariable Long id, @RequestBody ApiKey key) {
        return ser.updateApiKey(id, key);
    }

    @GetMapping("/{id}")
    public ApiKey getApiKeyById(@PathVariable Long id) {
        return ser.getApiKeyById(id);
    }

    @GetMapping
    public List<ApiKey> getAllApiKeys() {
        return ser.getAllApiKeys();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateApiKey(@PathVariable Long id) {
        ser.deactivateApiKey(id);
        return "API Key Deactivated";
    }
}

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    @Autowired
    KeyExemptionService ser;

    @PostMapping
    public KeyExemption createExemption(@RequestBody KeyExemption exemption) {
        return ser.createExemption(exemption);
    }

    @PutMapping("/{id}")
    public KeyExemption updateExemption(@PathVariable Long id, @RequestBody KeyExemption exemption) {
        return ser.updateExemption(id, exemption);
    }

    @GetMapping("/key/{keyId}")
    public KeyExemption getByKey(@PathVariable Long keyId) {
        return ser.getExemptionByKey(keyId);
    }

    @GetMapping
    public List<KeyExemption> getAll() {
        return ser.getAllExemptions();
    }
}

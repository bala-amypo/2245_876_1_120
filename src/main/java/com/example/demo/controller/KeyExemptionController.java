@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @PostMapping(consumes = "application/json")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Create a key exemption",
        required = true
    )
    public KeyExemption createExemption(
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {

        return exemptionService.createExemption(exemption);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public KeyExemption updateExemption(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {

        return exemptionService.updateExemption(id, exemption);
    }

    @GetMapping("/key/{keyId}")
    public Optional<KeyExemption> getExemptionByKey(
            @PathVariable Long keyId) {

        return exemptionService.getExemptionByKey(keyId);
    }

    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return exemptionService.getAllExemptions();
    }
}

@RestController
@RequestMapping("/api/enforcements")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService service;

    public RateLimitEnforcementController(RateLimitEnforcementService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody RateLimitEnforcement enforcement) {
        service.createEnforcement(enforcement);
    }

    @GetMapping("/{id}")
    public RateLimitEnforcement getById(@PathVariable Long id) {
        return service.getEnforcementById(id);
    }

    @GetMapping("/key/{keyId}")
    public List<RateLimitEnforcement> getByKey(@PathVariable Long keyId) {
        return service.getEnforcementsForKey(keyId);
    }
}

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository exemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(
            KeyExemptionRepository exemptionRepository,
            ApiKeyRepository apiKeyRepository) {
        this.exemptionRepository = exemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {

        // 1️⃣ Validate extension limit
        if (exemption.getTemporaryExtensionLimit() != null &&
            exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Temporary extension limit must be >= 0");
        }

        // 2️⃣ Validate expiry
        if (exemption.getValidUntil() == null ||
            exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }

        // 3️⃣ Fetch ApiKey properly (IMPORTANT)
        Long apiKeyId = exemption.getApiKey().getId();

        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        // 4️⃣ Attach managed ApiKey
        exemption.setApiKey(apiKey);

        return exemptionRepository.save(exemption);
    }
}

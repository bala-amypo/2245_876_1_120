@Entity
@Table(name = "api_usage_logs")
public class ApiUsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public ApiUsageLog() {}

    public ApiUsageLog(ApiKey apiKey, String endpoint, LocalDateTime timestamp) {
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.timestamp = timestamp;
    }

    // ===== GETTERS =====
    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public String getEndpoint() { return endpoint; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // ===== SETTERS =====
    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    // ✅ ONLY JSON setter
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // ❌ HIDDEN FROM JSON (tests still use it)
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setTimestamp(Instant timestamp) {
        this.timestamp = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
    }
}


package basvuru_asistani.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "consumer_cases")
public class ConsumerCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "requested_remedy", nullable = false)
    private RequestedRemedy requestedRemedy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CaseStatus status;

    @Column(name = "dispute_amount", precision = 12, scale = 2)
    private BigDecimal disputeAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Transient
    private Set<DocumentType> requiredDocuments = new HashSet<>();

    @Transient
    private Set<DocumentType> uploadedDocuments = new HashSet<>();


    // Hibernate/JPA için boş constructor
    protected ConsumerCase() {
    }


    // Eski constructor: Mevcut CaseService için geçici olarak duruyor
    public ConsumerCase(Long id, String description,
                        RequestedRemedy requestedRemedy) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID pozitif olmalı");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Açıklama boş olamaz");
        }

        if (requestedRemedy == null) {
            throw new IllegalArgumentException("İstenen çözüm boş olamaz");
        }

        this.id = id;
        this.description = description;
        this.requestedRemedy = requestedRemedy;
        this.status = CaseStatus.DRAFT;
    }


    // Yeni constructor: Gerçek proje için
    public ConsumerCase(Long userId,
                        String description,
                        RequestedRemedy requestedRemedy,
                        BigDecimal disputeAmount) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException(
                    "Geçerli kullanıcı kimliği gerekli"
            );
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "Açıklama boş olamaz"
            );
        }

        if (requestedRemedy == null) {
            throw new IllegalArgumentException(
                    "İstenen çözüm boş olamaz"
            );
        }

        this.userId = userId;
        this.description = description;
        this.requestedRemedy = requestedRemedy;
        this.disputeAmount = disputeAmount;
        this.status = CaseStatus.DRAFT;
    }


    // JPA yeni kayıt oluştururken çalışır
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }


    // JPA mevcut kaydı güncellerken çalışır
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public void uploadDocument(DocumentType type) {
        uploadedDocuments.add(type);
    }


    public Map<DocumentType, Boolean> getChecklist() {
        Map<DocumentType, Boolean> checklist = new HashMap<>();

        for (DocumentType documentType : requiredDocuments) {
            checklist.put(
                    documentType,
                    uploadedDocuments.contains(documentType)
            );
        }

        return checklist;
    }


    public int calculateCompletionPercentage() {
        if (requiredDocuments.isEmpty()) {
            return 100;
        }

        int uploadedRequired = 0;

        for (DocumentType documentType : requiredDocuments) {
            if (uploadedDocuments.contains(documentType)) {
                uploadedRequired++;
            }
        }

        return uploadedRequired * 100 / requiredDocuments.size();
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setRequestedRemedy(RequestedRemedy requestedRemedy) {
        this.requestedRemedy = requestedRemedy;
    }


    public void addRequiredDocument(DocumentType type) {
        requiredDocuments.add(type);
    }


    public Long getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public RequestedRemedy getRequestedRemedy() {
        return requestedRemedy;
    }


    public CaseStatus getStatus() {
        return status;
    }
}

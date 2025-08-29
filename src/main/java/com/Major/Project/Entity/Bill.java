package com.Major.Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "bills",
        indexes = {
                @Index(name = "idx_patient", columnList = "patient_id"),
                @Index(name = "idx_billingDate", columnList = "billing_date")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 255)
    private String description;
  
    @NotNull(message = "Billing date cannot be null")
    @Column(name = "billing_date", nullable = false)
    private LocalDateTime billingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnoreProperties("billings")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff handledBy;

    // Auditing fields
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Enum for payment status
    public enum PaymentStatus {
        PENDING, PAID, FAILED, REFUNDED
    }
}

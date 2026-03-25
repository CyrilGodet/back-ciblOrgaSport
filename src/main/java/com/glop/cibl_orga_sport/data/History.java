package com.glop.cibl_orga_sport.data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class History{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistory;

    private String action;
    private String status;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur user;

    private String userConnected;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreatedDate
    private Instant creationDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

}

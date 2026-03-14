package com.glop.cibl_orga_sport.data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistory;

    private String action;
    private String status;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private Utilisateur user;

    private String userConnected;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

}

package com.glop.cibl_orga_sport.data;

import jakarta.persistence.*;

import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private int age;

    private String mdp;

    private int state;

    @ManyToOne
    @JoinColumn(name = "id_lieu")
    private Lieu lieu;

    @ManyToOne
    @JoinColumn(name = "idRoles")
    private Roles roles;

    @OneToMany(mappedBy = "user")
    private List<History> historyList;
}

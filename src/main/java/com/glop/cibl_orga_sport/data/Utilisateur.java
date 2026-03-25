package com.glop.cibl_orga_sport.data;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur implements UserDetails {

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<History> historyList = new ArrayList<>();

    public Utilisateur(String nom, String prenom, String email, int age, Lieu lieu) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.lieu = lieu;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.getDesignation()));
    }

    @Override
    public String getPassword() {
        return mdp;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + idUtilisateur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                '}';
    }
}
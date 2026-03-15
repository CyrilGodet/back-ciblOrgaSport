package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.Utilisateur;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UtilisateurDTO {
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private int age;
    private LieuDTO lieu;

    private String mdp;

    private String verifyMdp;

    private RolesDto roles;

    private int state;

    public UtilisateurDTO() {}

    public UtilisateurDTO(Long idUtilisateur, String nom, String prenom, String email, int age, LieuDTO lieu) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.lieu = lieu;
    }

    @Builder
    public UtilisateurDTO(Long idUtilisateur, String nom, String prenom, String email, int age,
                          LieuDTO lieu, String mdp, String verifyMdp, RolesDto roles, int state) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.lieu = lieu;
        this.mdp = mdp;
        this.verifyMdp = verifyMdp;
        this.roles = roles;
        this.state = state;
    }

    public static UtilisateurDTO fromEntity(Utilisateur user) {
        if (user == null) {
            return null;
        }
        return UtilisateurDTO.builder()
                .idUtilisateur(user.getIdUtilisateur())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .email(user.getEmail())
                .age(user.getAge())
                .lieu(LieuDTO.fromEntity(user.getLieu()))
                .mdp(user.getMdp())
                .roles(RolesDto.fromEntity(user.getRoles()))
                .state(user.getState())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDTO userDto) {
        if (userDto == null) {
            return null;
        }
        Utilisateur user = new Utilisateur();
        user.setIdUtilisateur(userDto.getIdUtilisateur());
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setLieu(LieuDTO.toEntity(userDto.getLieu()));
        user.setMdp(userDto.getMdp());
        user.setRoles(RolesDto.toEntity(userDto.getRoles()));
        user.setState(userDto.getState());
        return user;
    }
}

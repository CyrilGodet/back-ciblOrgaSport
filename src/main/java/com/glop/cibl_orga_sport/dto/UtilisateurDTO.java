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
        user.setIdUtilisateur(user.getId());
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

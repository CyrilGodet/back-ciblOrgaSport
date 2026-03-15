package com.glop.cibl_orga_sport.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRoles;

    private String designation;

    @OneToMany(mappedBy = "roles")
    private List<Utilisateur> userList;

    @OneToMany(mappedBy = "roles")
    private List<Permission> permissionList;
}

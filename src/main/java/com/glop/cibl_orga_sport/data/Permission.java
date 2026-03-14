package com.glop.cibl_orga_sport.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;

    private int credential;
    private int status;
    private String caption;

    @ManyToOne
    @JoinColumn(name = "idRoles")
    private Roles roles;
}

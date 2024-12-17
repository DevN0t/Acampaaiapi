package com.devnot.CampistsIbod.modules.branch.infra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}

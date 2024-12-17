package com.devnot.CampistsIbod.modules.event.infra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer branchId;
}

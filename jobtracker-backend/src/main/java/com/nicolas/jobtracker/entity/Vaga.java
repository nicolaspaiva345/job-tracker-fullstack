package com.nicolas.jobtracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresa;
    private String cargo;
    private String linkVaga;
    private String anotacoes;

    @Enumerated(EnumType.STRING)
    private StatusVaga status;
}
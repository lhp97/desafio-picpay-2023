package com.picpaysimplificado.picpaysimplificado.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor //Cria o construtor que recebe todos os parametros da classe
@EqualsAndHashCode()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera os ids de forma sequencial
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String mail;

    private String password;

    private BigDecimal balance; //saldo

    @Enumerated(EnumType.STRING)
    private UserType userType;

}

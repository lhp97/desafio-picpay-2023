package com.picpaysimplificado.picpaysimplificado.domain.user;

import com.picpaysimplificado.picpaysimplificado.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor //Cria o construtor que recebe todos os parametros da classe
@NoArgsConstructor
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

    public User(UserDTO userDTO) {
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.document = userDTO.document();
        this.mail = userDTO.mail();
        this.password = userDTO.password();
        this.balance = userDTO.balance();
        this.userType = userDTO.userType();
    }
}

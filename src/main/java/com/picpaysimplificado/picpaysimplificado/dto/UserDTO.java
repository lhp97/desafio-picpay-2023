package com.picpaysimplificado.picpaysimplificado.dto;

import com.picpaysimplificado.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(Long id, String firstName, String lastName, String document, String mail, String password, BigDecimal balance, UserType userType) {

}

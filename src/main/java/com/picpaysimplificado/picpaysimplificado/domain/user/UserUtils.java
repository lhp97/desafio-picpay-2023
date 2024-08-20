package com.picpaysimplificado.picpaysimplificado.domain.user;

import com.picpaysimplificado.picpaysimplificado.dto.UserDTO;

public class UserUtils {

    public static User convertUserDTOToUser(UserDTO dto) {
        return new User(dto.id(), dto.firstName(), dto.lastName(), dto.document(), dto.mail(), dto.password(), dto.balance(), dto.userType());
    }

    public static UserDTO convertUserToDTO(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocument(),
                user.getMail(),
                user.getPassword(),
                user.getBalance(),
                user.getUserType());
    }
}

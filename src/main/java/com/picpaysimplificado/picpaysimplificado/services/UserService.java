package com.picpaysimplificado.picpaysimplificado.services;

import com.picpaysimplificado.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.picpaysimplificado.domain.user.UserUtils;
import com.picpaysimplificado.picpaysimplificado.dto.UserDTO;
import com.picpaysimplificado.picpaysimplificado.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception {

        boolean isSenderMerchant = UserType.MERCHANT.equals(sender.getUserType());
        if (isSenderMerchant) {
            throw new Exception("Sender is a Merchant and cannot send amount");
        }

        boolean isSenderHaveBalance = sender.getBalance().compareTo(amount) >= 0;
        if (!isSenderHaveBalance) {
            throw new Exception("Sender no Balance!");
        }
    }

    public User findUserById(Long id) throws EntityNotFoundException {
        Optional<User> userOptional = this.userRepository.findUserById(id);

        return userOptional.orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);

        newUser = this.userRepository.save(newUser);

        return UserUtils.convertUserToDTO(newUser);
    }

    public List<UserDTO> getAll() {
        List<User> users = this.userRepository.findAll();

        return users.stream().map(UserUtils::convertUserToDTO).collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserDTO dto) {

        User userUpdate = this.userRepository.getReferenceById(id);

        userUpdate.setFirstName(dto.firstName());
        userUpdate.setLastName(dto.lastName());
        userUpdate.setDocument(dto.document());
        userUpdate.setMail(dto.mail());
        userUpdate.setBalance(dto.balance());
        userUpdate.setUserType(dto.userType());

        this.userRepository.save(userUpdate);

        return UserUtils.convertUserToDTO(userUpdate);
    }

}

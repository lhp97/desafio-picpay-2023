package com.picpaysimplificado.picpaysimplificado.services;

import com.picpaysimplificado.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.picpaysimplificado.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public void createTransaction(TransactionDTO transactionDTO) throws Exception {

        User sender = this.userService.findUserById(transactionDTO.senderId());
        this.userService.validadeTransaction(sender, transactionDTO.value());

        if (!isAuthorized()) {
            throw new Exception("Unauthorized");
        }

        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.updateUser(sender);
        this.userService.updateUser(receiver);

    }

    private boolean isAuthorized() {
        ResponseEntity<Map> response = this.restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Objects.requireNonNull(response.getBody()).get("status").equals("success");
        }

        return false;
    }

}

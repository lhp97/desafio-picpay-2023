package com.picpaysimplificado.picpaysimplificado.domain.transaction;

import com.picpaysimplificado.picpaysimplificado.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;

    private BigDecimal amount;

    private LocalDateTime timestamp;
}

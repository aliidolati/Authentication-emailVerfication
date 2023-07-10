package com.example.authentication.Model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List ;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends AbstractEntity {
    private Long balance;
    @OneToOne()
    private User user ;
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Transaction> transactions ;
}

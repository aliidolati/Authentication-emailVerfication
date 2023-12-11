package com.example.authentication.Model.entity;

import com.example.authentication.Model.enums.DealType;
import com.example.authentication.Model.enums.TrackingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction extends AbstractEntity{
    private Date date ;
    private Long amount ;
    @ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
    private Wallet wallet ;
    @Enumerated(EnumType.STRING)
    private DealType dealType ;
}

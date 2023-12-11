package com.example.authentication.Model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AbstractEntity {
    @Id
    @GeneratedValue
    private Long id ;
    @Version
    private Integer version ;
    @CreatedDate
    private Date insertTimeStamp ;
    @LastModifiedDate
    private Date updateTimeStamp ;
}
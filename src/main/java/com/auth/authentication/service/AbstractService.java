package com.example.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class AbstractService<E , R extends JpaRepository<E , Long>>{

    @Autowired
    protected R repository ;

}

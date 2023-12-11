package com.example.authentication.convertor;

import com.example.authentication.Model.dto.RegisterRequest;
import com.example.authentication.Model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvertor extends BaseConvertor<User , RegisterRequest> {
}

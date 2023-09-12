package com.arjun.springboot.mapper;

import com.arjun.springboot.dto.UserDto;
import com.arjun.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

//    Incase the User Entity and the UserDTO class have diff names
//    @Mapping(source = "email", target = "emailAddress")

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

}

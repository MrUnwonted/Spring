package com.arjun.springboot.service.impl;

import com.arjun.springboot.dto.UserDto;
import com.arjun.springboot.entity.User;
import com.arjun.springboot.mapper.AutoUserMapper;
import com.arjun.springboot.mapper.UserMapper;
import com.arjun.springboot.repository.UserRepository;
import com.arjun.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

//    @Override
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    @Override
    public UserDto createUser(UserDto userDto) {
//        Convert UserDto into User JPA Entity
//        User user = UserMapper.mapToUser(userDto);

//        User user = modelMapper.map(userDto,User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

//         Convert User JPA entity to UserDto
//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
//        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
//        return UserMapper.mapToUserDto(user);
//        return modelMapper.map(user,UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());
//        return users.stream().map((user -> modelMapper.map(user,UserDto.class)))
//                .collect(Collectors.toList());
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updateUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updateUser);
//        return modelMapper.map(updateUser,UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updateUser);
    }

    @Override
    public void  deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}

package ru.ddk.simplewebservice.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddk.simplewebservice.domain.User;
import ru.ddk.simplewebservice.dto.UserDto;
import ru.ddk.simplewebservice.mapper.UserMapper;
import ru.ddk.simplewebservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class UserServiceImpl {//implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //@Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    //@Override
    public boolean delete(String userId) {
        try{
            userRepository.deleteById(userId);
            log.info("User has ben deleted id: " + userId);
            return true;
        }catch (Exception e){
            log.error("Except delete user ", e);
            return false;
        }
    }

    //@Override
    public UserDto save(User user) {
        try{
            UserDto save = userMapper.toDto( userRepository.save(user));
            log.info("User has ben save User: " + user);
            return save;
        }catch (Exception e){
            log.error("Except save user ", e);
            return null;
        }
    }

    //@Override
    public UserDto findById(String id) {
        return userRepository.findById(id).map(userMapper::toDto).orElse(new UserDto("can`t find by id: " + id, "", "","",""));
    }
}

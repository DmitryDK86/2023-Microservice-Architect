package ru.ddk.simplewebservice.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.domain.TranManager;
import ru.ddk.simplewebservice.dto.TranManagerDto;
import ru.ddk.simplewebservice.dto.UserDto;
import ru.ddk.simplewebservice.mapper.TranManagerMapper;
import ru.ddk.simplewebservice.mapper.UserMapper;
import ru.ddk.simplewebservice.repository.LocalChangesRepository;
import ru.ddk.simplewebservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserServiceHttpImpl implements UserServiceTranManagerHttp {

    private final UserRepository userRepository;
    private final LocalChangesRepository localChangesRepository;
    private final UserMapper userMapper;
    private final HttpManagerRest httpManagerRest;
    private final TranManagerMapper tranManagerMapper;

    public UserServiceHttpImpl(UserRepository userRepository,
                               LocalChangesRepository localChangesRepository, UserMapper userMapper, HttpManagerRest httpManagerRest, TranManagerMapper tranManagerMapper) {
        this.userRepository = userRepository;
        this.localChangesRepository = localChangesRepository;
        this.userMapper = userMapper;
        this.httpManagerRest = httpManagerRest;
        this.tranManagerMapper = tranManagerMapper;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> allLocalChg = localChangesRepository.findAll().stream().filter(LocalChanges::getCommitted).map(userMapper::toDto).collect(Collectors.toList());
        List<String> chUserName = allLocalChg.stream().map(UserDto::getUsername).distinct().collect(Collectors.toList());
        List<UserDto> allUser = userRepository.findAll().stream().filter(user -> chUserName.stream().noneMatch(c -> c.equals(user.getUsername())) ).map(userMapper::toDto).collect(Collectors.toList());

        allLocalChg.addAll(allUser);
        return allLocalChg;
    }

//    @Override
//    public boolean delete(String userId) {
//        try{
//            userRepository.deleteById(userId);
//            log.info("User has ben deleted id: " + userId);
//            return true;
//        }catch (Exception e){
//            log.error("Except delete user ", e);
//            return false;
//        }
//    }

    @Override
    public TranManagerDto save(TranManager tranManager, LocalChanges localChanges) {
        try{
            //UserDto save = userMapper.toDto( userRepository.save(user));

            // http вызов менеджера
            TranManagerDto tranManagerDto = tranManagerMapper.toDto(httpManagerRest.postSaveQuery(tranManager, localChanges));

            log.info("TranManagerDto has ben save User: " + tranManagerDto);
            return tranManagerDto;
        }catch (Exception e){
            log.error("Except save tranManagerDto ", e);
            return null;
        }
    }

    @Override
    public UserDto findById(String id) {
        UserDto userDto = localChangesRepository.findById(id).filter(LocalChanges::getCommitted).map(userMapper::toDto).orElse(null);
        if(userDto == null) {
            return userRepository.findById(id).map(userMapper::toDto).orElse(new UserDto("can`t find by id: " + id, "", "", "", ""));
        }

        return new UserDto(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail() + " FROM LOCAL CH", userDto.getPhone());
    }
}

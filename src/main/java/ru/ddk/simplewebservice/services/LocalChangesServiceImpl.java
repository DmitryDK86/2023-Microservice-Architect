package ru.ddk.simplewebservice.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.dto.LocalChangesDto;
import ru.ddk.simplewebservice.dto.TranManagerDto;
import ru.ddk.simplewebservice.mapper.LocalChangesMapper;
import ru.ddk.simplewebservice.mapper.UserMapper;
import ru.ddk.simplewebservice.repository.LocalChangesRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class LocalChangesServiceImpl implements LocalChangesService {

    private final LocalChangesRepository repository;
    private final UserService userService;
    private final LocalChangesMapper localChangesMapper;
    private final UserMapper userMapper;
    private final TranManagerService tranManagerService;


    public LocalChangesServiceImpl(LocalChangesRepository repository, UserService userService, LocalChangesMapper localChangesMapper, UserMapper userMapper, TranManagerService tranManagerService) {
        this.repository = repository;
        this.userService = userService;
        this.localChangesMapper = localChangesMapper;
        this.userMapper = userMapper;
        this.tranManagerService = tranManagerService;
    }

    @Override
    public List<LocalChangesDto> findAll() {
        return repository.findAll().stream().map(localChangesMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean delete(String userName) {
        try{
            repository.deleteById(userName);
            log.info("LocalStorage has ben deleted id: " + userName);
            return true;
        }catch (Exception e){
            log.error("Except delete LocalStorage ", e);
            return false;
        }
    }

    public LocalChangesDto save(LocalChanges localChanges) {
        LocalChangesDto localChangesDto = localChangesMapper.toDto(localChanges);
        LocalChanges localChangesInStorage = repository.findAll().stream()
                .filter(u -> u.getUsername().equals(localChangesDto.getUsername()))
                .findFirst().orElse(null);

        // 1 если транзакция успешна НА МЕНЕДЖЕРЕ, сравнить id, если разные, то перенести в user
        if (localChangesInStorage != null) {
            TranManagerDto byIdTranManager = tranManagerService.findById(localChangesInStorage.getTranId());
            if (byIdTranManager.getCommitted() && !Objects.equals(localChangesDto.getTranId(), localChangesInStorage.getTranId())) {
                userService.save(userMapper.fromDto(userMapper.toDto(localChangesInStorage)));
            }
        }

        return writeDbLocalChanges(localChanges);
    }


    private LocalChangesDto writeDbLocalChanges(LocalChanges localChanges) {
        try {
            localChanges.setAborted(false);
            localChanges.setCommitted(true);
            return localChangesMapper.toDto(repository.save(localChanges));
        } catch (Exception e) {
            localChanges.setAborted(true);
            localChanges.setCommitted(false);
            return localChangesMapper.toDto(localChanges);
        }
    }
//    @Override
//    public List<CommitLogDto> findAll() {
//        return repository.findAll().stream().map(commitLogMapper::toDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public CommitLogDto save(CommitLog commitLog) {
//        try{
//            CommitLogDto save = commitLogMapper.toDto(repository.save(commitLog));
//            log.info("CommitLog has ben save CommitLog: " + commitLog);
//            return save;
//        }catch (Exception e){
//            log.error("Except save CommitLog ", e);
//            return null;
//        }
//    }

}

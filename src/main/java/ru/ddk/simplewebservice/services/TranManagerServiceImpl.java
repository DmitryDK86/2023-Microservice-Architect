package ru.ddk.simplewebservice.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.ddk.simplewebservice.domain.TranManager;
import ru.ddk.simplewebservice.dto.TranManagerDto;
import ru.ddk.simplewebservice.mapper.TranManagerMapper;
import ru.ddk.simplewebservice.repository.TranManagerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TranManagerServiceImpl implements TranManagerService {

    private final TranManagerRepository tranManagerRepository;
    private final TranManagerMapper tranManagerMapper;

    public TranManagerServiceImpl(TranManagerRepository tranManagerRepository, TranManagerMapper tranManagerMapper) {
        this.tranManagerRepository = tranManagerRepository;
        this.tranManagerMapper = tranManagerMapper;
    }

    @Override
    public List<TranManagerDto> findAll() {
        return tranManagerRepository.findAll().stream().map(tranManagerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TranManagerDto findById(String tranId) {
        return tranManagerMapper.toDto(tranManagerRepository.findById(tranId).orElse(null));
    }

    @Override
    public TranManagerDto save(TranManager tranManager) {
        try {
            TranManagerDto save = tranManagerMapper.toDto(tranManagerRepository.save(tranManager));

            // тут update вызвать через http

            log.info("tranManager has ben save tranManager: " + tranManager);
            return save;
        } catch (Exception e) {
            log.error("Except save tranManager ", e);
            return null;
        }
    }

    @Override
    public TranManagerDto update(TranManager tranManager) {
        try {
            TranManagerDto tranManagerId = tranManagerRepository.findById(tranManager.getIdTran()).map(tranManagerMapper::toDto).orElse(null);
            if (tranManagerId == null) {
                return null;
            } else if (!tranManagerId.getAborted() && !tranManagerId.getCommitted()) {
                // all ok
                if (!tranManager.getAborted() && tranManager.getCommitted()) {
                    tranManagerId.setCntRespInst(tranManagerId.getCntRespInst() + 1);
                    if (tranManagerId.getCntRespInst() == tranManagerId.getCntInst()) {
                        tranManagerId.setCommitted(true);
                    }
                } else if (tranManager.getAborted()) {
                    tranManagerId.setCommitted(false);
                    tranManagerId.setAborted(true);
                }

                TranManagerDto save = tranManagerMapper.toDto(tranManagerRepository.save(tranManagerMapper.fromDto(tranManagerId)));
                log.info("tranManager has ben save tranManager: " + tranManager);
                return save;
            }
            return null;
        } catch (Exception e) {
            log.error("Except save tranManager ", e);
            return null;
        }
    }

    @Override
    public boolean delete(String tranId) {
        try {
            tranManagerRepository.deleteById(tranId);
            log.info("tranManager has ben deleted id: " + tranId);
            return true;
        } catch (Exception e) {
            log.error("Except delete tranManager ", e);
            return false;
        }
    }
}

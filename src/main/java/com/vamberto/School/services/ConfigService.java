package com.vamberto.School.services;

import com.vamberto.School.models.Config;
import com.vamberto.School.repositories.ConfigRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigRepository configRepository;

    public void createConfig(Config config){
        configRepository.save(config);
    }

}

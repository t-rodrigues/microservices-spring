package dev.trodrigues.orderworker.services.impl;

import dev.trodrigues.orderworker.clients.CepClient;
import dev.trodrigues.orderworker.services.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CepServiceImpl implements CepService {

    private final CepClient cepClient;

    @Override
    public void findByCep(String cep) {
        var address = cepClient.findByCep(cep);
        log.info("ADDRESS: {}", address);
    }

}

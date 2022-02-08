package dev.trodrigues.orderworker.clients;

import dev.trodrigues.orderworker.domain.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${viacep}")
public interface CepClient {

    @GetMapping("/{cep}/json")
    Address findByCep(@PathVariable String cep);

}

package com.devops.example.server.service;

import com.devops.example.server.model.Security;
import com.devops.example.server.repository.SecurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Transactional
public class SecurityService {

    @Autowired
    private SecurityRepository securityRepository;

    public Flux<Security> getAll(){
        return securityRepository.findAll();
    }

    public Mono<Security> getById(final String id){
        return securityRepository.findById(id);
    }

    public Mono<Security> save(final Security security){
        return securityRepository.save(security);
    }

    public Mono<Security> update(final String id, final Security security){
        return securityRepository.findById(id).flatMap(existingSecurity ->{
            existingSecurity.setName(security.getName());
            existingSecurity.setCategory(security.getCategory());
            existingSecurity.setPurchasePrice(security.getPurchasePrice());
            existingSecurity.setPurchasedate(security.getPurchasedate());
            existingSecurity.setQuantity(security.getQuantity());

            return securityRepository.save(existingSecurity);
        });
    }

    public Mono<?> delete(final String id){
        return getById(id).switchIfEmpty(Mono.empty())
                .flatMap(securityToBeDeleted -> {
                    return securityRepository.delete(securityToBeDeleted).then(Mono.just(securityToBeDeleted));
                })
                .switchIfEmpty(Mono.empty());
    }
}

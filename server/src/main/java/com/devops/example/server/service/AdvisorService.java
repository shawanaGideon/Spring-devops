package com.devops.example.server.service;

import com.devops.example.server.model.Advisor;
import com.devops.example.server.repository.AdvisorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AdvisorService {

    @Autowired
    private AdvisorRepository advisorRepository;

    public Flux<Advisor> findAll(){
        return advisorRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<Advisor> save(final Advisor advisor){
        return advisorRepository.save(advisor);
    }

    public Flux<Advisor> saveAll(final List<Advisor> advisors) {
        return advisorRepository.saveAll(advisors);
    }

    public Mono<Advisor> getById(final String id){
        return advisorRepository.findById(id);
    }

    public Mono<Advisor> update(final String id, Advisor advisor){
        return advisorRepository.findById(id).flatMap(existingAdvisor -> {
            existingAdvisor.setFirstName(advisor.getFirstName());
            existingAdvisor.setLastName(advisor.getLastName());
            existingAdvisor.setPhone(advisor.getPhone());
            existingAdvisor.setEmail(advisor.getEmail());

            return advisorRepository.save(existingAdvisor);
        });
    }

    public Mono<?>delete(final String id){
        return getById(id).switchIfEmpty(Mono.empty())
                .flatMap(advisorToBeDeleted -> {
                    return advisorRepository.delete(advisorToBeDeleted).then(Mono.just(advisorToBeDeleted));
                })
                .switchIfEmpty(Mono.empty());
    }
}

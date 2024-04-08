package com.devops.example.server.controller;


import com.devops.example.server.model.Advisor;
import com.devops.example.server.service.AdvisorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/advisor")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @GetMapping
    public Flux<Advisor> findAll(){
        return advisorService.findAll();
    }
    @PostMapping
    public Mono<?> save(@RequestBody final Advisor advisor){
        return advisorService.save(advisor);
    }

    @PostMapping("/save-list")
    public Flux<Advisor> save(@RequestBody final List<Advisor> advisors) {
        return advisorService.saveAll(advisors);
    }

    @GetMapping("/{id}")
    public Mono<Advisor> update(@PathVariable("id") final String id){
        return advisorService.getById(id);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Advisor>> updateById(@PathVariable("id") final String _id, @RequestBody Advisor advisor){
        return advisorService.update(_id,advisor)
                .map(updatedAdvisor -> ResponseEntity.ok().body(updatedAdvisor))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @DeleteMapping("{id}")
    public Mono<?> delete(@PathVariable final String id){
        return advisorService.delete(id);
    }
}

package com.devops.example.server.controller;

import com.devops.example.server.model.Security;
import com.devops.example.server.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @GetMapping
    public Flux<Security> getAll(){
        return securityService.getAll();
    }

    @GetMapping("{id}")
    public Mono<Security> getById(@PathVariable("id") final String id){
        return securityService.getById(id);
    }

    @PostMapping
    public Mono<?> save(@RequestBody final Security security){
        return securityService.save(security);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Security>> update(@PathVariable("id") final String id, @RequestBody Security security){
        return securityService.update(id, security)
                .map(updatedSecurity -> ResponseEntity.ok().body(updatedSecurity))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @DeleteMapping("{id}")
    public Mono<?> delete(@PathVariable final String id){
        return securityService.delete(id);
    }
}

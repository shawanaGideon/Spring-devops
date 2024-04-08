package com.devops.example.server.controller;

import com.devops.example.server.model.Client;
import com.devops.example.server.service.ClientService;
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
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Flux<Client> getAll(){
        return clientService.getAll();
    }

    @PostMapping
    public Mono<Client> save(@RequestBody final Client client) {
        return clientService.save(client);
    }

    @PostMapping("/save-list")
    public Flux<Client> saveAll(@RequestBody final List<Client> clients) {
        return clientService.saveAll(clients);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> getById(@PathVariable("id") final String id) {
        return clientService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Client>> update(@PathVariable("id") final String id, @RequestBody Client client){
        return clientService.update(id, client)
                .map(updatedClient -> ResponseEntity.ok().body(updatedClient))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @DeleteMapping("{id}")
    public Mono<?> delete(@PathVariable final String id){
        return clientService.delete(id);
    }
}

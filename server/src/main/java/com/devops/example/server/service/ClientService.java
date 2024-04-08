package com.devops.example.server.service;

import com.devops.example.server.model.Client;
import com.devops.example.server.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Flux<Client> getAll(){
        return clientRepository.findAll();
    }

    public Mono<Client> save(final Client client) {
        return clientRepository.save(client);
    }

    public Flux<Client> saveAll(final List<Client> clients) {
        return clientRepository.saveAll(clients);
    }

    public Mono<Client>getById(final String id){
        return clientRepository.findById(id);
    }

    public Mono<Client> update(final String id, Client client){
        return clientRepository.findById(id).flatMap(existingClient -> {
            existingClient.setFirstName(client.getFirstName());
            existingClient.setLastName(client.getLastName());
            existingClient.setAddress(client.getAddress());
            existingClient.setPhone(client.getPhone());
            existingClient.setEmail(client.getEmail());

            return clientRepository.save(existingClient);
        });
    }

    public Mono<?>delete(final String id){
        return getById(id).switchIfEmpty(Mono.empty())
                .flatMap(clientToBeDeleted -> {
                    return clientRepository.delete(clientToBeDeleted).then(Mono.just(clientToBeDeleted));
                })
                .switchIfEmpty(Mono.empty());
    }
}

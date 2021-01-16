package ${groupId}.domain.impl;

import ${groupId}.contract.${entity}Model;
import ${groupId}.domain.shared.${entity}Domain;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ${entity}DomainImpl implements ${entity}Domain  {

    @Override
    public Mono<${entity}Model> getById(UUID id) throws Exception {
        return Mono.empty();
    }

    @Override
    public Mono<${entity}Model> create(${entity}Model ${entity_camel}Model) throws Exception {
        return Mono.empty();
    }

    @Override
    public Mono<${entity}Model> update(${entity}Model ${entity_camel}Model) throws Exception {
        return Mono.empty();
    }

    @Override
    public Mono<Integer> deleteById(UUID id) throws Exception {
        return Mono.empty();
    }
}
package ${groupId}.domain.shared;

import ${groupId}.contract.${entity}Model;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ${entity}Domain {

    Mono<${entity}Model> getById(UUID id) throws Exception;

    Mono<${entity}Model> create(${entity}Model ${entity_camel}Model) throws Exception;

    Mono<${entity}Model> update(${entity}Model ${entity_camel}Model) throws Exception;

    Mono<Integer> deleteById(UUID id) throws Exception;
}
package ${groupId}.domain.shared;

import ${groupId}.contract.${entity}Model;

import java.util.UUID;

public interface ${entity}Domain {

    ${entity}Model getById(UUID id) throws Exception;

    ${entity}Model create(${entity}Model ${entity_camel}Model) throws Exception;

    ${entity}Model update(${entity}Model ${entity_camel}Model) throws Exception;

    Integer deleteById(UUID id) throws Exception;
}
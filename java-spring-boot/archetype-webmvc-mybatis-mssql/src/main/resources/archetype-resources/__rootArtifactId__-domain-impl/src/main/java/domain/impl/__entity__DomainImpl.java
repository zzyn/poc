package ${groupId}.domain.impl;

import ${groupId}.contract.${entity}Model;
import ${groupId}.domain.shared.${entity}Domain;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ${entity}DomainImpl implements ${entity}Domain  {

    @Override
    public ${entity}Model getById(UUID id) throws Exception {
        return null;
    }

    @Override
    public ${entity}Model create(${entity}Model ${entity_camel}Model) throws Exception {
        return null;
    }

    @Override
    public ${entity}Model update(${entity}Model ${entity_camel}Model) throws Exception {
        return null;
    }

    @Override
    public Integer deleteById(UUID id) throws Exception {
        return 0;
    }
}
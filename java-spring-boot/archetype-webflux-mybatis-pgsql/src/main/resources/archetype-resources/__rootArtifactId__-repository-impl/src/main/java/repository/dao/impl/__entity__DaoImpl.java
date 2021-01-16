package ${groupId}.repository.dao.impl;

import ${groupId}.repository.dao.shared.${entity}Dao;
import ${groupId}.repository.entity.${entity};
import ${groupId}.repository.mapper.${entity}Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ${entity}DaoImpl implements ${entity}Dao {

    @Resource
    private ${entity}Mapper ${entity_camel}Mapper;

    @Override
    @Transactional
    public ${entity} get(UUID id) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ${entity} create(${entity} ${entity_camel}) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ${entity} update(${entity} ${entity_camel}) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public Integer delete(UUID id) throws Exception {
        return null;
    }
}
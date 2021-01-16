package ${groupId}.repository.dao.shared;

import ${groupId}.repository.entity.${entity};
import java.util.UUID;

public interface ${entity}Dao {

    ${entity} get(UUID id) throws Exception;

    ${entity} create(${entity} ${entity_camel}) throws Exception;

    ${entity} update(${entity} ${entity_camel}) throws Exception;

    Integer delete(UUID id) throws Exception;
}
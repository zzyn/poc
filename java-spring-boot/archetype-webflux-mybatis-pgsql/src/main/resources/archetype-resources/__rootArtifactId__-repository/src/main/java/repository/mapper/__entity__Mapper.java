package ${groupId}.repository.mapper;

import ${groupId}.repository.handler.*;
import ${groupId}.repository.entity.${entity};
import ${groupId}.repository.provider.${entity}Provider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface ${entity}Mapper {

    @SelectProvider(type = ${entity}Provider.class, method = "getById")
    @Results(id = "${entity}", value = {
            @Result(property = "id", column = "id", javaType = UUID.class, jdbcType = JdbcType.CHAR, typeHandler = UUIDTypeHandler.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "createdStamp", column = "created_stamp", javaType = LocalDateTime.class, jdbcType = JdbcType.TIMESTAMP, typeHandler = LocalDateTimeTypeHandler.class),
            @Result(property = "lastUpdatedStamp", column = "last_updated_stamp", javaType = LocalDateTime.class, jdbcType = JdbcType.TIMESTAMP, typeHandler = LocalDateTimeTypeHandler.class),
            @Result(property = "createdBy", column = "created_by", javaType = String.class),
            @Result(property = "lastUpdatedBy", column = "last_updated_by", javaType = String.class),
    })
    ${entity} getById(@Param("id") UUID id);

    @InsertProvider(type = ${entity}Provider.class, method = "create")
    Integer create(Map<String, Object> params);

    @UpdateProvider(type = ${entity}Provider.class, method = "updateById")
    Integer updateById(@Param("id") UUID id);

    @DeleteProvider(type = ${entity}Provider.class, method = "deleteById")
    Integer deleteById(@Param("id") UUID id);
}
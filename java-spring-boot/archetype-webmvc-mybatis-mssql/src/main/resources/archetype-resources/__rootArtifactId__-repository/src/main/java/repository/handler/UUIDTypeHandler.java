package ${groupId}.repository.handler;

import java.util.Objects;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, UUID uuid, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(columnIndex, uuid.toString());
    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        if (Objects.nonNull(value)) {
            return UUID.fromString(value);
        }
        return null;
    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String value = resultSet.getString(columnIndex);
        if (Objects.nonNull(value)) {
            return UUID.fromString(value);
        }
        return null;
    }

    @Override
    public UUID getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String value = callableStatement.getString(columnIndex);
        if (Objects.nonNull(value)) {
            return UUID.fromString(value);
        }
        return null;
    }
}

package ${groupId}.repository.handler;

import java.util.Objects;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    private static final String FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, LocalDateTime dateTime, JdbcType jdbcType) throws SQLException {

        preparedStatement.setString(columnIndex, dateTime.format(DateTimeFormatter.ofPattern(FORMATTER)));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        if (Objects.nonNull(value)) {
            return java.sql.Timestamp.valueOf(value).toLocalDateTime();
        }
        return null;
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String value = resultSet.getString(columnIndex);
        if (Objects.nonNull(value)) {
            return java.sql.Timestamp.valueOf(value).toLocalDateTime();
        }
        return null;
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String value = callableStatement.getString(columnIndex);
        if (Objects.nonNull(value)) {
            return java.sql.Timestamp.valueOf(value).toLocalDateTime();
        }
        return null;
    }
}

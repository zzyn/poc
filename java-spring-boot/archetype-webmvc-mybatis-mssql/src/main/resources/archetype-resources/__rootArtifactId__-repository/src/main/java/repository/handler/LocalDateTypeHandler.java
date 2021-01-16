package ${groupId}.repository.handler;

import java.util.Objects;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

    private static final String FORMATTER = "yyyy-MM-dd";

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, LocalDate date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(columnIndex, date.format(DateTimeFormatter.ofPattern(FORMATTER)));
    }

    @Override
    public LocalDate getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        if (Objects.nonNull(value)) {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(FORMATTER));
        }
        return null;
    }

    @Override
    public LocalDate getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String value = resultSet.getString(columnIndex);
        if (Objects.nonNull(value)) {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(FORMATTER));
        }
        return null;
    }

    @Override
    public LocalDate getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String value = callableStatement.getString(columnIndex);
        if (Objects.nonNull(value)) {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(FORMATTER));
        }
        return null;
    }
}

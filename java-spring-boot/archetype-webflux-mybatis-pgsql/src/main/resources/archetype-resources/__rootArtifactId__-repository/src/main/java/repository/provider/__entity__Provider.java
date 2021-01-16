package ${groupId}.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class ${entity}Provider {

    private static final String TABLE_NAME = "${entity}";

    public String getById() {
        String text = new SQL() {{
        SELECT(" * ");
        FROM(TABLE_NAME);
        WHERE("id = #{id}");
        }}.toString();
        return text;
    }

    public String create(){
        String text = new SQL() {{
        INSERT_INTO(TABLE_NAME);
        VALUES("id","#{id}");
        VALUES("name","#{name}");
        VALUES("created_by","#{created_by}");
        VALUES("created_stamp","#{created_stamp}");
        VALUES("last_updated_by","#{last_updated_by}");
        VALUES("last_updated_stamp","#{last_updated_stamp}");
        }}.toString();
        return text;
    }

    public String updateById(){
        String text = new SQL() {{
        UPDATE(TABLE_NAME);
        SET("name = #{name}");
        WHERE("id = #{id}");
        }}.toString();
        return text;
    }

    public String deleteById() {
        String text = new SQL() {{
        DELETE_FROM(TABLE_NAME);
        WHERE("id = #{id}");
        }}.toString();
        return text;
    }
}
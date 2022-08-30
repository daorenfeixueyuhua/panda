package com.daoren.dbagent.util;

import com.daoren.dbagent.enums.SqlType;
import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.dto.SqlParams;
import org.junit.Test;

import java.util.Map;

public class DbHttpUtilTest {

    @Test
    public void post() {
        String url = "http://127.0.0.1:9097/preProxy";
        String sql = DbAgentUtil.sqlEncode("select * from sys_person where name = #{name};");
        SqlParams params = new SqlParams();
        params.put("name", "彭达");
//        final String result = DbAgentUtil.fetchSql(sql, params);

        final RequestParams requestParams = RequestParams.builder()
                .tip("127.0.0.1")
                .db("api")
                .type(SqlType.SELECT)
                .code(sql)
                .params(params).build();
        final Map result = DbHttpUtil.post(url, requestParams);
        System.out.println(result);
    }
}
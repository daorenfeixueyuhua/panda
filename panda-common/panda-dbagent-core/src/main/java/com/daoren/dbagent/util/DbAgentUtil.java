package com.daoren.dbagent.util;

import com.daoren.dbagent.model.dto.SqlParams;
import org.apache.commons.lang.StringUtils;

import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sql解析
 *
 * @author peng_da
 * @date 2022/8/11 9:25
 */
public class DbAgentUtil {

    private static volatile Pattern pattern = Pattern.compile("#\\{(.*?)}");

    private DbAgentUtil() {
    }

    /**
     * sql语句Base64转String
     *
     * @param code :
     * @return java.lang.String
     * @author peng_da
     * @since 2022/8/11 9:33
     */
    public static String sqlDecode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        final String base64 = code.substring(0, code.length() - 4) +
                new StringBuffer(code.substring(code.length() - 4)).reverse();
        String sql = new String(Base64.getDecoder().decode(base64.getBytes()));
        return sql;
    }

    /**
     * sql语句String转Base64
     *
     * @param sql :
     * @return java.lang.String
     * @author peng_da
     * @since 2022/8/11 9:34
     */
    public static String sqlEncode(String sql) {
        if (StringUtils.isBlank(sql)) {
            return null;
        }
        StringBuilder code = new StringBuilder(Base64.getEncoder().encodeToString(sql.getBytes()));
        final String[] lasts = code.substring(code.length() - 4).split("");
        code = new StringBuilder(code.substring(0, code.length() - 4));
        for (int i = lasts.length - 1; i >= 0; i--) {
            code.append(lasts[i]);
        }
        return code.toString();
    }

    /**
     * 获取sql中的#{xxx}参数
     * @author peng_da
     * @since 2022/8/11 9:46
     * @param sql :
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getSqlParams(String sql){
        final Matcher matcher = pattern.matcher(sql);

        List<String> params = new LinkedList<>();
        while (matcher.find()){
            params.add(matcher.group(1));
        }
        return params;
    }

    /**
     * 将params填充进入sql中
     * @author peng_da
     * @since 2022/8/11 9:58
     * @param sql : sql原始语句 例如 <p>select * from sys_demo where name = #{name};</p>
     * @param params : 参数
     * @return java.lang.String
     */
    public static String fetchSql(String sql, SqlParams params) {
        List<String> sqlParams = getSqlParams(sql);

        int j;
        String key;
        for(j = 0; j < sqlParams.size(); ++j) {
            key = (String)sqlParams.get(j);
            if (key.contains(".")) {
                params.put(key.replace(".", "Dot") + j, params.get(key));
            }

            params.put(key + j, params.get(key));
        }

        for (String sqlParam : sqlParams) {
            key = sqlParam;
            params.remove(key);
        }

        for(j = 0; j < sqlParams.size(); ++j) {
            key = (String)sqlParams.get(j);
            Object value = params.get(key + j);
            if (!(value instanceof List)) {
                String replaceKey = key;
                if (key.contains(".")) {
                    replaceKey = key.replace(".", "Dot");
                }

                if (sql.contains("%#{" + key + "}%")) {
                    sql = sql.replace("%#{" + key + "}%", "CONCAT(CONCAT('%',#{params." + replaceKey + j + "}),'%')");
                } else if (sql.contains("%#{" + key + "}")) {
                    sql = sql.replace("%#{" + key + "}", "CONCAT('%',#{params." + replaceKey + j + "})");
                } else if (sql.contains("#{" + key + "}%")) {
                    sql = sql.replace("#{" + key + "}%", "CONCAT(#{params." + replaceKey + j + "},'%')");
                } else {
                    sql = sql.replace("#{" + key + "}", "#{params." + replaceKey + j + "}");
                }
            } else {
                List<Object> values = (List)value;
                int index = 0;
                StringBuilder stringBuilder = new StringBuilder();
                String replaceKey = key;
                if (key.contains(".")) {
                    replaceKey = key.replace(".", "Dot");
                }

                for(Iterator<Object> var11 = values.iterator(); var11.hasNext(); ++index) {
                    Object object = var11.next();
                    params.put(replaceKey + j + index, object);
                    stringBuilder.append("#{params.").append(replaceKey).append(j).append(index).append("},");
                }

                String valueStr = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
                sql = sql.replace("#{" + key + "}", valueStr);
            }
        }

        return sql;
    }
}

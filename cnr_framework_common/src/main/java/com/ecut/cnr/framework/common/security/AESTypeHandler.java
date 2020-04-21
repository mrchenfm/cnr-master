package com.ecut.cnr.framework.common.security;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/1 14:15
 * @Description:
 */
public class AESTypeHandler extends BaseTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        if (parameter instanceof String) {
            if (StringUtils.isNotBlank((String) parameter)) {
                String encodedParam = SimpleAES.encrypt((String) parameter, "aaa");
                ps.setString(i, encodedParam);
            }
            else {
                ps.setString(i, "");
            }
        } else {
            ps.setObject(i, parameter);
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String param = rs.getString(columnName);
        if (StringUtils.isNotEmpty(param)) {
            return SimpleAES.decrypt(param, "aaa");
        } else {
            return param;
        }
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}

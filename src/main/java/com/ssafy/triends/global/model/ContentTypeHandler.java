package com.ssafy.triends.global.model;

import com.ssafy.triends.global.model.ContentType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@MappedTypes(ContentType.class)
public class ContentTypeHandler<E extends Enum<E>> implements TypeHandler<ContentType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, ContentType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getContentTypeId());
    }

    @Override
    public ContentType getResult(ResultSet rs, String columnName) throws SQLException {
        return getContentType(rs.getInt(columnName));
    }

    @Override
    public ContentType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getContentType(rs.getInt(columnIndex));
    }

    @Override
    public ContentType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getContentType(cs.getInt(columnIndex));
    }

    private ContentType getContentType(int code) {
        for (ContentType contentType : ContentType.values()) {
            if (contentType.getContentTypeId() == code)
                return contentType;
        }
        return null;
    }
}

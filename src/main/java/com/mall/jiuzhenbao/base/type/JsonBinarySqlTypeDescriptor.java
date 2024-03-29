package com.mall.jiuzhenbao.base.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicBinder;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Descriptor for sql type jsonb
 * @author Neo.Li
 * @version 0.0.1
 */
public class JsonBinarySqlTypeDescriptor extends AbstractJsonSqlTypeDescriptor {

	private static final long serialVersionUID = 7191189259826320110L;

	public static final JsonBinarySqlTypeDescriptor INSTANCE = new JsonBinarySqlTypeDescriptor();

	@Override
	public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
		return new BasicBinder<X>(javaTypeDescriptor, this) {
			
			@Override
			protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options)
					throws SQLException {
				st.setObject(index, javaTypeDescriptor.unwrap(value, JsonNode.class, options), getSqlType());
			}

			@Override
			protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
					throws SQLException {
				st.setObject(name, javaTypeDescriptor.unwrap(value, JsonNode.class, options), getSqlType());
			}
		};
	}

}

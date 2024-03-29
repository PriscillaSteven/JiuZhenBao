package com.mall.jiuzhenbao.base.type;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicExtractor;

/**
 * Descriptor for sql type json/jsonb
 * @author Neo.Li
 * @version 0.0.1
 */
public abstract class AbstractJsonSqlTypeDescriptor implements SqlTypeDescriptor {

	private static final long serialVersionUID = -5747945434935958375L;

	@Override
	public int getSqlType() {
		return Types.OTHER;
	}

	@Override
	public boolean canBeRemapped() {
		return true;
	}

	@Override
	public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
		return new BasicExtractor<X>(javaTypeDescriptor, this) {

			@Override
			protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
				return javaTypeDescriptor.wrap(rs.getObject(name), options);
			}

			@Override
			protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
				return javaTypeDescriptor.wrap(statement.getObject(index), options);
			}

			@Override
			protected X doExtract(CallableStatement statement, String name, WrapperOptions options)
					throws SQLException {
				return javaTypeDescriptor.wrap(statement.getObject(name), options);
			}
		};
	}
}

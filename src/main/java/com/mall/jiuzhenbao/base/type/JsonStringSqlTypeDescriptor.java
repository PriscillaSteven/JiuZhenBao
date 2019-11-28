package com.mall.jiuzhenbao.base.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicBinder;

/**
 * Descriptor for sql type json
 * @author Neo.Li
 * @version 0.0.1
 */
public class JsonStringSqlTypeDescriptor extends AbstractJsonSqlTypeDescriptor {

	private static final long serialVersionUID = -7239664005212474257L;
	public static final JsonStringSqlTypeDescriptor INSTANCE = new JsonStringSqlTypeDescriptor();
	 
	    @Override
	    public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
	    	
	        return new BasicBinder<X>(javaTypeDescriptor, this) {
	        	
	            @Override
	            protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
	                st.setString(index, javaTypeDescriptor.unwrap(value, String.class, options));
	            }
	 
	            @Override
	            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)throws SQLException {
	                st.setString(name, javaTypeDescriptor.unwrap(value, String.class, options));
	            }
	        };
	    }

}

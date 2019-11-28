package com.mall.jiuzhenbao.base.type;

import java.util.Properties;

import com.mall.jiuzhenbao.utils.JacksonUtil;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutableMutabilityPlan;
import org.hibernate.usertype.DynamicParameterizedType;

/**
 * Java type descriptors : JsonType
 * @author Neo.Li
 */
public class JsonTypeDescriptor extends AbstractTypeDescriptor<Object> implements DynamicParameterizedType {

	private static final long serialVersionUID = 1745962549444018280L;
	private Class<?> jsonObjectClass;

	@Override
	public void setParameterValues(Properties parameters) {
		jsonObjectClass = ((ParameterType) parameters.get(PARAMETER_TYPE)).getReturnedClass();

	}

	public JsonTypeDescriptor() {
		super(Object.class, new MutableMutabilityPlan<Object>() {
			
			private static final long serialVersionUID = -6896913363550534827L;

			@Override
			protected Object deepCopyNotNull(Object value) {
				return JacksonUtil.clone(value);
			}
		});
	}

	@Override
	public boolean areEqual(Object one, Object another) {
		if (one == another) {
			return true;
		}
		if (one == null || another == null) {
			return false;
		}
		return JacksonUtil.toJsonNode(JacksonUtil.toString(one))
				.equals(JacksonUtil.toJsonNode(JacksonUtil.toString(another)));
	}

	@Override
	public String toString(Object value) {
		return JacksonUtil.toString(value);
	}

	@Override
	public Object fromString(String string) {
		return JacksonUtil.fromString(string, jsonObjectClass);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public <X> X unwrap(Object value, Class<X> type, WrapperOptions options) {
		if (value == null) {
			return null;
		}
		if (String.class.isAssignableFrom(type)) {
			return (X) toString(value);
		}
		if (Object.class.isAssignableFrom(type)) {
			return (X) JacksonUtil.toJsonNode(toString(value));
		}
		throw unknownUnwrap(type);
	}

	@Override
	public <X> Object wrap(X value, WrapperOptions options) {
		if (value == null) {
			return null;
		}
		return fromString(value.toString());
	}

}

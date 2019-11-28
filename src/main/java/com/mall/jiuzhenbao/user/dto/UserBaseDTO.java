package com.mall.jiuzhenbao.user.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.mall.jiuzhenbao.base.dto.CommonJsonKey;
import com.mall.jiuzhenbao.security.exception.BeanValidationErrorCode;
import com.mall.jiuzhenbao.validation.UUIDFormat;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserBaseDTO implements Serializable{
	private static final long serialVersionUID = -1805433939624460884L;
	
	@JsonProperty(value=CommonJsonKey.FIRST_NAME, required=true)
	@NotNull(message=BeanValidationErrorCode.NOT_BLANK)
	@NotBlank(message=BeanValidationErrorCode.NOT_BLANK)
	@Length(min=0, max=128, message=BeanValidationErrorCode.ELEMENT_LENGTH)
	protected String firstName;
	
	@NotNull(message=BeanValidationErrorCode.NOT_BLANK)
	@NotBlank(message=BeanValidationErrorCode.NOT_BLANK)
	@JsonProperty(value=CommonJsonKey.LAST_NAME, required=true)
	@Length(min=0, max=128, message=BeanValidationErrorCode.ELEMENT_LENGTH)
	protected String lastName;
	
	@JsonProperty(value=CommonJsonKey.EMAIL, required=true)
	@NotNull(message=BeanValidationErrorCode.NOT_BLANK)
	@NotBlank(message=BeanValidationErrorCode.NOT_BLANK)
	@Length(min=6,max=128,message=BeanValidationErrorCode.ELEMENT_LENGTH)
	@Email(message=BeanValidationErrorCode.EMAIL_FORMAT)
	protected String email;
	
	@JsonProperty(CommonJsonKey.PHONE_NUMBER)
	@Length(min=0, max=25, message=BeanValidationErrorCode.ELEMENT_LENGTH)
	protected String phoneNumber;
	
	@JsonProperty(value=CommonJsonKey.ROLE_ID, required=true)
	@NotNull(message=BeanValidationErrorCode.NOT_BLANK)
	@NotBlank(message=BeanValidationErrorCode.NOT_BLANK)
	String roleId;
	
	@UUIDFormat
	@JsonProperty(CommonJsonKey.ORGANIZATION_ID)
	String organizationId;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}

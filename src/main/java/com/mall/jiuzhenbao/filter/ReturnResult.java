package com.mall.jiuzhenbao.filter;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.jiuzhenbao.dto.Pagination;
import com.mall.jiuzhenbao.message.SpogErrorMessageBean;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnResult implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int status;
    private Object data;
    private Pagination pagination;
    private SpogErrorMessageBean[] errors = new SpogErrorMessageBean[0];
    
    public ReturnResult(){}
    
    public ReturnResult(int status, Object data){
        this.status = status;
        this.data = data;
    }
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public SpogErrorMessageBean[] getErrors() {
        return errors;
    }
    public void setErrors(SpogErrorMessageBean[] errors) {
        this.errors = errors;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
    
}

package com.mall.jiuzhenbao.user.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserDTO extends UserBaseDTO implements Serializable {
    private static final long serialVersionUID = -1805433939624460884L;

    @NotNull
    @JsonProperty("user_id")
    private String userId;
    
    @JsonProperty("image_url")
    private String imageUrl;
    
    @NotNull
    @JsonProperty("create_ts")
    private long createTimestamp;
    
    @JsonProperty("last_login_ts")
    private long lastLoginTimestamp;
    
    @JsonProperty("blocked")
    private boolean blocked;

    @JsonProperty("status")
    private String status;
    
	@JsonProperty("allowed_actions")
	private List<UserActions>  allowedActions;
	
	@JsonProperty("name")
	private String fullName;
    
    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(long lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<UserActions> getAllowedActions() {
		return allowedActions;
	}
	
	public void setAllowedActions(List<UserActions> allowedActions) {
		this.allowedActions = allowedActions;
	}

	public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", createTimestamp=" + createTimestamp + ", isBlocked=" + blocked + "]";
    }

}

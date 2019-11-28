package com.mall.jiuzhenbao.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements InitializingBean {
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${spring.jersey.application-path}")
	private String apiBase;
	
	@Value("${profile.image.base.path}")
	private String profileImageBasePath;
	
	@Value("${host.cloudconsole}")
	private String ccHost;

	@Value("${protocol.cloudconsole}")
	private String ccProtocol;

	@Value("${reset_password.path}")
	private String resetPasswordPath;

	private String ccBaseUrl;
	
	public String getApiBase() {
		return apiBase;
	}
	
	public String getProfileImageBasePath() {
		return profileImageBasePath;
	}

	public String getCcHost() {
		return ccHost;
	}

	public String getCcProtocol() {
		return ccProtocol;
	}

	public String getCcBaseUrl() {
		return ccBaseUrl;
	}

	public String getResetPasswordPath() {
		return resetPasswordPath;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public void setApiBase(String apiBase) {
		this.apiBase = apiBase;
	}

	public void setProfileImageBasePath(String profileImageBasePath) {
		this.profileImageBasePath = profileImageBasePath;
	}

	public void setCcHost(String ccHost) {
		this.ccHost = ccHost;
	}

	public void setCcProtocol(String ccProtocol) {
		this.ccProtocol = ccProtocol;
	}

	public void setResetPasswordPath(String resetPasswordPath) {
		this.resetPasswordPath = resetPasswordPath;
	}

	public void setCcBaseUrl(String ccBaseUrl) {
		this.ccBaseUrl = ccBaseUrl;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ccBaseUrl = ccProtocol + "://" + ccHost;
	}
}

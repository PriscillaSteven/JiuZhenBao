package com.mall.jiuzhenbao.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.mall.jiuzhenbao.user.resource.UserResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * The resource(Controller) configuration of jersey api
 * Created by Steven
 * 
 * @version 0.0.1
 */
@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
//        register(JspMvcFeature.class);
        register(MultiPartFeature.class);
        register(JacksonJsonProvider.class);
//		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
//		property(ServletProperties.FILTER_FORWARD_ON_404, true);
//		property(JspMvcFeature.TEMPLATES_BASE_PATH, "/html");
//		property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);

		registerAPIResources();
	}

	private void registerAPIResources() {
        register(UserResource.class);
	}
}
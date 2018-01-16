package com.horizon.config;

import com.horizon.controller.type.ApiVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author Amjad
 */
@Configuration
public class ApiRequestMappingConfig extends WebMvcConfigurationSupport {

    /**
     * The api path prefix.
     */
    @Value("${api.version.prefix}")
    private String apiPrefix;

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {

            @Override
            protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
                RequestMappingInfo requestMappingInfo  = super.getMappingForMethod(method, handlerType);
                if(requestMappingInfo  == null) {
                    return null;
                }

                //Check the method annotation first so we can override the class annotation if needed.
                ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
                if(methodAnnotation != null) {
                    return prependApiPath(methodAnnotation.value(), requestMappingInfo);
                }

                ApiVersion handlerTypeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
                if(handlerTypeAnnotation != null) {
                    return prependApiPath(handlerTypeAnnotation.value(), requestMappingInfo);
                }

                return requestMappingInfo;
            }

        };
    }

    /**
     * Prepends the api path to the controller path.
     *
     * @param value
     * @param requestMappingInfo
     * @return /api/v{value}/path
     */
    private RequestMappingInfo prependApiPath(int value, RequestMappingInfo requestMappingInfo) {
        return RequestMappingInfo.paths(apiPrefix + value).build().combine(requestMappingInfo);
    }

}


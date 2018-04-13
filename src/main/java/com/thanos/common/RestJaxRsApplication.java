package com.thanos.common;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * @author  date13 on 15/3/10.
 */
public class RestJaxRsApplication extends ResourceConfig {
    public RestJaxRsApplication() {
        // register application resources
       /* register(PodcastResource.class);
        register(PodcastLegacyResource.class);*/

        // register filters
        register(RequestContextFilter.class);
        //register(LoggingResponseFilter.class);
        //register(CORSResponseFilter.class);

        // register exception mappers
       /* register(GenericExceptionMapper.class);
        register(AppExceptionMapper.class);
        register(NotFoundExceptionMapper.class);*/

        // register features
        register(JacksonFeature.class);
        register(MultiPartFeature.class);
    }
}

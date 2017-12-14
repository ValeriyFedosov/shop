package edu.karazin.shop.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigurerAdapter extends WebMvcConfigurerAdapter {

    private static final String[] CLASSPATH_RESOURCE_LOCATION = {"classpath:/static/"};


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/discount").setViewName("discount-list");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATION);
    }
}

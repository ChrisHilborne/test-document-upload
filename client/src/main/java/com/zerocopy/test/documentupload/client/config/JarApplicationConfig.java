package com.zerocopy.test.documentupload.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;


//@Configuration
//@Profile("docker")
public class JarApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**", "/css/**", "/js/**", "/templates/**", "/templates/fragments/files/list/**", "/templates/fragments/files/upload/**").addResourceLocations("classpath:/static/images/", "classpath:/static/css/", "classpath:/static/js/", "classpath:/templates/fragments/files/list", "classpath:/templates/fragments/files/upload", "classpath:/templates/");

    }

//    @Bean
//    public ViewResolver htmlViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        ISpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateEngineMessageSource(htmlTemplateResolver());
//        resolver.setTemplateEngine(engine);
//        resolver.setContentType("text/html; charset=UTF-8");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setViewNames(new String[]{"*.html"});
//        return resolver;
//    }
//
//    private ITemplateResolver htmlTemplateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setPrefix("/templates/");
//        resolver.setCacheable(false);
//        resolver.setTemplateMode(TemplateMode.HTML);
//        return resolver;
//    }
}

package com.cskaoyan.config;

import com.cskaoyan.interceptor.MyFirstInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.stereotype.Controller;
import com.cskaoyan.converter.String2DateConverter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;

@EnableWebMvc
@ComponentScan(value = "com.cskaoyan.controller",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class))

//实现WebMvcConfig接口
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    ConfigurableConversionService conversionService;

    //日期转换
    @PostConstruct
    public void addConverters(){
        String2DateConverter string2DateConverter = new String2DateConverter();
        conversionService.addConverter(string2DateConverter);
    }

    @Bean
    @Primary
    public ConfigurableConversionService configurableConversionService(){
        return conversionService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor());
        //registry.addInterceptor().addPathPatterns("/abc/**");
    }
    //处理所有静态资源的配置
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //配置视图解析器
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/image/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");}
}


package com.cskaoyan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//注册扫描包
@ComponentScan(value = "com.cskaoyan",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}))
//注册aspectj
@EnableAspectJAutoProxy
//注册事务
@EnableTransactionManagement
public class SpringConfig {
    //datasource
    @Bean
    public DataSource druidDatasource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://192.168.8.117:3306/production_ssm?characterEncoding=utf-8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("lxt000205");
        return druidDataSource;
    }



    //MapperScannerConfigurer
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("com.cskaoyan.mapper");
        return  mapperScannerConfigurer;
    }

    //文件上传
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        //设置文件上传大小限制
        commonsMultipartResolver.setMaxInMemorySize(5240000);
        return commonsMultipartResolver;
    }

    //事务
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

//分页
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //分页 以下
        PageInterceptor pageInterceptor = new PageInterceptor();
        //创建插件需要的参数集合
        Properties properties = new Properties();
        //配置数据库方言 为oracle
        properties.setProperty("helperDialect","mysql");
        //配置分页的合理化数据
        properties.setProperty("reasonable","true");
        pageInterceptor.setProperties(properties);
        //将拦截器设置到sqlSessionFactory中
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        //以上
        return sqlSessionFactoryBean;

    }

}



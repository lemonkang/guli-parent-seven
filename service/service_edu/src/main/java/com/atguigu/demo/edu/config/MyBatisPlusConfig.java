package com.atguigu.demo.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atguigu.demo.edu.mapper")
public class MyBatisPlusConfig {

    /*逻辑删除配置*/
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    /*分页查询配置*/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

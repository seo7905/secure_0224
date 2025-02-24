package com.sist.secure_0224.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.sist.secure_0224.mapper")
public class DbConfig {

    @Bean
    public SqlSessionFactory getFactory(DataSource dataSource) throws Exception{
        
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        
        PathMatchingResourcePatternResolver resolver = 
            new PathMatchingResourcePatternResolver();
            Resource[] resource = resolver.getResources("classpath:mapper/**/*.xml");
            // getResources s 가 붙으면 Resource를 [] 배열로 받아야 한다.
        factoryBean.setMapperLocations(resource);

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate getTemplate(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}

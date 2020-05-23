package com.chengcheng.configration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.chengcheng.dao.postgresDao",
			sqlSessionFactoryRef = "slaveSqlSessionFactory02")
public class PostGresDataSourceConfig {

	@Bean(name = "slaveDataSource02")
	@ConfigurationProperties(prefix = "spring.datasource.database-postgresql")
	public DataSource slaveDataSource(){

		DataSource druidDataSource = DataSourceBuilder.create().build();
		return druidDataSource;
	}

	@Bean(name = "slaveSqlSessionFactory02")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource02") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath*:mybatis/mybatis-postgresql/*.xml"));
		return sessionFactoryBean.getObject();
	}

}

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
@MapperScan(basePackages = "com.chengcheng.dao.mysqlDao",
			sqlSessionFactoryRef = "slaveSqlSessionFactory01")
public class MySQLDataSourceConfig {

	@Bean(name = "slaveDataSource01")
	@ConfigurationProperties(prefix = "spring.datasource.database-mysql")
	public DataSource slaveDataSource(){

		DataSource druidDataSource = DataSourceBuilder.create().build();
		return druidDataSource;
	}

	@Bean(name = "slaveSqlSessionFactory01")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource01") DataSource dataSource) throws Exception {

		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath*:mybatis/mybatis-mysql/*.xml"));

		return sessionFactoryBean.getObject();
	}

}

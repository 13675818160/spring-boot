/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spring.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.config.ApplicationProperties;
import com.spring.boot.config.TomcatDataSourceProperties;
import com.spring.web.ParameterInterceptor;
import com.spring.web.SecurityInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PreDestroy;

@Configuration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class )
@EnableConfigurationProperties({TomcatDataSourceProperties.class,ApplicationProperties.class})
@MapperScan("com.spring.mapper")
@ComponentScan("com.spring")
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Application extends WebMvcConfigurerAdapter {

	private static Log logger = LogFactory.getLog(Application.class);

	@Autowired
	private com.spring.boot.config.TomcatDataSourceProperties config;

	private org.apache.tomcat.jdbc.pool.DataSource pool;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
        logger.debug("dataSource bean create");
		this.pool = new org.apache.tomcat.jdbc.pool.DataSource();

		this.pool.setDriverClassName(config.getDriverClassName());
		this.pool.setUrl(config.getUrl());
		if (config.getUsername() != null) {
			this.pool.setUsername(config.getUsername());
		}
		if (config.getPassword() != null) {
			this.pool.setPassword(config.getPassword());
		}
		this.pool.setInitialSize(config.getInitialSize());
		this.pool.setMaxActive(config.getMaxActive());
		this.pool.setMaxIdle(config.getMaxIdle());
		this.pool.setMinIdle(config.getMinIdle());
		this.pool.setTestOnBorrow(config.isTestOnBorrow());
		this.pool.setTestOnReturn(config.isTestOnReturn());
		this.pool.setTestWhileIdle(true);
		this.pool.setValidationQuery(config.getValidationQuery());
		return this.pool;
	}
 
	public void close() {
        logger.debug("dataSourcePool close");

		if (this.pool != null) {
			this.pool.close();
		}
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        logger.debug("sqlSessionFactory bean create");

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/spring/mapper/*.xml"));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
        logger.debug("transactionManager bean create");
        return new DataSourceTransactionManager(dataSource());
	}


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        logger.debug("mappingJackson2HttpMessageConverter bean create");
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new CustomObjectMapper();
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    public SecurityInterceptor securityInterceptor(){
        logger.debug("securityInterceptor bean create");
        SecurityInterceptor securityInterceptor = new SecurityInterceptor();
        return securityInterceptor;
    }

    @Bean
    public ParameterInterceptor parameterInterceptor(){
        logger.debug("parameterInterceptor bean create");
        ParameterInterceptor parameterInterceptor = new ParameterInterceptor();
        return parameterInterceptor;
    }

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(parameterInterceptor()).addPathPatterns("/pay/**");
		registry.addInterceptor(securityInterceptor()).addPathPatterns("/pay/**");
	}

	public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
	}


}

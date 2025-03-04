package com.paypal.infrastructure.configuration;

import com.paypal.infrastructure.model.entity.JobExecutionInformationEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Datasource setup for application module
 */
@Configuration
@PropertySource({ "classpath:infrastructure_db.properties" })
@EnableJpaRepositories(basePackages = "com.paypal.infrastructure.repository",
		entityManagerFactoryRef = "applicationEntityManagerFactory",
		transactionManagerRef = "applicationTransactionManager")
public class InfrastructureDatasourceConfig {

	/**
	 * Creates a bean to retrieve configuration properties for
	 * {@link DataSourceProperties}
	 * @return the {@link DataSourceProperties}
	 */
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "infrastructure.db.datasource")
	public DataSourceProperties applicationDataSourceDataSourceProperties() {
		return new DataSourceProperties();
	}

	/**
	 * Creates a bean to setup the {@link DataSource} according with
	 * {@link InfrastructureDatasourceConfig#applicationDataSourceDataSourceProperties()}
	 * properties
	 * @return the {@link DataSource}
	 */
	@Primary
	@Bean(name = "applicationDataSource")
	public DataSource applicationDataSource(final DataSourceProperties applicationDataSourceProperties) {
		return applicationDataSourceProperties.initializeDataSourceBuilder().build();
	}

	/**
	 * Creates a new {@link LocalContainerEntityManagerFactoryBean} that holds the
	 * packages within entities inside application module
	 * @param builder the {@link EntityManagerFactoryBuilder}
	 * @return the {@link LocalContainerEntityManagerFactoryBean}
	 */
	@Primary
	@Bean(name = "applicationEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean applicationEntityManagerFactory(
			final EntityManagerFactoryBuilder builder, final DataSource applicationDataSource) {
		return builder.dataSource(applicationDataSource).packages(JobExecutionInformationEntity.class).build();
	}

	/**
	 * Creates a new {@link PlatformTransactionManager} using the bean generated by
	 * {@link InfrastructureDatasourceConfig#applicationEntityManagerFactory(org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder, javax.sql.DataSource)}
	 * @param applicationTransactionManager the
	 * {@link LocalContainerEntityManagerFactoryBean}
	 * @return the {@link PlatformTransactionManager}
	 */
	@SuppressWarnings("java:S4449")
	@Primary
	@Bean(name = "applicationTransactionManager")
	public PlatformTransactionManager applicationTransactionManager(
			final @Qualifier("applicationEntityManagerFactory") LocalContainerEntityManagerFactoryBean applicationTransactionManager) {
		return new JpaTransactionManager(applicationTransactionManager.getObject());
	}

}

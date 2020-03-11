package net.fashiongo.webadmin.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "net.fashiongo.webadmin.data.repository.stats",
        entityManagerFactoryRef = "statEntityManager",
        transactionManagerRef = "statTransactionManager"
)
public class FgStats {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource6")
    public DataSourceProperties statDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "statDataSource")
    @ConfigurationProperties(prefix="spring.datasource6.dbcp2")
    public DataSource statDataSource() {
        return statDatasourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name="statEntityManager")
    public LocalContainerEntityManagerFactoryBean statEntityManager(EntityManagerFactoryBuilder builder,
                                                                        @Qualifier("statDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("net.fashiongo.webadmin.data.entity.stats").build();
    }

    @Bean(name = "statTransactionManager")
    public PlatformTransactionManager statTransactionManager(
            @Qualifier("statEntityManager") EntityManagerFactory statEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(statEntityManager);
        return transactionManager;
    }

    @Bean(name="statJdbcTemplate")
    public JdbcTemplate statJdbcTemplate (
            @Qualifier("statDataSource") DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }
}


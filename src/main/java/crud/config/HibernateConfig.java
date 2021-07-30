package crud.config;

import crud.model.Role;
import crud.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@EnableJpaRepositories("crud")
public class HibernateConfig {

    @Resource
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource =
                new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
        driverManagerDataSource.setUrl(env.getProperty("db.url"));
        driverManagerDataSource.setUsername(env.getProperty("db.username"));
        driverManagerDataSource.setPassword(env.getProperty("db.password"));
        return driverManagerDataSource;
    }


    @Bean
    public LocalSessionFactoryBean getLocalSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean =
                new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        localSessionFactoryBean.setHibernateProperties(props);
        localSessionFactoryBean.setAnnotatedClasses(User.class, Role.class);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(getLocalSessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

package it.univpm.advancedcode.cri.app;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

import it.univpm.advancedcode.cri.test.DataServiceConfigTest;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.FilterType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = {"it.univpm.advancedcode.cri.model", "it.univpm.advancedcode.cri.services"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = {DataServiceConfigTest.class})})

@EnableTransactionManagement
public class DataServiceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataServiceConfig.class);

    
    /** 
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        try {

            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
            ds.setUrl("jdbc:mysql://localhost:3306/CRI_DB?createDatabaseIfNotExist=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
            ds.setUsername("root");
            ds.setPassword("rootroot");

            return ds;
        } catch (Exception e) {
            logger.error("DataSource bean cannot be created!!", e);
            logger.error("DataSource bean cannot be created!!", e);

            return null;
        }
    }

    
    /** 
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    /** 
     * @return Properties
     */
    @Bean
    protected Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        hibernateProp.put("javax.persistence.schema-generation.database.action", "none");

        return hibernateProp;
    }

    
    /** 
     * @param dataSource
     * @param hibernateProperties
     * @return SessionFactory
     * @throws IOException
     */
    @Bean
    @Autowired
    public SessionFactory sessionFactory(DataSource dataSource, Properties hibernateProperties) throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("it.univpm.advancedcode.cri.model");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    
    /** 
     * @param sessionFactory
     * @return PlatformTransactionManager
     * @throws IOException
     */
    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) throws IOException {
        return new HibernateTransactionManager(sessionFactory);
    }
}

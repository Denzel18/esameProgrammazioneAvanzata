package it.univpm.advancedcode.cri.test;

import it.univpm.advancedcode.cri.app.DataServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"it.univpm.advancedcode.cri.model"})
@EnableTransactionManagement
public class DataServiceConfigTest extends DataServiceConfig {

    @Bean
    @Override
    protected Properties hibernateProperties() {
        Properties hibernateProp = super.hibernateProperties();
        hibernateProp.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        return hibernateProp;
    }
}

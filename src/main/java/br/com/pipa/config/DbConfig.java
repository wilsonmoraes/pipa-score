package br.com.pipa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static java.lang.String.format;


@Configuration
@EnableTransactionManagement
public class DbConfig {

    /*@Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        // ds.setUrl(format("jdbc:postgresql://%s:%s/%s", "192.168.10.22", 5433, "teste_wilson"));
        ds.setUrl(format("jdbc:postgresql://%s:%s/%s", "localhost", 5432, "pipa_score"));
        ds.setUsername("mymixtapez");
        ds.setPassword("xpto1234");
        return ds;
    }*/


    /**
     * @return DataSource
     * Connect with mysql
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(format("jdbc:mysql://%s:%s/%s?autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false", "localhost", 3306, "pipa_score"));
        ds.setUsername("root");
        ds.setPassword("abc102030");
        return ds;
    }

}

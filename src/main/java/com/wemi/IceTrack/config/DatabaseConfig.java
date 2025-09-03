package com.wemi.IceTrack.config;

import javax.sql.DataSource;

import com.wemi.IceTrack.services.SecretService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("!test")
public class DatabaseConfig {

    private final SecretService secretService;

    public DatabaseConfig(SecretService secretService) {
        this.secretService = secretService;
    }

    @Bean
    public DataSource dataSource() throws Exception {
        String username = secretService.getSecret("booming-premise-470408-r1", "icetrack-db-username");
        String password = secretService.getSecret("booming-premise-470408-r1", "icetrack-db-password");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://google/ice-track-db?cloudSqlInstance=booming-premise-470408-r1:europe-west2:ice-track-db&socketFactory=com.google.cloud.sql.postgres.SocketFactory");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}

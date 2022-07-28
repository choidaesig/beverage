package com.bmilk.bev;

import com.bmilk.bev.repository.BevRepository;
import com.bmilk.bev.repository.JdbcBevRepository;
import com.bmilk.bev.service.BevService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public BevRepository bevRepository(){
        return new JdbcBevRepository(dataSource);
    }

    @Bean
    public BevService bevService(){
        return new BevService(bevRepository());
    }

}

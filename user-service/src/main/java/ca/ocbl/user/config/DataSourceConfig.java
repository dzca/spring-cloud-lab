package ca.ocbl.user.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
	
	private Logger logger = Logger.getLogger(getClass());
	
    @Value("${datasource.druid.initialSize}")  
    private int initialSize;   
    
    @Value("${datasource.druid.minIdle}")  
    private int minIdle;  
    
    @Value("${datasource.druid.maxActive}")  
    private int maxActive;  
    
    @Value("${datasource.druid.maxWait}")  
    private int maxWait;  
    
    @Value("${datasource.druid.timeBetweenEvictionRunsMillis}")  
    private int timeBetweenEvictionRunsMillis;  
    
    @Value("${datasource.druid.minEvictableIdleTimeMillis}")  
    private int minEvictableIdleTimeMillis;  
    
    @Value("${datasource.druid.validationQuery}")  
    private String validationQuery;   
    
    @Value("${datasource.druid.testWhileIdle}")  
    private boolean testWhileIdle;  
    
    @Value("${datasource.druid.testOnBorrow}")  
    private boolean testOnBorrow;  
    
    @Value("${datasource.druid.testOnReturn}")  
    private boolean testOnReturn;  
    
    @Value("${datasource.druid.filters}")  
    protected String filters;
    
    @Value("${spring.datasource.driverClassName}")
    private String driverClass;
    
    @Value("${spring.datasource.url}")
    private String url;
 
    @Value("${spring.datasource.username}")
    private String username;
 
    @Value("${spring.datasource.password}")
    private String password;
    
    @Bean(name="dataSource")
    @Qualifier("dataSource")
    @Primary
    public DataSource dataSource(){  
    	DruidDataSource datasource = getDataSource();
        
        datasource.setUrl(url);  
        datasource.setUsername(username);  
        datasource.setPassword(password);   
        
        try {  
            datasource.setFilters(filters);  
            
        } catch (SQLException e) {  
            logger.error("druid configuration initialization filter", e);  
        } 
        
        return datasource;  
    }  
    
    public DruidDataSource getDataSource(){  
        DruidDataSource datasource = new DruidDataSource();
        
        datasource.setDriverClassName(driverClass);
        //configuration  
        datasource.setInitialSize(initialSize);  
        datasource.setMinIdle(minIdle);  
        datasource.setMaxActive(maxActive);  
        datasource.setMaxWait(maxWait);  
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
        datasource.setValidationQuery(validationQuery);  
        datasource.setTestWhileIdle(testWhileIdle);  
        datasource.setTestOnBorrow(testOnBorrow);  
        datasource.setTestOnReturn(testOnReturn); 
        
        List<Filter> filterList=new ArrayList<>();
        filterList.add(wallFilter());
        datasource.setProxyFilters(filterList); 
          
        return datasource;  
    } 
    
    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter=new WallFilter();
        wallFilter.setDbType("mysql");
        wallFilter.setLogViolation(true);
        wallFilter.setThrowException(false);
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }
    
    @Bean
    public WallConfig wallConfig(){
        WallConfig config =new WallConfig();
        
        config.setDir("META-INF/druid/wall/mysql");
        config.setSelectUnionCheck(false);
        return config;
    }
}

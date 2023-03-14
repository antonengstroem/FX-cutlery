package FXcutlery.configuration;

import FXcutlery.services.CutOffTimeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CutOffTimeServiceImpl currencyCutoffTimesServiceImpl() {
        return new CutOffTimeServiceImpl();
    }
}

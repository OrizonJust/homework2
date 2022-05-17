package edu.laverno;

import edu.laverno.service.TestService;
import edu.laverno.service.TestServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {

    @Bean
    public static PropertySourcesPlaceholderConfigurer pspc() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
            TestService service = context.getBean(TestServiceImpl.class);

            service.startTesting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

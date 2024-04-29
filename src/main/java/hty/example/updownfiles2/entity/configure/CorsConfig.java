package hty.example.updownfiles2.entity.configure;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configurable
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**").
                allowedOrigins("*")
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                .maxAge(168000) //
                .allowedHeaders("*")
                .allowCredentials(true);//是否发送cookie
    }
}

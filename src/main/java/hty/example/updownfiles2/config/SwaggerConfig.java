package hty.example.updownfiles2.config;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Configurable
public class SwaggerConfig {
    /**
     * 配置Swagger2相关的bean
     */
   // @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("nnn"))

                //.apis(RequestHandlerSelectors.any())

                .paths(PathSelectors.ant("/kuang/**")).build()
                .enable(false);
    }
         /*
         RequestHandlerSelectors配置要扫描接口的方式
            *          basePackage:指定要扫描的包=>RequestHandlerSelectors.basePackage("com.kuang.swagger.controller")
            *          any():扫描全部
            *          none():全部不扫描
            *          withClassAnnotation:扫描类上的注解=>RequestHandlerSelectors.withClassAnnotation(RestController.class)
            *          withMethodAnnotation:扫描方法上的注解=>RequestHandlerSelectors.withMethodAnnotation(GetMapping.class)
         */
    //}

    /**
     * 此处主要显示API文档页面显示信息
     */
    private ApiInfo apiInfo()
    {
        //作者信息
        Contact contact = new Contact("bayes","http://shan.ye","111111111@qq.com");
        return new ApiInfoBuilder()
                .title("演示项目API")           //标题
                .description("演示项目")        //描述
                .version("1.0")              //版本
                .contact(contact)             //作者信息
                .build();
    }


}

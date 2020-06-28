package com.springboot.shiro.springbootshiro.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.web.Swagger2Controller;

@Configuration
@EnableSwagger2
//@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
//@ConditionalOnClass(value = Swagger2Controller.class)
public class Swagger2Config {

    private final static String TITLE = "SpringBoot Shiro";
    private final static String VERSION = "V1.0";
    private final static String DESCRIPTION = "API在线文档";
    private final static String BASEPACKAGE = "com.springboot.shiro.springbootshiro.shiro.controller";

    /**
     * 通过 createRestApi函数来构建一个DocketBean
     * 函数名,可以随意命名,喜欢什么命名就什么命名
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage(BASEPACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(TITLE)
                //条款地址
//                .termsOfServiceUrl("http://despairyoke.github.io/")
                .version(VERSION)
                //描述
                .description(DESCRIPTION)
                .build();
    }

}

package com.liumd.data.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liumuda
 * @date 2021/7/15 17:23
 */
@SpringBootConfiguration
public class InterceptorPathConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private HandlerInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/user")
                .excludePathPatterns("/login/user/register")
                .excludePathPatterns("/login/admin");
        //addPathPatterns("/**")  这是拦截那些路径
        //excludePathPatterns("")这是放行那些路径,可以加多个这方法,接着点就行
    }


}

package com.hqm.rabbit.utils.security;

import com.hqm.rabbit.utils.error.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @作者 胡勤明
 * @时间 2022-01-12 13:55
 * @版本 1.0
 */
@Component
@EnableWebSecurity
/**
 * 开启@EnableGlobalMethodSecurity(prePostEnabled = true)注解
 * 在继承 WebSecurityConfigurerAdapter 这个类的类上面贴上这个注解
 * 并且prePostEnabled设置为true,@PreAuthorize这个注解才能生效
 * SpringSecurity默认是关闭注解功能的.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入过滤器
     */
    @Resource
    private JwtVerificationFilter jwtVerificationFilter;
    /**
     * 认证失败处理类
     */
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf防护 >只有关闭了,才能接受来自表单的请求
        http.csrf().disable()
                .cors()//开启跨域
                .and()
                // 认证失败处理类
                .exceptionHandling() .authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedPage("/403")
                //.accessDeniedHandler()
                .and()
                //开启授权请求
                .authorizeRequests()
                //放行接口，因为使用自定义登录页面所以需要放行
                .antMatchers("/**/imserver/**","/imserver/**","/**/userinfo","/**/getLogin","/**/postLogin","/**/swagger-ui/**/","/**/doc.html","/swagger-ui.html").permitAll()
                //设置页面权限
                //.antMatchers("/menuLogin").hasAuthority("p2")
                //拦截所有请求，所有请求都需要登录认证
                .anyRequest().authenticated()
                .and()
                //开启问URL"/ logout"，使HTTP Session无效来清除用户，清除已配置的任何#rememberMe()身份验证，清除SecurityContextHolder，然后重定向到"/login?success"
                .logout()
                .and()
                .addFilterAfter(jwtVerificationFilter, UsernamePasswordAuthenticationFilter.class)
                //前后端分离采用JWT 不需要session（添加后Spring永远不会创建session）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

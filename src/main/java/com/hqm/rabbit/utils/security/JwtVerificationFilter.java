package com.hqm.rabbit.utils.security;


import javax.annotation.Resource;
import javax.servlet.FilterChain;

import com.hqm.rabbit.domain.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
/**
 * @作者 胡勤明
 * @时间 2022-01-12 13:56
 * @版本 1.0
 * @作用 拦截器
 */

/**
 * @author admin
 * 过滤器 发起请求前检验Token 实现并在每次请求时只执行一次过滤
 * 在spring中，filter都默认继承OncePerRequestFilter
 * OncePerRequestFilter顾名思义，他能够确保在一次请求只通过一次filter，而不需要重复执行
 * 为了兼容不同的web container，特意而为之
 * <p>
 * 在servlet2.3中，Filter会经过一切请求，包括服务器内部使用的forward转发请求和<%@ include file=”/login.jsp”%>的情况
 * <p>
 * servlet2.4中的Filter默认情况下只过滤外部提交的请求，forward和include这些内部转发都不会被过滤，
 */
@Component

public class JwtVerificationFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("过滤开始");

        SysUserVo userVo = jwtUtils.getToken(httpServletRequest);
        if (userVo != null) {

            try {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userVo, null, userVo.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

//                SecurityContextHolder
//                        .getContext()
//                        .setAuthentication(new UsernamePasswordAuthenticationToken(userVo.getUsername(), userVo.getPassword()));
                //刷新令牌
                jwtUtils.verifyToken(userVo);
            } catch (Exception e) {
                throw new RuntimeException("用户未登录");
            }
        }
        //将请求转发给过滤器链下一个filter
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

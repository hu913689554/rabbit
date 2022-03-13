package com.hqm.rabbit.utils.error;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @作者 胡勤明
 * @时间 2022-01-12 21:33
 * @版本 1.0
 */


/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException aue)
            throws IOException
    {
        response.sendRedirect("/403");
//        try
//        {
//            response.setStatus(200);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().print(new AjaxResult(0, "请求访问：{"+request.getRequestURI()+"}，认证失败账号或密码错误，无法访问系统资源"));
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }


}

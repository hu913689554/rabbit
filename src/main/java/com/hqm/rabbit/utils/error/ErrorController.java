package com.hqm.rabbit.utils.error;

import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
@RequestMapping
public class ErrorController {

    @RequestMapping(value = "/403")
    @ResponseStatus(value = HttpStatus.OK)
    public AjaxResult error403(HttpServletRequest request) {
        return new AjaxResult(403,"权限不足");
    }

    @RequestMapping(value = "/404")
    @ResponseStatus(value = HttpStatus.OK)
    public AjaxResult error404(HttpServletRequest request) {
        return new AjaxResult(404,"未找到此地址");
    }

    @RequestMapping(value = "/500")
    @ResponseStatus(value = HttpStatus.OK)
    public AjaxResult error500(HttpServletRequest request) {
        return new AjaxResult(500,"错误,请联系管理员解决");
    }
}

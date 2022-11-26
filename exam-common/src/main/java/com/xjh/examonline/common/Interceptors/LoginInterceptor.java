package com.xjh.examonline.common.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 登录拦截器
 * */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        logger.debug("request:"+request.getRequestURI());
        Object loginTeacher = request.getSession().getAttribute("loginTeacher");
        if (loginTeacher == null){
            request.getRequestDispatcher("/common/timeout.html").forward(request,response);
            //没有登录信息
            return false;
        }
//      //有登录信息可以继续执行
        return true;
    }
}

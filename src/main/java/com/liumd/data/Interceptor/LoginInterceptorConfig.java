package com.liumd.data.Interceptor;

import com.alibaba.fastjson.JSON;
import com.liumd.data.constant.Constant;
import com.liumd.data.utils.DateUtils;
import com.liumd.data.utils.exceptionUtil.ErrorInfo;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liumuda
 * @date 2021/7/15 14:31
 */
@Slf4j
@Component
public class LoginInterceptorConfig implements HandlerInterceptor {

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器!");
        return true;
        /*PrintWriter out;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        //请求进入拦截器,通过token查询,判断该用户是否登录
        if (!ObjectUtils.isEmpty(request.getHeader(Constant.USER_MAILBOX))) {
            if ((!ObjectUtils.isEmpty(
                    valueOperations.get(Constant.USER_MAILBOX+ request.getHeader(Constant.USER_MAILBOX))))){
                return true;
            }
        }
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(Constant.LOGIN_ERROR);
        errorInfo.setMessage("无权访问,请先登录");
        out = response.getWriter();
        out.append(JSON.toJSONString(errorInfo));
        out.flush();
        out.close();
        return false;*/
    }

}


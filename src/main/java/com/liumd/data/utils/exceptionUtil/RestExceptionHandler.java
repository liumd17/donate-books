package com.liumd.data.utils.exceptionUtil;

/**
 * @author liumuda
 * @date 2022/1/14 9:17
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

/**
 * 自定义异常处理类
 * 针对不同的异常自定义不同的方法
 * 环绕通知
 * 切面:针对所有的controller中抛出的异常
 * 若使用@ControllerAdvice,则不会自动转换为JSON格式
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 业务异常处理
     * @param e
     * @return ErrorInfo
     */
    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorInfo> businessExceptionHandler(HttpServletRequest request, ServiceException e) throws ServiceException {
        return new ResponseEntity(new ErrorInfo(e.getCode(),e.getMessage()), HttpStatus.CONFLICT);
    }

    /**
     * 业务异常处理
     * @param e
     * @return ErrorInfo
     */
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorInfo> BusinessExceptionHandler(HttpServletRequest request, AccessDeniedException e) throws ServiceException {
        return new ResponseEntity(new ErrorInfo(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 只要抛出该类型异常,则由此方法处理
     * 并由此方法响应出异常状态码及消息
     * 如:RoleController.getRoleById(String id)方法
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest request, Exception e) throws Exception {

        ErrorInfo body = new ErrorInfo();
        body.setCode(500);
        body.setMessage(e.getMessage());

        //可以根据公司情况不同，类似的异常可能需要不同的状态码
        ResponseEntity<ErrorInfo> responseEntity = new ResponseEntity<ErrorInfo>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}

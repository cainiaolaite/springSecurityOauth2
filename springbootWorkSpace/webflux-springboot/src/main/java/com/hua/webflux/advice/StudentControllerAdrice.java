package com.hua.webflux.advice;

import com.hua.webflux.exception.StudentValidNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * ControllerAdvice 正对于所有的 controller的切面
 * 执行前后，已经异常捕获等等
 */
@ControllerAdvice
public class StudentControllerAdrice {

    @ExceptionHandler
    public static ResponseEntity<String> handlerException(StudentValidNameException studentValidNameException){
        return new ResponseEntity<>(studentValidNameExceptionToString(studentValidNameException),HttpStatus.NOT_FOUND);
    }

    /**
     * 在使用 Hibernate-valid 时 验证完成后 会出现 BindingResult （用于返回验证结果）
     *  webExchangeBindException 实现了 BindingResult
     * @param webExchangeBindException
     * @return
     */
    @ExceptionHandler
    public static ResponseEntity<String> handlerException(WebExchangeBindException webExchangeBindException){
        return new ResponseEntity<String>(exToStr(webExchangeBindException), HttpStatus.NOT_FOUND);
    }

    public static String exToStr(WebExchangeBindException webExchangeBindException){
        return webExchangeBindException.getFieldErrors().stream()
                .map(fieldError -> fieldErrorToString(fieldError))
                .reduce("",(s1,s2)-> (StringUtils.isEmpty(s1) ? "[" : (s1 +",")) +s2)+"]";
        //reduce 减少  将流中 两个合并成一个 逐步减少

    }

    public static String fieldErrorToString(FieldError fieldError){
        StringBuffer buffer=new StringBuffer();
        buffer.append("{");
        String objectName=fieldError.getObjectName();
        buffer.append("\"objectName\":\""+objectName+"\",");
        String field=fieldError.getField();
        buffer.append("\"field\":\""+field+"\",");
        String message=fieldError.getDefaultMessage();
        buffer.append("\"message\":\""+message+"\"");
        buffer.append("}");
        return buffer.toString();
    }
    public static String studentValidNameExceptionToString(StudentValidNameException studentValidNameException){
        StringBuffer buffer=new StringBuffer();
        buffer.append("{");
        String objectName=studentValidNameException.getObjectName();
        buffer.append("\"objectName\":\""+objectName+"\",");
        String field=studentValidNameException.getField();
        buffer.append("\"field\":\""+field+"\",");
        String message=studentValidNameException.getMessage();
        buffer.append("\"message\":\""+message+"\"");
        buffer.append("}");
        return buffer.toString();
    }


}

package br.com.waes.json.diff.web.utils;

import br.com.waes.json.diff.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class RestControllerAdvice {

    private static final int INTERNAL_SERVER_ERROR = 500;
    private final int BAD_REQUEST = 400;

    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class})
    public DefaultResponse genericBusinessException(HttpServletRequest request, BusinessException ex) {
        return DefaultResponse.invalidResponse(ex.getMessage(),BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public DefaultResponse unexpectedError(HttpServletRequest request, Exception ex) {
        log.error("method=unexpectedError uri={} message={}",request.getRequestURI(),ex.getMessage(),ex);
        return DefaultResponse.invalidResponse("Sorry, something wrong happened",INTERNAL_SERVER_ERROR);
    }
}

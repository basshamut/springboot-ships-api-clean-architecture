package com.jrhub.api.presentation.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import com.jrhub.api.dto.HttpErrorInfoDto;
import com.jrhub.api.domain.exception.ServiceException;
import com.jrhub.api.utils.FormatUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class GeneralControllerExceptionHandler {

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseBody
    public ResponseEntity<HttpErrorInfoDto> handleBadCredentialsException(BadCredentialsException badCredentialsException, HttpServletRequest request) {
        HttpErrorInfoDto httpErrorInfoDto = FormatUtils.httpErrorInfoFormatted(HttpStatus.UNAUTHORIZED, request, badCredentialsException);
        log.error(httpErrorInfoDto.toString());
        log.error(Arrays.toString(badCredentialsException.getStackTrace()));
        return new ResponseEntity<>(httpErrorInfoDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseBody
    public ResponseEntity<HttpErrorInfoDto> handleServiceException(ServiceException serviceException, HttpServletRequest request) {
        HttpErrorInfoDto httpErrorInfoDto;

        switch (serviceException.getCode()) {
            case 400 -> {
                httpErrorInfoDto = FormatUtils.httpErrorInfoFormatted(HttpStatus.BAD_REQUEST, request, serviceException);
                log.error(httpErrorInfoDto.toString());
                log.error(Arrays.toString(serviceException.getStackTrace()));
                return new ResponseEntity<>(httpErrorInfoDto, HttpStatus.BAD_REQUEST);
            }
            case 404 -> {
                httpErrorInfoDto = FormatUtils.httpErrorInfoFormatted(HttpStatus.NOT_FOUND, request, serviceException);
                log.error(httpErrorInfoDto.toString());
                log.error(Arrays.toString(serviceException.getStackTrace()));
                return new ResponseEntity<>(httpErrorInfoDto, HttpStatus.NOT_FOUND);
            }
            default -> {
                httpErrorInfoDto = FormatUtils.httpErrorInfoFormatted(HttpStatus.INTERNAL_SERVER_ERROR, request, serviceException);
                log.error(httpErrorInfoDto.toString());
                log.error(Arrays.toString(serviceException.getStackTrace()));
                return new ResponseEntity<>(httpErrorInfoDto, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}

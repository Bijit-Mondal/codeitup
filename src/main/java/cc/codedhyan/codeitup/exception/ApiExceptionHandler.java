package cc.codedhyan.codeitup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { ApiRequestExceptionBadRequest.class })
    public ResponseEntity<Object> handleBadRequestApiRequestException(ApiRequestExceptionBadRequest e) {
        ApiException apiException =  ApiException.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .throwable(e)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ApiRequestExceptionConflict.class })
    public ResponseEntity<Object> handleConflictException(ApiRequestExceptionConflict e) {
        ApiException apiException =  ApiException.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .throwable(e)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { ApiInternalServerErrorException.class })
    public ResponseEntity<Object> handleInternalServerErrorException(ApiInternalServerErrorException e) {
        ApiException apiException =  ApiException.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .throwable(e)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { ApiRequestExceptionUnauthorized.class })
    public ResponseEntity<Object> handleUnauthorizedException(ApiRequestExceptionUnauthorized e) {
        ApiException apiException =  ApiException.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .throwable(e)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { ApiRequestExceptionNotFound.class })
    public ResponseEntity<Object> handleNotFoundException(ApiRequestExceptionNotFound e) {
        ApiException apiException =  ApiException.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .throwable(e)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}

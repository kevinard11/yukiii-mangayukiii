package yukiii.mangayukiii.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import yukiii.mangayukiii.config.exception.BadRequestException;
import yukiii.mangayukiii.constant.ErrorResponse;
import yukiii.mangayukiii.dto.common.response.ResponseErrorTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ResponseErrorTemplate<Object>> clientException(BadRequestException ex) {
    ResponseErrorTemplate<Object> errorMessage = ResponseErrorTemplate.builder()
        .respCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).respDesc(ex.getMessage())
        .errorList(Collections.singletonList(ex.getMessage())).timestamp(LocalDateTime.now())
        .build();
    ex.printStackTrace();
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ResponseErrorTemplate<Object>> globalException(Exception ex) {

    String errMessage = ErrorResponse.INTERNAL_ERROR.getMessage();
    ResponseErrorTemplate<Object> errorMessage = ResponseErrorTemplate.builder()
        .respCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())).respDesc(errMessage)
        .errorList(Collections.singletonList(errMessage)).timestamp(LocalDateTime.now()).build();

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);

    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ResponseErrorTemplate<Object>> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex) {

    String errMessage = "Data Input Tidak Valid";
    ResponseErrorTemplate<Object> errorMessage = ResponseErrorTemplate.builder()
        .respCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).respDesc(errMessage)
        .errorList(Collections.singletonList(errMessage)).timestamp(LocalDateTime.now())
        .build();

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    log.error(sw.toString());
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
    List<String> errorList = new ArrayList<>();

    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      errorList.add(error.getDefaultMessage());
    }

    String errMessage = "Data Input Tidak Valid";
    ResponseErrorTemplate<Object> errorMessage = ResponseErrorTemplate.builder()
        .respCode(String.valueOf(httpStatus.value())).respDesc(errMessage).errorList(errorList)
        .timestamp(LocalDateTime.now()).build();
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {

    String errMessage = "Data Input Tidak Valid";
    ResponseErrorTemplate<Object> errorMessage = ResponseErrorTemplate.builder()
        .respCode(String.valueOf(httpStatus.value())).respDesc(errMessage)
        .errorList(Collections.singletonList(ex.getMostSpecificCause().getMessage()))
        .timestamp(LocalDateTime.now()).build();

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    log.error(sw.toString());
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }
}

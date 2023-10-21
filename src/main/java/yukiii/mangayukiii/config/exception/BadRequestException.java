package yukiii.mangayukiii.config.exception;

import yukiii.mangayukiii.constant.ErrorResponse;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BadRequestException(ErrorResponse error) {
    super(error.getMessage());
  }
}

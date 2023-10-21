package yukiii.mangayukiii.constant;

public enum ErrorResponse {

  INPUT_INVALID("Data input invalid", 101),
  PAGE_SIZE_ERROR("Page/Size index tidak boleh 0", 301),
  INTERNAL_ERROR("Maaf sistem bermasalah. Hubungi Admin", 900);

  private String message;
  private int code;

  ErrorResponse(String message, int code) {
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }
}


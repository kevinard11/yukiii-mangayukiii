package yukiii.mangayukiii.dto.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
public class ResponseErrorTemplate<K> {

  @JsonProperty("resp_code")
  private String respCode;

  @JsonProperty("resp_desc")
  private String respDesc;

  @JsonProperty("timestamp")
  private LocalDateTime timestamp;

  @JsonProperty("error_message")
  private K errorList;
}

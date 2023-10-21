package yukiii.mangayukiii.dto.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSuccessTemplate<K> {

  @JsonProperty("resp_code")
  private String respCode;

  @JsonProperty("resp_desc")
  private String respDesc;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("data")
  private K data;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("paging_info")
  private ResponsePagination pagingInfo;

  @JsonProperty("timestamp")
  private LocalDateTime timestamp;
}


package yukiii.mangayukiii.dto.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePagination {

  @JsonProperty("totalCurrentItems")
  private int totalCurrentItems;

  @JsonProperty("totalItems")
  private Long totalItems;

  @JsonProperty("currentPage")
  private int currentPage;

  @JsonProperty("totalPages")
  private int totalPages;

  public static <T> ResponsePagination createPagination(Page<T> page) {
    return ResponsePagination.builder()
        .totalCurrentItems(page.getNumberOfElements())
        .totalItems(page.getTotalElements())
        .currentPage(page.getNumber() + 1)
        .totalPages(page.getTotalPages())
        .build();
  }
}

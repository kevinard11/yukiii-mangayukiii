package yukiii.mangayukiii.dto.common.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PagingRequestMeta {

  private int page;
  private int size;
  private List<String> sorts;
}

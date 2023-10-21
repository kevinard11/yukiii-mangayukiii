package yukiii.mangayukiii.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import yukiii.mangayukiii.constant.exception.BadRequestException;
import yukiii.mangayukiii.constant.ErrorResponse;
import yukiii.mangayukiii.dto.common.request.PagingRequestMeta;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class PageUtil {

  public static final int NUMBER_OF_PARAM = 2;

  public static Sort.Direction getSortDirection(String direction) {
    if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    } else if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    }
    return Sort.Direction.ASC;
  }

  public static Pageable buildPagingAndSortQuery(int page, int size, List<String> sorts) {
    if (page < 1 || size < 1) {
      throw new BadRequestException(ErrorResponse.PAGE_SIZE_ERROR);
    }

    return (sorts == null || sorts.isEmpty())
            ? PageRequest.of(page - 1, size)
            : PageRequest.of(page - 1, size, Sort.by(PageUtil.parseSortParams(sorts)));
  }

  public static List<Sort.Order> parseSortParams(List<String> sorts) {
    List<Sort.Order> orders = new ArrayList<>();
    for (String sortParams : sorts) {
      if (StringUtils.isBlank(sortParams)) {
        continue;
      }

      String[] sortArr = sortParams.split("\\.");
      if (sortArr.length > NUMBER_OF_PARAM) {
        throw new InvalidParameterException("Invalid sort params");
      }
      if (sortArr.length == 1) {
        orders.add(new Sort.Order(PageUtil.getSortDirection("asc"), sortArr[0]));
      } else {
        orders.add(new Sort.Order(PageUtil.getSortDirection(sortArr[1]), sortArr[0]));
      }
    }
    return orders;
  }

  public static Pageable buildPagingAndSortQueryMeta(PagingRequestMeta pagingRequestMeta) {
    return PageUtil.buildPagingAndSortQuery(
            pagingRequestMeta.getPage(),
            pagingRequestMeta.getSize(),
            pagingRequestMeta.getSorts()
    );
  }
}

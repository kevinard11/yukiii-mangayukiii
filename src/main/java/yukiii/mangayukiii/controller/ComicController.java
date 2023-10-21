package yukiii.mangayukiii.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yukiii.mangayukiii.dto.comic.response.ComicResponseReadDto;
import yukiii.mangayukiii.dto.common.request.PagingRequestMeta;
import yukiii.mangayukiii.dto.common.response.ResponsePagination;
import yukiii.mangayukiii.dto.common.response.ResponseSuccessTemplate;
import yukiii.mangayukiii.service.ComicService;
import yukiii.mangayukiii.util.PageUtil;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "comic")
@Tag(name = "Comic", description = "Controller for comic")
public class ComicController {

  @Autowired
  private ComicService comicService;

  @RequestMapping(method = RequestMethod.GET)
  @Operation(summary = "Comic", operationId = "comic")
  public ResponseSuccessTemplate<List<ComicResponseReadDto>> getAllComics(
    @RequestParam(name = "search_key", required = false) String searchKey,
    @RequestParam(name = "page", defaultValue = "1", required = false) int page,
    @RequestParam(name = "size", defaultValue = "10", required = false) int size
  ){
    var paging = PageUtil.buildPagingAndSortQueryMeta(PagingRequestMeta.builder().page(page).size(size).build());
    var data = comicService.getAllComics(searchKey, paging);

    return ResponseSuccessTemplate.<List<ComicResponseReadDto>>builder()
            .respCode(String.valueOf(HttpStatus.OK.value())).respDesc(HttpStatus.OK.getReasonPhrase())
            .data(data.getContent()).pagingInfo(ResponsePagination.createPagination(data))
            .timestamp(LocalDateTime.now()).build();
  }
}

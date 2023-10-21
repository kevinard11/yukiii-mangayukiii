package yukiii.mangayukiii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import yukiii.mangayukiii.dao.entity.Comic;
import yukiii.mangayukiii.dao.repository.ComicRepository;
import yukiii.mangayukiii.dto.comic.response.ComicResponseReadDto;
import yukiii.mangayukiii.mapper.ComicMapper;
import yukiii.mangayukiii.util.SpecificationUtil;

import java.util.stream.Collectors;

@Service
public class ComicService {

  @Autowired
  private ComicRepository repository;

  public Page<ComicResponseReadDto> getAllComics(String searchKey, Pageable pageable) {

    Specification<Comic> specification = Specification
            .where(SpecificationUtil.likeComicId(searchKey))
            .or(SpecificationUtil.likeComicName(searchKey))
            .or(SpecificationUtil.likeComicAlternativeName(searchKey));

    var comics = repository.findAll(specification, pageable);

    return new PageImpl<>(
            comics.stream().map(ComicMapper.INSTANCE::entityToDto).collect(Collectors.toList()),
            comics.getPageable(),
            comics.getTotalElements()
    );
  }
}

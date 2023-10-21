package yukiii.mangayukiii.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import yukiii.mangayukiii.dao.entity.Comic;
import yukiii.mangayukiii.dto.comic.response.ComicResponseReadDto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ComicMapper {
  ComicMapper INSTANCE = Mappers.getMapper(ComicMapper.class);

  ComicResponseReadDto entityToDto(Comic comic);
}

package yukiii.mangayukiii.dto.comic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComicResponseReadDto {

  @JsonProperty(value = "comic_id")
  private Integer comicId;

  @JsonProperty(value = "name")
  private Integer name;

  @JsonProperty(value = "alternative_name")
  private Integer alternativeName;

  @JsonProperty(value = "synopsis")
  private Integer synopsis;
}

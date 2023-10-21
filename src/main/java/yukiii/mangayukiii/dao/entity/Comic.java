package yukiii.mangayukiii.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Where;
import yukiii.mangayukiii.dao.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Where(clause = "deleted = false")
public class Comic extends BaseEntity {

  @Id
  @Column(name = "comic_id")
  private Integer comicId;

  @Column(name = "name")
  private Integer name;

  @Column(name = "alternative_name")
  private Integer alternativeName;

  @Column(name = "synopsis")
  private Integer synopsis;
}

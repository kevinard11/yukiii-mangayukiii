package yukiii.mangayukiii.dao;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

  @CreationTimestamp
  @Column(updatable = false)
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  private ZonedDateTime updatedAt;

  private Boolean deleted = false;

  private ZonedDateTime deletedAt;
}

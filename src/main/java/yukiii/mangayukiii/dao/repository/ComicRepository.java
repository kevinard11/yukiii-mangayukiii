package yukiii.mangayukiii.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import yukiii.mangayukiii.dao.entity.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, String>, JpaSpecificationExecutor<Comic> {
}

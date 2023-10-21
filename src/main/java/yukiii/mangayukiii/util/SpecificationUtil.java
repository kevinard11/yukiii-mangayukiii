package yukiii.mangayukiii.util;

import org.springframework.data.jpa.domain.Specification;
import yukiii.mangayukiii.dao.entity.Comic;

public class SpecificationUtil {

  public static Specification<Comic> likeComicId(String comicId) {
    return comicId == null ? null : (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("comicId"), parseLike(comicId));
  }

  public static Specification<Comic> likeComicName(String comicName) {
    return comicName == null ? null : (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("comicName"), parseLike(comicName));
  }

  public static Specification<Comic> likeComicAlternativeName(String comicAlternativeName) {
    return comicAlternativeName == null ? null : (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("comicAlternativeName"), parseLike(comicAlternativeName));
  }

  private static String parseLike(String param) {
    return "%" + param + "%";
  }
}

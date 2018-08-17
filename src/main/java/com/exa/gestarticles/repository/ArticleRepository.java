package com.exa.gestarticles.repository;

import com.exa.gestarticles.domain.Article;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}

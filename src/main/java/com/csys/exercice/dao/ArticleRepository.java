package com.csys.exercice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csys.exercice.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	Article findByIdArticle(Long idArticle);
	Article findByDesignation(String designation);

}

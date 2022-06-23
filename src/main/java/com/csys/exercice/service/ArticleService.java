package com.csys.exercice.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csys.exercice.dao.ArticleRepository;
import com.csys.exercice.domain.Article;
import com.csys.exercice.dto.ArticleDto;
import com.csys.exercice.factory.ArticleFactory;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;

	@Transactional
	public ArticleDto findOne(Long id) {
		Article article = articleRepository.findByIdArticle(id);
		return ArticleFactory.articleTOArticleDTO(article);
	}

	public Collection<ArticleDto> findAll() {
		Collection<Article> result = articleRepository.findAll();
		return ArticleFactory.articlesToArticleDTOs(result);
	}

	public ArticleDto add(ArticleDto articleDTO) {
		Article article = ArticleFactory.articleDTOTOArticle(articleDTO);
		article = articleRepository.save(article);
		return ArticleFactory.articleTOArticleDTO(article);
	}
	 public void deleteArticle(Long id) {
	        articleRepository.deleteById(id);

	    }

}

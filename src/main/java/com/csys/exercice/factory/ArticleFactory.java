package com.csys.exercice.factory;

import java.util.ArrayList;
import java.util.Collection;

import com.csys.exercice.domain.Article;
import com.csys.exercice.dto.ArticleDto;

public class ArticleFactory {

	public static Article articleDTOTOArticle(ArticleDto articleDTO) {
		Article article = new Article();
		article.setIdArticle(articleDTO.getIdArticle());
		article.setDesignation_article(articleDTO.getDesignation());
		return article;
	}

	public static ArticleDto articleTOArticleDTO(Article article) {
		if (article != null) {
			ArticleDto articleDto = new ArticleDto();
			articleDto.setIdArticle(article.getIdArticle());
			articleDto.setDesignation(article.getDesignation_article());
			return articleDto;
		} else {
			return null;
		}
	}

	public static Collection<ArticleDto> articlesToArticleDTOs(Collection<Article> articles) {
		Collection<ArticleDto> articleDTOs = new ArrayList<>();
		for (Article article : articles) {
			ArticleDto articleDTO = articleTOArticleDTO(article);
			articleDTOs.add(articleDTO);
		}
		return articleDTOs;
	}

}

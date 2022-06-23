package com.csys.exercice.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.csys.exercice.dto.ArticleDto;
import com.csys.exercice.service.ArticleService;
import com.csys.exercice.util.RestPreconditions;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/api/articles")
public class ArticleResource {

	@Autowired
	ArticleService articleService;
	private static final String ENTITY_NAME = "Article";

	@GetMapping("/{id}")
	public ArticleDto findOne(@PathVariable Long id) {
		ArticleDto article = articleService.findOne(id);
		RestPreconditions.checkFound(article, ENTITY_NAME + "article not found");
		return article;
	}

	@GetMapping
	public Collection<ArticleDto> findAll() {
		return articleService.findAll();
	}

	@PostMapping
	public ResponseEntity<ArticleDto> addArticle(@Valid @RequestBody ArticleDto article) throws URISyntaxException {
		ArticleDto result = articleService.add(article);
		return ResponseEntity.created(new URI("/api/articles/add" + result.getIdArticle())).body(result);
	}

	@PutMapping
	public ResponseEntity<ArticleDto> updateArticle(@Valid @RequestBody ArticleDto articleDTO)
			throws MethodArgumentNotValidException {
		ArticleDto result = articleService.add(articleDTO);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteArticle(@Valid @PathVariable Long id) {
		articleService.deleteArticle(id);
		return ResponseEntity.ok().build();
	}

}

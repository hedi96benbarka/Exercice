package com.csys.exercice.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.csys.exercice.domain.Stock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StockRepository extends JpaRepository<Stock, Long>, QuerydslPredicateExecutor<Stock> {
 
	Stock findByIdStock (Long id);
	Stock findByDatePeremption(Date date );

	List<Stock> findAllByOrderByDatePeremptionAsc();

	List<Stock>findByArticle(Long idArticle);

	Collection<Stock> findByArticleAndDepot(Long idarticle, Long idDepot);

	@Override
	@EntityGraph(value = "article-depot-graph",type = EntityGraph.EntityGraphType.FETCH)
	Iterable<Stock> findAll(Predicate predicate);
}

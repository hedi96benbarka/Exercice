package com.csys.exercice.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.csys.exercice.domain.Stock;
import com.csys.exercice.dto.StockDto;

public class StockFactory {
	public static Stock stockDTOTOStock(StockDto stockdto) {
		Stock stock = new Stock();
		stock.setQte(stockdto.getQte());
		//stock.setIdStock(idStock);
		stock.setDatePeremption(stockdto.getDatePeremption());
		//stock.setArticle(stock.getArticle());
		//stock.setArticle(ArticleFactory.articleDTOTOArticle(stockdto.getArticleDto()));
		//stock.setDepot(DepotFactory.depotDTOTODepot(stockdto.getDepot()));
	//	stock.setDepot(stockdto.getIdDeepot());
		return stock;
	}

	public static StockDto stockTOStockDTO(Stock stock) {
		if (stock != null) {
			StockDto stockdto = new StockDto();
			stockdto.setIdStock(stock.getIdStock());
			
		//stockdto.setArticle(articleDto);(stock.getArticle());
		//	stockdto.setIdDeepot(stockdto.getIdDeepot());
			stockdto.setIdArticle(stock.getArticle().getIdArticle());
			stockdto.setIdDepot(stock.getDepot().getIdDepo());
			stockdto.setDesignation(stock.getArticle().getDesignation_article());
			stockdto.setDepot(stock.getDepot().getNomDepot());
			stockdto.setDatePeremption(stock.getDatePeremption());
			stockdto.setQte(stock.getQte());
			return stockdto;
		} else {
			return null;
		}
	}

	public static Collection<StockDto> stockToStockDTOs(Collection<Stock> stocks) {
		Collection<StockDto> stockdto = new ArrayList<>();
		for (Stock stock : stocks) {
			StockDto a = stockTOStockDTO(stock);
			System.out.println(a);
			stockdto.add(a);
		}
		return stockdto;
	}

}

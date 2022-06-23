package com.csys.exercice.service;

import com.csys.exercice.dao.StockRepository;
import com.csys.exercice.domain.QStock;
import com.csys.exercice.domain.Stock;
import com.csys.exercice.dto.ArticleDto;
import com.csys.exercice.dto.DepotDto;
import com.csys.exercice.dto.StockDto;
import com.csys.exercice.factory.ArticleFactory;
import com.csys.exercice.factory.DepotFactory;
import com.csys.exercice.factory.StockFactory;
import com.csys.exercice.util.RestPreconditions;
import com.csys.exercice.util.WhereClauseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class StockService {
    private static final String ENTITY_NAME = "Stock";
    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);
    @Autowired
    StockRepository stockrepro;
    @Autowired
    ArticleService articleService;
    @Autowired
    DepotService depotService;


    public StockDto findOne(Long id) {
        Stock stock = stockrepro.findByIdStock(id);
        return StockFactory.stockTOStockDTO(stock);
    }

    public Collection<StockDto> filterByIdarticle(Long id) throws Exception {
        Collection<StockDto> liste = StockFactory.stockToStockDTOs(stockrepro.findByArticle(id));
        return liste;

    }

    public Collection<StockDto> filterByIdArticleOrIdepot(Long idArticle, Long idDepot) {
        QStock qStock = QStock.stock;
        WhereClauseBuilder clauseBuilder = new WhereClauseBuilder()
                .optionalAnd(idArticle, () -> qStock.article().idArticle.eq(idArticle))
                .optionalAnd(idDepot, () -> qStock.depot().idDepo.eq(idDepot));
        Collection<Stock> result = (Collection<Stock>) stockrepro.findAll(clauseBuilder);
        Collection<StockDto> listeStockDto = StockFactory.stockToStockDTOs(result);
        return listeStockDto;
    }

    public Collection<StockDto> findAll() {
        Collection<Stock> result = stockrepro.findAll();

        return StockFactory.stockToStockDTOs(result);
    }

    public Collection<StockDto> findByIdepotAndIdArticle(Long idAtyicle, Long idDepot) throws Exception {
        Collection<StockDto> listStock = StockFactory.stockToStockDTOs(stockrepro.findAll());
        Collection<Stock> s=stockrepro.findByArticleAndDepot(idAtyicle,idDepot);
        Collection<StockDto> listStockDatePriasice = new ArrayList<>();
        /*for (StockDto stock : listStock) {
            if (stock.getDepot().getId() == idDepot && stock.getArticleDto().getIdArticle() == idAtyicle) {
                listStockDatePriasice.add(stock);
            }
        }*/
        listStockDatePriasice.add(StockFactory.stockTOStockDTO((Stock) s));
        return listStockDatePriasice;

    }

    public StockDto add(StockDto stock) {
        ArticleDto article=articleService.findOne(stock.getIdArticle());
     DepotDto depot=depotService.findOne(stock.getIdDepot());
        Stock stockadd = StockFactory.stockDTOTOStock(stock);
        stockadd.setArticle(ArticleFactory.articleDTOTOArticle(article));
        stockadd.setDepot(DepotFactory.depotDTOTODepot(depot));
       /// StockDto s=StockFactory.stockTOStockDTO(stockadd);
        StockDto s=StockFactory.stockTOStockDTO(stockrepro.save(stockadd));
        LOGGER.info(s.toString());
        return s;

    }

    public void deleteStock(Long id) {
        stockrepro.deleteById(id);

    }

    public int stockActuell(long idarticle, long iddepot) {
        Collection<Stock> result = stockrepro.findAll();
        int somme = result.stream().filter(x -> x.getArticle().getIdArticle() == idarticle && x.getDepot().getIdDepo() == iddepot).map(
                x -> x.getQte()).reduce(0, (a, b) -> a + b);

        return somme;
    }

    public Stock modifierquentite(long idarticle, long iddepot, int qnt) {
        Collection<Stock> result = stockrepro.findAll();
        for (Stock s : result) {
            if (s.getArticle().getIdArticle() == idarticle && s.getDepot().getIdDepo() == iddepot) {
                s.setQte(s.getQte() + qnt);

                return s;
            }

        }
        return null;

    }

    public Stock modifierquentiteAvecDate(long idarticle, long iddepot, int qnt, Date date) {
        Collection<Stock> result = stockrepro.findAll();
        for (Stock s : result) {
            System.out.println(s.getDatePeremption());
            System.out.println(s.getDatePeremption().compareTo(date));
            if (s.getArticle().getIdArticle() == idarticle && s.getDepot().getIdDepo() == iddepot && s.getDatePeremption().compareTo(date) == 0) {
                date.setHours(24);
                s.setQte(s.getQte() + qnt);
                s.setDatePeremption(date);
                return s;
            }
        }
        return null;

    }

    public Collection<StockDto> soustraireQuentite(long idarticle, long iddepot, int qnt) throws Exception {
        Collection<Stock> result = stockrepro.findAllByOrderByDatePeremptionAsc();

        Collection<Stock> listeArticleDepot = result.stream().
                filter(x -> x.getDepot().getIdDepo() == iddepot && x.getArticle().getIdArticle() == idarticle)
                .collect(Collectors.toCollection(ArrayList<Stock>::new));
        LOGGER.info(stockActuell(idarticle, iddepot) + "");
        if (qnt <= stockActuell(idarticle, iddepot)) {
            for (Stock se : listeArticleDepot) {

                if (se.getQte() > qnt) {
                    se.setQte(se.getQte() - qnt);
                    qnt = 0;
                } else {
                    qnt = qnt - se.getQte();
                    se.setQte(0);
                }
            }
        } else {
            LOGGER.info("insufficient quantity");
            RestPreconditions.checkFound(qnt, ENTITY_NAME + "insufficient quantity");
            throw new Exception("insufficient quantity");
        }

        Collection<StockDto> resultatList = new ArrayList<>();

       //resultatList = addStockAfterUpdate(listeArticleDepot);
        return StockFactory.stockToStockDTOs(listeArticleDepot);

    }

    public Collection<StockDto> addStockAfterUpdate(Collection<Stock> l) {
        Collection<StockDto> listStock = new ArrayList<>();
        for (Stock stock : l) {
            stock.getDatePeremption().setHours(24);
            StockDto stockDto = this.add(StockFactory.stockTOStockDTO(stock));
            listStock.add(stockDto);
        }
        return listStock;
    }

}

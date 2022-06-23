package com.csys.exercice.web.rest;

import com.csys.exercice.domain.Stock;
import com.csys.exercice.dto.StockDto;
import com.csys.exercice.factory.StockFactory;
import com.csys.exercice.service.StockService;
import com.csys.exercice.util.RestPreconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks")
public class StockResource {

    private static final String ENTITY_NAME = "Stock";
    private static final Logger LOGGER = LoggerFactory.getLogger(StockResource.class);

    @Autowired
    StockService stockService;

    @GetMapping("/{id}")
    public StockDto findOne(@PathVariable Long id) throws Exception {
        StockDto stock = stockService.findOne(id);
        StockDto stock2 = stockService.findOne(id);
        if (stock == null) {
            RestPreconditions.checkFound(stock, ENTITY_NAME + "Stock  not found");
            LOGGER.error(stock.toString());


        }
        LOGGER.info(stock.toString());

        return stock;
    }


    @PostMapping
    public ResponseEntity<StockDto> addDepot(@Valid @RequestBody StockDto stock) throws URISyntaxException {
        if (stock.getIdStock() != null) {
            RestPreconditions.checkFound(stock, ENTITY_NAME + "Post does not allow a stock with a id");
        }

        StockDto result = stockService.add(stock);
        return ResponseEntity.created(new URI("/api/stocks" + result.getQte())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDto> updateDepot(@PathVariable Long id, @Valid @RequestBody StockDto stock) throws MethodArgumentNotValidException {
        StockDto s = stockService.findOne(id);
        s.setQte(stock.getQte());
        s.setDatePeremption(s.getDatePeremption());
        s.setIdStock(stock.getIdStock());
        s.getDatePeremption().setHours(24);
        StockDto result = stockService.add(s);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepot(@PathVariable Long id) throws Exception {
        StockDto stock = stockService.findOne(id);
        LOGGER.info(stock.toString());
        RestPreconditions.checkFound(stock, ENTITY_NAME + " not found");
        stockService.deleteStock(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public Collection<StockDto> findAll(@RequestParam(required = false) Long idArticle, @RequestParam(required = false) Long idDepot) {
        System.out.println(idArticle);
        Collection<StockDto> listeStock = stockService.filterByIdArticleOrIdepot(idArticle, idDepot);
        return listeStock;
    }


    @GetMapping("/sum-quantity")
    public int calcul(@RequestParam Long idarticle, @RequestParam Long idDepot) {
        int sum = stockService.stockActuell(idarticle, idDepot);
        return sum;

    }


    @PostMapping("/add-quantity")
    public ResponseEntity<StockDto> addqte(@RequestParam Long idarticle, @RequestParam Long idDepot,
                                           @RequestParam int qnt, @RequestParam(name = "Date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
            throws URISyntaxException {
        System.out.println(idarticle);
        date.setHours(24);
        Stock stock = stockService.modifierquentiteAvecDate(idarticle, idDepot, qnt, date);
        System.out.println(stock.getQte());
        stock.getDatePeremption().setHours(24);
        //StockDto d = stockService.add(StockFactory.stockTOStockDTO(stock));
        return ResponseEntity.ok(StockFactory.stockTOStockDTO(stock));

    }


    @PostMapping("/subtract-quantity")
    public Collection<StockDto> soustraireQnte(@RequestParam Long idarticle, @RequestParam Long idDepot,
                                               @RequestParam int qnt) throws Exception {

        Collection<StockDto> listeStock = stockService.soustraireQuentite(idarticle, idDepot, qnt);
        LOGGER.info(listeStock.toString());
        return listeStock;

    }

}

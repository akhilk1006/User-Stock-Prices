package com.example.demo.stock;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/stocks")
public class StockServiceController {
	@Autowired
	RestTemplate restTemplate;
	public StockServiceController() {
	}
	
	@GetMapping("/{userName}")
	public List<Stock> getStocksByUserName(@PathVariable("userName") final String userName){
		String dbServiceURL = "http://db-service/quotes/"+userName; 
		ResponseEntity<List<String>> quoteResponse = this.restTemplate.exchange(dbServiceURL, HttpMethod.GET, 
				null, new ParameterizedTypeReference<List<String>>() {});
		List<String> quotes = quoteResponse.getBody();
		return quotes.stream().map(quote -> getStockPrice(quote)).collect(Collectors.toList());
	}

	private Stock getStockPrice(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			e.printStackTrace();
			return new Stock(quote);
		}
	}
	
}

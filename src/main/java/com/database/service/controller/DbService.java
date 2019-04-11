package com.database.service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.service.modal.Quote;
import com.database.service.modal.Quotes;
import com.database.service.repository.QuotesRepository;

@RestController
@RequestMapping(value="/rest/db")
public class DbService {
	//test
	@Autowired
	private QuotesRepository quotesRepository;
	
	@GetMapping(value="/getdata/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
	    return getQuotesByUserName(username);
	}
	
	@PostMapping(value="/add")
	public List<String> add(@RequestBody final Quotes quotes) {
	
	    quotes.getQuotes()
	            .stream()
	            .map(quote -> new Quote(quotes.getUserName(), quote))
	            .forEach(quote -> quotesRepository.save(quote));
	    return getQuotesByUserName(quotes.getUserName());
	}
	
	
	@PostMapping(value="/delete/{username}")
	public List<String> delete(@PathVariable("username") final String username) {
	
	    List<Quote> quotes = quotesRepository.findByUserName(username);
	    quotes.stream().forEach(quote->quotesRepository.deleteById(quote.getId()));
	    return getQuotesByUserName(username);
	}
	
	
	private List<String> getQuotesByUserName(@PathVariable("username") String username) {
	    return quotesRepository.findByUserName(username)
	            .stream()
	            .map(Quote::getQuote)
	            .collect(Collectors.toList());
	}
}


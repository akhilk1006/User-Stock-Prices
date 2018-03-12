package dbQuotes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbQuotes.model.Quote;

@RestController
@RequestMapping("/quotes")
public class DBQuoteController {
	
	private QuoteRepository quoteRepository;

	public DBQuoteController(QuoteRepository quoteRepository) {
		super();
		this.quoteRepository = quoteRepository;
	}
	
	public List<String> getQuotesByUserName(String userName){
		List<Quote> quotes = this.quoteRepository.findByUserName(userName);
        return quotes.stream().map(Quote::getQuote).collect(Collectors.toList());
	}
	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String userName){
		return getQuotesByUserName(userName);
	}
	
	@PostMapping("/{username}")
	public List<String> addQuote(@RequestBody Quotes quotes){
		quotes.getQuotes().stream().forEach(quote -> quoteRepository.save(new Quote(quotes.getUserName(),quote)));
		return getQuotesByUserName(quotes.getUserName());
	}
	
	@PostMapping("/delete/{userName}")
	public List<String> deleteQuote(@PathVariable("userName") final String userName){
		List<Quote> quotes = this.quoteRepository.findByUserName(userName);
		this.quoteRepository.deleteAll(quotes);
		return getQuotesByUserName(userName);
	}
	
}

package dbQuotes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dbQuotes.model.Quote;

public interface QuoteRepository extends CrudRepository<Quote, Integer>{
	public List<Quote> findByUserName(String userName);
}

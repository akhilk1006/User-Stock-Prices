package dbQuotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="quote_id")
	private int Id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_quote")
	private String quote;

	public Quote() {
		
	}

	public Quote(String userName, String quote) {
		this.userName = userName;
		this.quote = quote;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
	
}

package repository.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "findItemsByUser", query = "SELECT i FROM Item i WHERE i.user = :user"),
		@NamedQuery(name = "getItemCountByUser", query = "SELECT COUNT(i) FROM Item i WHERE i.user =:user") })
@Table(name = "items")
public class Item implements Serializable {

	private static final long serialVersionUID = -7601072572981272676L;

	public static final String QUERY_FIND_ITEMS_BY_USER = "findItemsByUser";
	public static final String QUERY_GET_ITEM_COUNT_BY_USER = "getItemCountByUser";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String description;

	@Column(name = "opening_date")
	private Timestamp openingDate;

	@Column(name = "closing_date")
	private Timestamp closingDate;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@Column(name = "initial_price")
	private Double initialPrice;

	@Column
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Timestamp openingDate) {
		this.openingDate = openingDate;
	}

	public Timestamp getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Timestamp closingDate) {
		this.closingDate = closingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

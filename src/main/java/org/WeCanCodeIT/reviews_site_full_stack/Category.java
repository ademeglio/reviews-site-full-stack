package org.WeCanCodeIT.reviews_site_full_stack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@ManyToOne
	private Review review;

	// Constructors
	protected Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public long getId() {
		return id;
	}
	
	public Review getReview() {
		return review;
	}

	// hashCode() & equals() for entity id
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}


	

} // End Category()

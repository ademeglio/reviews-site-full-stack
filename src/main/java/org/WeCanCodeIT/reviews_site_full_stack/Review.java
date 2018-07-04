package org.WeCanCodeIT.reviews_site_full_stack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Review {

	// Instance Variables
	@Id
	@GeneratedValue
	private Long id; // Review ID as Long
	
	private String title; // Review Title
	private String imageUrl; // Review URL Link to static image of object being reviewed
	
	@Basic(fetch=FetchType.LAZY)
    @Lob
	private String content; // Review content
	
	private String companyUrl; // Web site of company being reviewed
	
	@ManyToOne
	private Category category; // Review category
	
	@ManyToMany
	private Collection<Tag>	tags;

	//Constructor
	protected Review() { } //JPA Default Constructor
	
	public Review(String reviewTitle, String reviewImageUrl, 
			String reviewContent, String companyUrl, Category category, Tag...tags) {
		this.title = reviewTitle;
		this.imageUrl = reviewImageUrl;
		this.content = reviewContent;
		this.companyUrl = companyUrl;
		this.category = category;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}

	// Getters
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getCompanyUrl() {
		return companyUrl;
	}

	public Category getCategory() {
		return category;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}

	// hashCode() & equals() for entity id
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Review other = (Review) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	

	

	

}  // End Review()

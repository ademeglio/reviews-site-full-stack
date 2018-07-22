package org.WeCanCodeIT.reviews_site_full_stack;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {

	// Instance Variables
	@Id
	@GeneratedValue
	private long id;
	
	@Basic(fetch=FetchType.LAZY)
	@Lob
	private String commentContent;
	
	@JsonIgnore
	@ManyToOne
	private Review review;

	private String commenter; // Person authoring the comment

	// Constructors
	protected Comment() {} // JPA Default Constructor
	
	public Comment(String commentContent, String commenter, Review review) {
		this.commentContent = commentContent;
		this.commenter = commenter;
		this.review = review;
	}
	
	// Getters
	public long getId() {
		return id;
	}
	
	public String getCommentContent() {
		return commentContent;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

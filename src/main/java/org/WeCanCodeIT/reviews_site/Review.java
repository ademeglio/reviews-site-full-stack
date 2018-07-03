package org.WeCanCodeIT.reviews_site;

public class Review {

	// Instance Variables
	private Long id; // Review ID as Long
	private String title; // Review Title
	private String url; // Review URL Link to static image of object being reviewed
	private String category; // Review category
	private String content; // Review content
	private String companyUrl; // Web site of company being reviewed

	//Constructor
	public Review(Long courseID, String reviewTitle, String reviewImageUrl, 
			String reviewCategory, String reviewContent, String companyUrl) {
		this.id = courseID;
		this.title = reviewTitle;
		this.url = reviewImageUrl;
		this.category = reviewCategory;
		this.content = reviewContent;
		this.companyUrl = companyUrl;
	}

	// Getters
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getImageUrl() {
		return url;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getCompanyUrl() {
		return companyUrl;
	}

}  // End Review()

package org.WeCanCodeIT.reviews_site;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class ReviewRepositoryTest {

	ReviewRepository underTest;
	
	private Long reviewId = 1L;
	private Review review = new Review(reviewId, "review title", "url", "category", "content","coUrl1");
	
	private Long secondReviewId;
	private Review secondReview = new Review(secondReviewId, "review title", "url", "category", "content", "coUrl2");
	
	
	@Test
	public void shouldFindAReview() {
		// Setup
		underTest = new ReviewRepository(review);
		// Act
		Review result = underTest.findOne(reviewId);
		// Assert
		assertThat(result, is(review));
	}
	
	@Test
	public void shouldFindASecondReview() {
		// Setup
		underTest = new ReviewRepository(secondReview);
		// Act
		Review result = underTest.findOne(secondReviewId);
		// Assert
		assertThat(result, is(secondReview));
	}
	
	@Test
	public void shouldFindAllReviews() {
		// Setup
		underTest = new ReviewRepository(review, secondReview);
		// Act
		Collection<Review> result = underTest.findAll();
		// Assert
		assertThat(result, containsInAnyOrder(review, secondReview));
		
	}
} // End ReviewRepositoryTest()

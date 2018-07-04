package org.WeCanCodeIT.reviews_site_full_stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {
	
	// Setup Resources
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	
	
	
	// Category Tests
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("category"));
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	
	// Review Tests
	
	@Test
	public void shouldSaveAndLoadReview() {
		Category replica = categoryRepo.save(new Category("replicas"));
		Review review = reviewRepo.save(new Review("reviewTitle", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getTitle(), is("reviewTitle"));
	}
	
	@Test
	public void shouldEstablishCategoryToReviewRelationship() {
		Category replica = categoryRepo.save(new Category("replicas"));
		
		Review review = reviewRepo.save(new Review("reviewTitle", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getCategory(), is(replica));
		
	} 
	
	@Test
	public void shouldFindReviewsFoCategory() {
		Category replica = categoryRepo.save(new Category("replicas"));
		
		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		Review review2 = reviewRepo.save(new Review("reviewTitle2", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoryContains(replica);
		assertThat(reviewsForCategory, containsInAnyOrder(review1, review2));
	}

	
} // End JPAMappingsTest()

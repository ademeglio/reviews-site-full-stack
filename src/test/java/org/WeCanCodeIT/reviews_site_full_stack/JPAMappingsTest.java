package org.WeCanCodeIT.reviews_site_full_stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Date;
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
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	// Category Tests
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category", "description"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("category"));
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category", "description"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	
	// Review Tests
	
	@Test
	public void shouldSaveAndLoadReview() {
		Category replica = categoryRepo.save(new Category("replicas", "description"));
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
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		
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
	public void shouldFindReviewsForCategory() {
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		
		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		Review review2 = reviewRepo.save(new Review("reviewTitle2", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategory(replica);
		assertThat(reviewsForCategory, containsInAnyOrder(review1, review2));
	}
	
	// Tags Test
	
	@Test
	public void shouldSaveAndLoadTags() {
		Tag tag = tagRepo.save(new Tag("tag_name"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getName(), is("tag_name"));
	}
	
	@Test
	public void shouldGenerateTagId() {
		Tag tag = tagRepo.save(new Tag("tag_name"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(tagId, is (greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishReviewToTagRelationship() {
		// Tag is not the owner, so we must create tags first
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		Tag tag2 = tagRepo.save(new Tag("tag2"));
		
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		
		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica, tag1, tag2));
		long reviewId = review1.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review1 = result.get();
		assertThat(review1.getTags(), containsInAnyOrder(tag1,tag2));
	}
	
	@Test
	public void shouldFindReviewsForTag() {
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		
		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica, tag1));
		Review review2 = reviewRepo.save(new Review("reviewTitle2", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica, tag1));
		
		Collection<Review> reviewsForTag = reviewRepo.findByTags(tag1);
		
		assertThat(reviewsForTag, containsInAnyOrder(review1, review2));
	}

	@Test
	public void shouldFindReviewsForTagId() {
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		long tagId = tag1.getId();
		
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		
		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica, tag1));
		Review review2 = reviewRepo.save(new Review("reviewTitle2", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica, tag1));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForTopic = reviewRepo.findByTagsId(tagId);
		
		assertThat(reviewsForTopic, containsInAnyOrder(review1, review2));
	}
	
	// Comments Tests
	
	@Test
	public void shouldSaveAndLoadComment() {
		// Need a category to add to review
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		// Need a review to add comment to for testing
		Review review = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		
		Comment comment = commentRepo.save(new Comment("user_comment", "commenter", review ));
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Comment> result = commentRepo.findById(commentId);
		comment = result.get();
		assertThat(comment.getCommentContent(), is("user_comment"));
	}
	
	@Test
	public void shouldGenerateCommentId() {
		// Need a category to add to review
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		// Need a review to add comment to for testing
		Review review = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
			
		
		Comment comment = commentRepo.save(new Comment("user_comment","commenter",review));
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(commentId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishCommentToReviewRelationship() {
		
		Category replica = categoryRepo.save(new Category("replicas", "description"));
		
		Review review = reviewRepo.save(new Review("reviewTitle1", "reviewImageUrl",
				"reviewContent", "reviewedCompanyUrl", replica));
		
		// Add comments to review
		Comment comment1 = commentRepo.save(new Comment("user_comment1","commenter", review));
		Comment comment2 = commentRepo.save(new Comment("user_comment2","commenter", review));
		
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();	
		assertThat(review.getComments(), containsInAnyOrder(comment1,comment2));
	}
	
} // End JPAMappingsTest()

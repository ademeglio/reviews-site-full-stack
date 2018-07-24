package org.WeCanCodeIT.reviews_site_full_stack;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewRestController {
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource 
	private TagRepository tagRepo;
	
	@GetMapping("/{id}/tags")
	public Iterable<Tag> findAllTagsByReview(
			@PathVariable("id") Long reviewId) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		return tagRepo.findByReviews(review);
	}
	
	@PutMapping("/add-new-tag")
	public Iterable<Tag> addNewReviewTag(
			@RequestBody TagUpdateRequest addNewTag
		){
		
		Optional<Review> reviewOptional = reviewRepo.findById(addNewTag.reviewId);
		
		// If the review exists, check to see if tag exists.
		
		if (reviewOptional.isPresent()) {
			Optional<Tag> tagOptional = tagRepo.findFirstByName(addNewTag.tagName);
			Tag createdTag;
			
			// If the tag doesn't exist, create it.
			if(!tagOptional.isPresent()) {
				createdTag = tagRepo.save(new Tag(addNewTag.tagName));
			} else {
				createdTag = tagOptional.get();
			}
			
			// The tag exists, just add it if it doesn't exist in the review.
			Review review = reviewOptional.get();
			Collection<Tag> reviewTags = review.getTags();
			
			if (!reviewTags.contains(createdTag)) {
				reviewTags.add(createdTag);
				reviewRepo.save(review);
			}	

		}
		return tagRepo.findByReviews(reviewOptional);
	}
	
	@DeleteMapping("/removetag")
	public Iterable<Tag> removeTag (
			@RequestBody TagUpdateRequest removeTag
			){
		Optional<Tag> tagOptional = tagRepo.findFirstByName(removeTag.tagName);
		Tag tag = tagOptional.get();
		Optional<Review> reviewOptional = reviewRepo.findById(removeTag.reviewId);
		Review review = reviewOptional.get();
		
		try {
			review.deleteTag(tag);
			reviewRepo.save(review);
		} catch (Exception e) {
			// TODO: Report error if item didn't exist
		}
		return tagRepo.findByReviews(reviewOptional);
	}
} // End ReviewRestController()

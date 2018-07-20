package org.WeCanCodeIT.reviews_site_full_stack;

import java.util.Optional;

import javax.annotation.Resource;

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
	public Iterable<Tag> findALlTagsByReview(
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
			
			// If the tag doesn't exist, create it.
			if(!tagOptional.isPresent()) {
				Tag createdTag = tagRepo.save(new Tag(addNewTag.tagName));
				Review review = reviewOptional.get();
				review.addTag(createdTag);
				reviewRepo.save(review);
			}	
		}
		return tagRepo.findByReviews(reviewOptional);
	}
	
	
	
} // End ReviewRestController()

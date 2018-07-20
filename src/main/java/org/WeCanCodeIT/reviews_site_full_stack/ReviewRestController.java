package org.WeCanCodeIT.reviews_site_full_stack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.swing.text.html.Option;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/{id}/add-new-tag/{tag-name}")
	public Iterable<Tag> addNewReviewTag(
			@PathVariable("id") Long reviewId,
			@PathVariable("tag-name") String tagName,
			Model model){
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		
		if (reviewResult.isPresent()) {
			Optional<Tag> tagResult = tagRepo.findFirstByName(tagName);
			
			if(!tagResult.isPresent()) {
				Tag createdTag = tagRepo.save(new Tag(tagName));
				Review review = reviewResult.get();
				review.addTag(createdTag);
				reviewRepo.save(review);
			}	
		}
		return tagRepo.findByReviews(reviewResult);
	}
	
	
	
} // End ReviewRestController()

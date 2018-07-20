package org.WeCanCodeIT.reviews_site_full_stack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.swing.text.html.Option;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
} // End ReviewRestController()

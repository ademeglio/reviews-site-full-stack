package org.WeCanCodeIT.reviews_site_full_stack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ReviewController {
	@Resource
	ReviewRepository reviewRepo;
	
	@Resource
	CategoryRepository categoryRepo;
	
	@Resource
	TagRepository tagRepo;
	
	@Resource
	CommentRepository commentRepo;
	
	
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value="id") Long reviewId, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(reviewId);
		
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
	}
	
	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) { 
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value="id") long categoryId, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(categoryId);
		
		if(category.isPresent()) {
			model.addAttribute("categories", category.get());
			return "category";
		}
		throw new CategoryNotFoundException();
	}

	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";
	}

	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value="id")long tagId, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(tagId);
		
		if(tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			return "tag";
		}
		throw new TagNotFoundException();
	}

	@RequestMapping("/show-tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";
	}
	
	// Add a newly created comment directly to a specific review using an HTML form.
	@PostMapping("/new-comment")
	public RedirectView createCommentForReview(
			@RequestParam(value="commentContent") String commentContent,
			@RequestParam(value="commenter") String commenter,
			@RequestParam(value="id") Long reviewId,
			Model model) {
		
		Optional<Review> reviewOptional = reviewRepo.findById(reviewId);
		
		
		if (reviewOptional.isPresent()) {
			Review review = reviewOptional.get();
			Comment createdComment = commentRepo.save(new Comment(commentContent, commenter, review));
			reviewRepo.save(review);
		}
		return new RedirectView("/review?id=" + reviewId);
	}
	
	/*
	 * The PostMapping and DeleteMapping are from the second iteration where I used a form method to add and delete tags.
	 * In the third iteration, adding and deleting of tags is performed using AJAX
	 */
	
//	// Add a newly created tag directly to a specific review using a form.
//	@PostMapping("/new-tag")
//	public RedirectView createTagForReview(
//		@RequestParam(value="tagName") String tagName,
//		@RequestParam(value="reviewId") Long reviewId,
//		Model model) {
//		
//		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
//		
//		if (reviewResult.isPresent()) {
//			Tag createdTag = tagRepo.save(new Tag(tagName));
//			Review review = reviewResult.get();
//			review.addTag(createdTag);
//			reviewRepo.save(review);
//		}
//
//		return new RedirectView("/review?id=" + reviewId);
//	}
//	
//	@PostMapping("/remove-tag-from-review")
//	public RedirectView removeTagForReview(
//			@RequestParam(value="tagName") String tagName,
//			@RequestParam(value="reviewId") Long reviewId,
//			Model model) {
//			
//			Optional<Review> reviewResult = reviewRepo.findById(reviewId);
//			
//			if (reviewResult.isPresent()) {
//				Review review = reviewResult.get();
//				
//				Optional<Tag> tag = tagRepo.findFirstByName(tagName);
//				
//				review.deleteTag(tag.get());
//				reviewRepo.save(review);
//			}
//			
//			return new RedirectView("/review?id=" + reviewId);
//	}
	
	
} // End ReviewController()

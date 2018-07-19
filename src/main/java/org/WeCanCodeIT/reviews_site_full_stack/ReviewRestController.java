package org.WeCanCodeIT.reviews_site_full_stack;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/review")
public class ReviewRestController {
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource TagRepository tagRepo;
	
	@GetMapping("/tags")
	public Iterable<Tag> findAllTags() {
		return tagRepo.findAll();
	}
	
} // End ReviewRestController()

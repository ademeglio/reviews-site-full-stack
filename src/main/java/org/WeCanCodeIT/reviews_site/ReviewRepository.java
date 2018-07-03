package org.WeCanCodeIT.reviews_site;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {

	private Map<Long, Review> reviewList = new HashMap<>();
	
	// Build Database
	public ReviewRepository() {
		Review review1 = new Review(1L, "Factory Five Racing GTM",
										"/images/FFR-GTM.jpg", 
										"Sports Cars", 
										"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum rhoncus sagittis aliquet."
										+ " Vivamus viverra, quam vel lacinia porta, elit diam vulputate massa, et suscipit nunc erat "
										+ "eu diam. Nunc mollis ac ex eu rutrum. Cras maximus nec augue eget sagittis. Donec fermentum "
										+ "neque est, nec semper enim placerat id. Nam id convallis purus. Pellentesque et tellus eu "
										+ "metus varius luctus. Vestibulum tincidunt dui ut vehicula maximus. Duis at est id lectus finibus maximus.",
										"https://www.factoryfive.com");
		
		Review review2 = new Review(2L, "Sterling Sports Cars Nova",
										"/images/Sterling-Nova.jpg", 
										"Sports Cars", 
										"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum rhoncus sagittis aliquet."
										+ " Vivamus viverra, quam vel lacinia porta, elit diam vulputate massa, et suscipit nunc erat "
										+ "eu diam. Nunc mollis ac ex eu rutrum. Cras maximus nec augue eget sagittis. Donec fermentum "
										+ "neque est, nec semper enim placerat id. Nam id convallis purus. Pellentesque et tellus eu "
										+ "metus varius luctus. Vestibulum tincidunt dui ut vehicula maximus. Duis at est id lectus finibus maximus.",
										"http://sterlingsportscar.com");
		
		Review review3 = new Review(3L, "Brunton Auto Stalker V6 Clubman",
										"/images/BruntonAuto-StalkerV6.jpg", 
										"Replica", 
										"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum rhoncus sagittis aliquet."
										+ " Vivamus viverra, quam vel lacinia porta, elit diam vulputate massa, et suscipit nunc erat "
										+ "eu diam. Nunc mollis ac ex eu rutrum. Cras maximus nec augue eget sagittis. Donec fermentum "
										+ "neque est, nec semper enim placerat id. Nam id convallis purus. Pellentesque et tellus eu "
										+ "metus varius luctus. Vestibulum tincidunt dui ut vehicula maximus. Duis at est id lectus finibus maximus.",
										"http://stalkercars.com");
		
		// add reviews to repository
		reviewList.put(review1.getId(), review1);
		reviewList.put(review2.getId(), review2);
		reviewList.put(review3.getId(), review3);
	}
	
	
	// Constructor for testing
	public ReviewRepository(Review...reviews ) {
		for (Review review : reviews) {
			reviewList.put(review.getId(), review);
		}
	}
	
	// Methods
	public Review findOne(Long reviewID) {
		return reviewList .get(reviewID);
	}

	public Collection<Review> findAll() {
		return reviewList.values();
	}

} // End ReviewRepository()

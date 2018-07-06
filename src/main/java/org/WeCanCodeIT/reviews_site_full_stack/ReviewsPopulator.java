package org.WeCanCodeIT.reviews_site_full_stack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewsPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// Create Categories
		Category replicaVehicle = categoryRepo.save(new Category("Replicas", 
				"Replicas are vehicles usually assembled from components "
				+ "such as from a single vehicle, multiple vehicles, a kit, "
				+ "parts, or fabricated components. The vehicle resembles "
				+ "a vehicle of distinctive name, line-make, model or "
				+ "type as produced by a licensed manufacturer or manufacturer "
				+ "no longer in business."));
		Category kitVehicle = categoryRepo.save(new Category("Kits",
				"Kits are vehicles assembed from components such as from a "
				+ "single vehicle, multiple vehicles, parts, or fabricated "
				+ "components. The vehicle is of original design with the "
				+ "intention of being built by the end user or by someone "
				+ "other than the kit manufacturer for resale."));
		
		// Create Tags
		Tag sportsCar = tagRepo.save(new Tag("sports-car"));
		Tag handBuilt = tagRepo.save(new Tag("hand-built"));
		Tag componentCar = tagRepo.save(new Tag("component car"));
		Tag midEngine = tagRepo.save(new Tag("mid-engine"));
		Tag vintageDesign = tagRepo.save(new Tag("vintage design"));
		Tag superCar = tagRepo.save(new Tag("super-car"));
		
		// Create Reviews
		Review factoryFiveRacingGTM = reviewRepo.save(new Review("Factory Five Racing GTM",
				"/images/FFR-GTM.jpg", // image location
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum rhoncus sagittis aliquet."
				+ " Vivamus viverra, quam vel lacinia porta, elit diam vulputate massa, et suscipit nunc erat "
				+ "eu diam. Nunc mollis ac ex eu rutrum. Cras maximus nec augue eget sagittis. Donec fermentum "
				+ "neque est, nec semper enim placerat id. Nam id convallis purus. Pellentesque et tellus eu "
				+ "metus varius luctus. Vestibulum tincidunt dui ut vehicula maximus. Duis at est id lectus finibus maximus.",
				"https://www.factoryfive.com", // Company URL
				kitVehicle, // Category
				sportsCar, componentCar, handBuilt, midEngine, superCar)); // Tags
		
		Review sterlingSportsCarsNova = reviewRepo.save(new Review("Sterling Sports Cars Nova",
				"/images/Sterling-Nova.jpg", // image location
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum rhoncus sagittis aliquet."
				+ " Vivamus viverra, quam vel lacinia porta, elit diam vulputate massa, et suscipit nunc erat "
				+ "eu diam. Nunc mollis ac ex eu rutrum. Cras maximus nec augue eget sagittis. Donec fermentum "
				+ "neque est, nec semper enim placerat id. Nam id convallis purus. Pellentesque et tellus eu "
				+ "metus varius luctus. Vestibulum tincidunt dui ut vehicula maximus. Duis at est id lectus finibus maximus.",
				"http://sterlingsportscars.com", // Company URL
				kitVehicle, // Category
				sportsCar, componentCar, handBuilt)); // Tags
		
		Review bruntonAutoStalkerV6Clubman = reviewRepo.save(new Review("Brunton Auto Stalker V6 Clubman",
				"/images/BruntonAuto-StalkerV6.jpg",  // image location
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum rhoncus sagittis aliquet."
				+ " Vivamus viverra, quam vel lacinia porta, elit diam vulputate massa, et suscipit nunc erat "
				+ "eu diam. Nunc mollis ac ex eu rutrum. Cras maximus nec augue eget sagittis. Donec fermentum "
				+ "neque est, nec semper enim placerat id. Nam id convallis purus. Pellentesque et tellus eu "
				+ "metus varius luctus. Vestibulum tincidunt dui ut vehicula maximus. Duis at est id lectus finibus maximus.",
				"http://stalkercars.com", // Company URL
				replicaVehicle, // Category
				componentCar, handBuilt, sportsCar, vintageDesign)); // Tags
	}
	
}

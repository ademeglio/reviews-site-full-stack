package org.WeCanCodeIT.reviews_site;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static java.util.Arrays.asList;

import java.util.Collection;


@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewsControllerMockMvcTest {

	@Resource
	private MockMvc mvc;
	
	@Mock
	private Review firstReview;
	
	@Mock
	private Review secondReview;
	
	@MockBean
	private ReviewRepository repository;
	
	// Testing for all reviews
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review>allReviews = asList(firstReview, secondReview);
		when(repository.findAll()).thenReturn(allReviews);
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	// Testing for single review
	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToOneReviewView() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldPutOneReviewIntoModel() throws Exception {
		when(repository.findOne(1L)).thenReturn(firstReview);
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews", is(firstReview)));
	}
	
}

package org.WeCanCodeIT.reviews_site_full_stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReviewRestControllerTest {
	@Resource
	private TestRestTemplate restTemplate;
	
	@Test
	public void shouldBeOKForReviewTags() {
		ResponseEntity<String> response = restTemplate.getForEntity("/review/8/tags", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	
	@Test
	public void shouldBeOkForAddNewTag() throws URISyntaxException {
		// URI for request
		URI putUri = new URI("/review/add-new-tag");
		
		// JSON to send
		String json = "{\"reviewId\":\"8\", \"tagName\":\"super car\"}";
		
		// Build request out of URI and form
		RequestEntity<String> request = 
				RequestEntity.put(putUri) // PUT Request to this URI
				.header(", headerValues)
	}

}

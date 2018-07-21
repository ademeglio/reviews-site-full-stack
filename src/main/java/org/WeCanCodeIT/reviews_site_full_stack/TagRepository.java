package org.WeCanCodeIT.reviews_site_full_stack;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Optional<Tag> findFirstByName(String tagName);

	Collection<Tag> findByReviews(Optional<Review> review);

	Tag findByName(String tagName);

}

package org.WeCanCodeIT.reviews_site_full_stack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategory(Category category);

	Collection<Review> findByTags(Tag tag);

	Collection<Review> findByTagsId(long tagId);

}

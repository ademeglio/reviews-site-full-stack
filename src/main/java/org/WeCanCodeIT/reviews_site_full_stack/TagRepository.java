package org.WeCanCodeIT.reviews_site_full_stack;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Optional<Tag> findByName(String tagName);

}

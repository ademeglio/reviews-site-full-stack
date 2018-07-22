package org.WeCanCodeIT.reviews_site_full_stack;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}

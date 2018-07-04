package org.WeCanCodeIT.reviews_site_full_stack;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class TagNotFoundException extends Exception {

}

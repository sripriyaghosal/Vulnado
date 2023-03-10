package com.scalesec.vulnado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.List;
import java.io.Serializable;

/**
 * It's a Spring Boot application that exposes a REST endpoint at /comments
 */
@RestController
@EnableAutoConfiguration
public class CommentsController {
  @Value("${app.secret}")
  private String secret;

  /**
   * "If the user is authenticated, return all comments."
   *
   * The first line is a Java annotation that tells the server to allow cross-origin requests. This is necessary because
   * the frontend is running on a different port than the backend
   *
   * @param token The token that was generated by the login endpoint.
   * @return A list of comments
   */
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/comments", method = RequestMethod.GET, produces = "application/json")
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
    User.assertAuth(secret, token);
    return Comment.fetch_all();
  }

  /**
   * "Create a new comment with the given username and body, and return it."
   *
   * The @CrossOrigin annotation is a Spring annotation that allows us to configure CORS. In this case, we're allowing all
   * origins to access this endpoint
   *
   * @param token The token that was generated when the user logged in.
   * @param input The input object that will be passed in the request body.
   * @return A Comment object.
   */
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/comments", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
    return Comment.create(input.username, input.body);
  }

  /**
   * Delete a comment by id.
   *
   * @param token The token that was returned when the user logged in.
   * @param id The id of the comment to delete
   * @return Boolean
   */
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE, produces = "application/json")
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
    return Comment.delete(id);
  }
}

/**
 * It's a class that represents a request to create a comment
 */
class CommentRequest implements Serializable {
  public String username;
  public String body;
}

/**
 * It's a custom exception class that extends RuntimeException and is annotated with
 * @ResponseStatus(HttpStatus.BAD_REQUEST)
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequest extends RuntimeException {
  public BadRequest(String exception) {
    super(exception);
  }
}

/**
 * It's a RuntimeException that returns a 500 HTTP status code
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ServerError extends RuntimeException {
  public ServerError(String exception) {
    super(exception);
  }
}

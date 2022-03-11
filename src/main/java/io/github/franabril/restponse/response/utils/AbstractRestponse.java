package io.github.franabril.restponse.response.utils;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author ffrannabril@gmail.com
 */
public abstract class AbstractRestponse {

  private static final String STATUS_ERROR = "Status can't be NULL!";
  private static final String MESSAGE_ERROR = "Message can't be NULL!";

  private Object message;
  private Status status;

  private AbstractRestponse() {
    // empty constructor
  }

  public AbstractRestponse(Status status) {
    this();
    if (status == null) {
      throw new InternalServerErrorException(STATUS_ERROR);
    }
    this.status = status;
  }

  public AbstractRestponse(Object message, Status status) {
    this(status);
    if (message == null) {
      throw new InternalServerErrorException(MESSAGE_ERROR);
    }
    this.message = message;
  }

  protected Response build() {
    return Response.status(status).entity(message).build();
  }
}

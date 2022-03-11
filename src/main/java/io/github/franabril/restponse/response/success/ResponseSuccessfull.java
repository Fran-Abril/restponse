package io.github.franabril.restponse.response.success;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.github.franabril.restponse.response.utils.AbstractRestponse;

/**
 * <b>Family</b> SUCCESSFUL
 *
 * <p>
 * {@code 2xx} HTTP status codes.
 *
 *
 * @see <a href=
 *      "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status">HTTP/1.1
 *      documentation</a>}
 * @author ffrannabril@gmail.com
 */
public class ResponseSuccessfull extends AbstractRestponse {

  /**
   * Build a ResponseSuccessfull with family <b>SUCCESSFUL</b>
   *
   * @param entity your DTO object
   * @param status code
   */
  public ResponseSuccessfull(Object entity, Status status) {
    super(entity, status);
  }

  /**
   * Create a <b>Success Response</b> instance from the current ResponseBuilder.
   * The builderis reset
   * to a blank state equivalent to calling the ok method.
   *
   * @return javax.ws.rs.core.Response
   */
  public Response getResponse() {
    return this.build();
  }
}

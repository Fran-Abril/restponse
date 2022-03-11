package io.github.franabril.restponse.response.error.server;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.github.franabril.baseexception.BaseException;
import io.github.franabril.restponse.response.utils.BaseResponseError;

/**
 *
 * @author ffrannabril@gmail.com
 */
public class ResponseServerError extends BaseResponseError {

  public ResponseServerError(BaseException exception, Status status) {
    super(exception, status);
  }

  public Response getResponse() {
    return this.build();
  }
}

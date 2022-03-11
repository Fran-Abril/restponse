package io.github.franabril.restponse.response.error.client;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.github.franabril.baseexception.BaseException;
import io.github.franabril.restponse.response.utils.BaseResponseError;

/**
 *
 * @author ffrannabril@gmail.com
 */
public class ResponseClientError extends BaseResponseError {

  public ResponseClientError(BaseException exception, Status status) {
    super(exception, status);
  }

  public Response getResponse() {
    return this.build();
  }
}

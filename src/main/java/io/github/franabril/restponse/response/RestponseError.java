package io.github.franabril.restponse.response;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;

import io.github.franabril.baseexception.BaseException;
import io.github.franabril.baseexception.IError;
import io.github.franabril.restponse.exception.RestponseException;
import io.github.franabril.restponse.response.error.client.ResponseClientError;
import io.github.franabril.restponse.response.error.server.ResponseServerError;

/**
 *
 * In the Rest layout, catch all exceptions and throw this
 * <p>
 * <b>NOTE:</b> The Status with StatusType
 * !(javax.ws.rs.core.FAMILY.CLIENT_ERROR or SERVER_ERROR) do not use here
 *
 * @author ffrannabril@gmail.com
 */
public class RestponseError extends WebApplicationException {

  private static final long serialVersionUID = -2471385727456813620L;

  /**
   *
   * Catch all io.github.franabril.baseexception and throw it to send Response
   * with Exception information
   *
   * @param exception io.github.franabril.baseexception
   * @param status    javax.ws.rs.core.Status
   *                  javax.ws.rs.core.FAMILY.CLIENT_ERROR or SERVER_ERROR
   */
  public RestponseError(BaseException exception, Status status) {
    super(generateErrorResponse(exception, status));
  }

  /**
   *
   * throw it to send Response io.github.franabril.baseexception.IError
   * information
   *
   * @param status javax.ws.rs.core.Status
   *               javax.ws.rs.core.FAMILY.CLIENT_ERROR or SERVER_ERROR
   * @param error  io.github.franabril.baseexception.IError
   * @param args   arguments to be replaced in {%s}
   *               io.github.franabril.baseexception.IError
   */
  public RestponseError(Status status, IError error, Object... args) {
    super(generateErrorResponse(new RestponseException(error, args), status));
  }

  private static Response generateErrorResponse(BaseException exception, Status status) {
    switch (status.getFamily()) {
      case CLIENT_ERROR:
        return new ResponseClientError(exception, status).getResponse();
      case SERVER_ERROR:
        return new ResponseServerError(exception, status).getResponse();
      default:
        throw throwException(status);
    }
  }

  private static InternalServerErrorException throwException(Status status) {
    String error = "Status must be " + Family.CLIENT_ERROR + " or " + Family.SERVER_ERROR + " not -> '" + status + "'";
    InternalServerErrorException ex = new InternalServerErrorException(error);
    return ex;
  }
}

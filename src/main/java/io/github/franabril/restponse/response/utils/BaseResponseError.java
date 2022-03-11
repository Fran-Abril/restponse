package io.github.franabril.restponse.response.utils;

import javax.ws.rs.core.Response.Status;

import io.github.franabril.baseexception.BaseException;
import io.github.franabril.restponse.error.factory.ErrorFactory;
import io.github.franabril.restponse.error.model.ErrorDTO;

/**
 *
 * @author ffrannabril@gmail.com
 */
public abstract class BaseResponseError extends AbstractRestponse {

  public BaseResponseError(BaseException exception, Status status) {
    super(assemble(exception), status);
  }

  private static ErrorDTO assemble(BaseException exception) {
    return new ErrorFactory().create(exception.getCode(), exception.getMessage(), exception.getArguments());
  }
}

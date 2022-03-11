package io.github.franabril.restponse.exception;

import io.github.franabril.baseexception.BaseException;
import io.github.franabril.baseexception.IError;

/**
 *
 * Internal exception to manage errors in this library
 *
 * @author ffrannabril@gmail.com
 */
public class RestponseException extends BaseException {

  public RestponseException(IError error, Object... args) {
    super(error, args);
  }
}

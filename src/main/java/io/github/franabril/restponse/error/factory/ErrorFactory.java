package io.github.franabril.restponse.error.factory;

import java.util.List;

import javax.validation.Valid;

import io.github.franabril.restponse.error.model.ErrorDTO;

/**
 *
 * @author ffrannabril@gmail.com
 */
public class ErrorFactory implements IErrorFactory<ErrorDTO> {

  @Valid
  private ErrorDTO targetDto;

  public ErrorFactory() {
    targetDto = new ErrorDTO();
  }

  @Override
  public ErrorDTO create(String code, String message, List<String> arguments) {
    targetDto.setCode(code);
    targetDto.setMessage(message);
    targetDto.setArguments(arguments);
    return targetDto;
  }
}

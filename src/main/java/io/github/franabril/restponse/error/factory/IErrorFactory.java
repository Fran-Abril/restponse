package io.github.franabril.restponse.error.factory;

import java.util.List;

/**
 *
 * @author ffrannabril@gmail.com
 */
public interface IErrorFactory<E> {

  E create(String code, String message, List<String> arguments);
}

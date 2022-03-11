package io.github.franabril.restponse.error.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * Error that will be sent with Response Server Error
 *
 * @author ffrannabril@gmail.com
 */
public class ErrorDTO {

  @JsonProperty("code")
  protected String code = null;

  @JsonProperty("message")
  protected String message = null;

  @JsonProperty("arguments")
  protected List<String> arguments = null;

  public ErrorDTO() {
    // Empty Constructor
  }

  public ErrorDTO(ErrorDTO parentDto) {
    this.code = parentDto.code;
    this.message = parentDto.message;
    this.arguments = parentDto.arguments;
  }

  public ErrorDTO code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Contains error code
   *
   * @return code
   */
  @JsonProperty("code")
  @NotNull
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Contains error message
   *
   * @return message
   */
  @JsonProperty("message")
  @NotNull
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorDTO arguments(List<String> arguments) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<>();
    }
    this.arguments.addAll(arguments);
    return this;
  }

  public ErrorDTO addArgumentsItem(String argumentsItem) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<>();
    }
    this.arguments.add(argumentsItem);
    return this;
  }

  /**
   * Contains the arguments of the message
   *
   * @return arguments
   */
  @JsonProperty("arguments")
  public List<String> getArguments() {
    if (this.arguments == null) {
      return Collections.emptyList();
    }
    return arguments;
  }

  public void setArguments(List<String> arguments) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<>();
    }
    this.arguments.addAll(arguments);
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDTO Error = (ErrorDTO) o;
    return Objects.equals(this.code, Error.code)
        && Objects.equals(this.message, Error.message)
        && Objects.equals(this.arguments, Error.arguments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, arguments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class " + ErrorDTO.class.getSimpleName() + " {\n");
    sb.append("\tcode: ").append(toIndentedString(code)).append("\n");
    sb.append("\tmessage: ").append(toIndentedString(message)).append("\n");
    sb.append("\targuments: ").append(toIndentedString(arguments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString();
  }
}

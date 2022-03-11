package io.github.franabril.restponse.response;

import java.util.Optional;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;

import io.github.franabril.restponse.response.success.ResponseSuccessfull;

/**
 *
 * Restponse instead of javax.ws.rs.core.Response
 *
 * @author ffrannabril@gmail.com
 */
public class Restponse {

    private static final String INVALID_STATUS_CODE = "Instead of " + Restponse.class.getSimpleName() + ", throw "
            + RestponseError.class.getSimpleName() + " here for '%s'";

    private Restponse() {
        // empty constructor
    }

    /**
     * <b>Build Default Response:</b>
     *
     * <p>
     * Default Status <b>OK without message</b> or object.
     *
     *
     * @return javax.ws.rs.core.Response
     * @see <a href=
     *      "http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10">HTTP/1.1
     *      documentation</a>
     */
    public static Response get() {
        return generateResponse(Optional.empty(), Status.OK);
    }

    /**
     * <b>Build Response</b> without message or object and check the Status family
     *
     *
     * @param code javax.ws.rs.core.Response.Status
     * @return javax.ws.rs.core.Response
     * @see <a href=
     *      "http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10">HTTP/1.1
     *      documentation</a>
     */
    public static Response with(Status code) {
        return generateResponse(Optional.empty(), code);
    }

    /**
     * <b>Build Response</b>, Default Status OK and map entity to Response.
     *
     *
     * @param entity can be a <b>String</b> message or someone <b>Object</b>
     * @return javax.ws.rs.core.Response
     * @see <a href=
     *      "http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10">HTTP/1.1
     *      documentation</a>
     */
    public static Response with(Object entity) {
        return generateResponse(Optional.ofNullable(entity), Status.OK);
    }

    /**
     *
     *
     * @param entity entity can be a <b>String</b> message or someone <b>Object</b>
     * @param code   javax.ws.rs.core.Response.Status
     * @return javax.ws.rs.core.Response
     * @see <a href=
     *      "http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10">HTTP/1.1
     *      documentation</a>
     */
    public static Response with(Object entity, Status code) {
        return generateResponse(Optional.ofNullable(entity), code);
    }

    private static Response generateResponse(Optional<Object> entity, Status status) {
        if (status.getFamily() == Family.CLIENT_ERROR || status.getFamily() == Family.SERVER_ERROR) {
            InternalServerErrorException ex = new InternalServerErrorException(
                    String.format(INVALID_STATUS_CODE, status));
            throw ex;
        }
        return new ResponseSuccessfull(entity.orElse(status.name()), status).getResponse();
    }
}

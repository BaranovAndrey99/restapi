package restapi.dto;

/**
 * Entity, which uses for response for requests.
 * Unified response.
 * @param <T> - Generic - Entity/entities, which we want to send in response.
 */
public class ResponseEntityDto<T> {
    private final T responseBodies;
    private final String responseMessage;

    public ResponseEntityDto(String responseMessage, T responseBodies) {
        this.responseMessage = responseMessage;
        this.responseBodies = responseBodies;
    }

    public T getResponseBodies() {
        return responseBodies;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
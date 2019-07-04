package restapi.dto;

import org.springframework.http.HttpStatus;
import restapi.entity.Product;

import java.util.List;

public class ResponseEntityDto {
    private List<Product> responseBodies;
    private String responseMessage;

    public ResponseEntityDto() {
    }

    public ResponseEntityDto(String responseMessage, List<Product> responseBodies) {
        this.responseMessage = responseMessage;
        this.responseBodies = responseBodies;
    }

    public List<Product> getResponseBodies() {
        return responseBodies;
    }

    public void setResponseBodies(List<Product> responseBodies) {
        this.responseBodies = responseBodies;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

package net.fashiongo.webadmin.controller.ads;

public class ResourceNotFoundException extends RuntimeException {

    private String requestUri;

    public ResourceNotFoundException(String message, String requestUri) {
        super(message);
        this.requestUri = requestUri;
    }

    public ResourceNotFoundException(String message, Throwable cause, String requestUri) {
        super(message, cause);
        this.requestUri = requestUri;
    }

    public ResourceNotFoundException(Throwable cause, String requestUri) {
        super(cause);
        this.requestUri = requestUri;
    }

}

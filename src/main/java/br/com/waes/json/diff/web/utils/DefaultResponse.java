package br.com.waes.json.diff.web.utils;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Custom web response to encapsulate all system entities
 * as json http responses
 * @param <T> Generic parameter to represent any valid content
 */
@Data
@Builder
public class DefaultResponse <T> {

    private int status;
    private boolean valid;
    private T content;
    private String message;

    /**
     * Create a valid json http response with a valid content
     * @param content any possible system class to encapsulate
     * @return Default Response
     */
    public static <T> DefaultResponse<T> validResponse(T content) {
        return buildResponse(content,null,200,true);
    }

    /**
     * Create an invalid default response
     * @param message message indicating the possible error
     * @param status invalid http status code (ex: 4xx or 5xx)
     * @return Invalid {@link DefaultResponse}
     */
    public static <T> DefaultResponse<T> invalidResponse(String message, int status) {
        return buildResponse(null,message,status,false);
    }

    /**
     * Create {@link DefaultResponse} instance
     * @param content generic attribute to be serialized as response
     * @param message message to indicate a success or error response
     * @param status http status ex: 2xx, 4xx or 5xx
     * @param valid boolean to represent valid or invalid response
     * @return valid instance of {@link DefaultResponse}
     */
    public static <T> DefaultResponse<T> buildResponse(T content, String message, int status, boolean valid) {
        return (DefaultResponse<T>)
                DefaultResponse.builder()
                .content(content)
                .message(StringUtils.isBlank(message) ? "" : message)
                .status(status)
                .valid(valid).build();
    }
}

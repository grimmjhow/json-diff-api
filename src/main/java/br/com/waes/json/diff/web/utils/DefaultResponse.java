package br.com.waes.json.diff.web.utils;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class DefaultResponse <T> {
    private int status;
    private boolean valid;
    private T content;
    private String message;

    public static <T> DefaultResponse<T> validResponse(T content) {
        return buildResponse(content,null,200,true);
    }

    public static <T> DefaultResponse<T> invalidResponse(String message, int status) {
        return buildResponse(null,message,status,false);
    }

    public static <T> DefaultResponse<T> buildResponse(T content, String message, int status, boolean valid) {
        return (DefaultResponse<T>)
                DefaultResponse.builder()
                .content(content)
                .message(StringUtils.isBlank(message) ? "" : message)
                .status(status)
                .valid(valid).build();
    }
}

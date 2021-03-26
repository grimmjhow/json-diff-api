package br.com.waes.json.diff.exceptions;

/**
 * Can be used for:
 * <ul>
 *  <li>
 *     Validation Errors
 *  </li>
 * </ul>
 * <p>It's extend {@link RuntimeException} on propose to avoid
 * <i>throw</i> key word in every method call. All the exceptions in the system
 * are handle by {@link org.springframework.web.bind.annotation.RestController}</p>
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}

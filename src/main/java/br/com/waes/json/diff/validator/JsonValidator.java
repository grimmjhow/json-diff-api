package br.com.waes.json.diff.validator;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Validator responsible to compare
 * two json content
 */
@Component
@RequiredArgsConstructor
public class JsonValidator {

    /**
     * Validates if both json have different size
     * @param left Valid converted {@link JsonObject}
     * @param right Valid converted {@link JsonObject}
     * @return true if json does not have the same size, otherwise return false
     */
    public boolean isNotSameSize(JsonObject left, JsonObject right) {
        return !this.isSameSize(left, right);
    }

    /**
     * Validates if both json have same size, but different contents
     * @param left Valid converted {@link JsonObject}
     * @param right Valid converted {@link JsonObject}
     * @return true if both have the same size and not same content, otherwise return false
     */
    public boolean isNotSameContent(JsonObject left, JsonObject right) {
        return this.isSameSize(left,right) && !isSameContent(left,right);
    }

    /**
     * Validates if json structure have the same size
     * @param left Valid converted {@link JsonObject}
     * @param right Valid converted {@link JsonObject}
     * @return true if both have the same size, otherwise return false
     */
    public boolean isSameSize(JsonObject left, JsonObject right) {
        return left.size() == right.size();
    }

    /**
     * Validates if json structure have the content
     * @param left Valid converted {@link JsonObject}
     * @param right Valid converted {@link JsonObject}
     * @return true if both have same content, otherwise return false
     */
    public boolean isSameContent(JsonObject left, JsonObject right) {
        return left.equals(right);
    }
}

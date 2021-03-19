package br.com.waes.json.diff.validator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class JsonValidator {

    public boolean isNotSameSize(JsonObject left, JsonObject right) {
        return !this.isSameSize(left, right);
    }

    public boolean isNotSameContent(JsonObject left, JsonObject right) {
        return this.isSameSize(left,right) && !isSameContent(left,right);
    }
    public boolean isSameSize(JsonObject left, JsonObject right) {
        return left.size() == right.size();
    }

    public boolean isSameContent(JsonObject left, JsonObject right) {
        return left.equals(right);
    }
}

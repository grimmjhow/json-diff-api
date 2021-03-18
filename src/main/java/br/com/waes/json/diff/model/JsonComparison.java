package br.com.waes.json.diff.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JsonComparison {

    @Id
    private String uuid = UUID.randomUUID().toString();

    private long id;

    @Enumerated(EnumType.STRING)
    private DiffSide side = DiffSide.NONE;

    @EqualsAndHashCode.Exclude
    private String base64Encode = "";

    @EqualsAndHashCode.Exclude
    private String content = "";

    @EqualsAndHashCode.Exclude
    private JsonElement json;

    public boolean isBase64Empty() {
        return StringUtils.isBlank(base64Encode);
    }

    public JsonComparison(long id, DiffSide side, String content) {
        this.id = id;
        this.side = side;
        this.base64Encode = content;
    }

    public boolean isSameSize(JsonComparison other) {
        return getContent().length() == other.getContent().length();
    }

    public boolean isSameContent(JsonComparison right) {
        return json.equals(right.getJson());
    }

    public String getContent() {
        return this.json.getAsString();
    }
}

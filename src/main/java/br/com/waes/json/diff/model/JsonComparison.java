package br.com.waes.json.diff.model;

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

/**
 * Entity to represent a side in a Json Struct comparison.
 * <p>It can assume Left or Right comparison with the attribute {@link DiffSide}</p>
 */
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

    public boolean isBase64Empty() {
        return StringUtils.isBlank(base64Encode);
    }

    public JsonComparison(long id, DiffSide side, String base64Encode) {
        this.id = id;
        this.side = side;
        this.base64Encode = base64Encode;
    }
}

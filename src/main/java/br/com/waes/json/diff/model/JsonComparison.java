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
    private String content = "";

    public boolean isContentEmpty() {
        return StringUtils.isBlank(content);
    }

    public JsonComparison(long id, DiffSide side, String content) {
        this.id = id;
        this.side = side;
        this.content = content;
    }
}

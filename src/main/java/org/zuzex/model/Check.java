package org.zuzex.model;

import io.quarkiverse.hibernate.types.json.JsonBinaryType;
import io.quarkiverse.hibernate.types.json.JsonTypes;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@Entity(name = "Check")
@Table(name = "checks")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs(@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class))
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_seq")
    @SequenceGenerator(name = "check_seq", sequenceName = "check_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shop_name")
    private String shopName;

    @Type(type = JsonTypes.JSON_BIN)
    @Column(name = "product_imprints", columnDefinition = JsonTypes.JSON_BIN)
    private List<ProductImprint> productImprints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Check))
            return false;
        Check entity = (Check) o;
        return id != null &&
                id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

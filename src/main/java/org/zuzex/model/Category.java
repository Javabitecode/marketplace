package org.zuzex.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_sequence")
    private Long id;

    @Column(name = "name",
            unique = true,
            updatable = false,
            nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category))
            return false;
        Category entity = (Category) o;
        return id != null &&
                id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

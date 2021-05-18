package h8.chikey.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int manufacturerID;

    @Column(name = "Name")
    private String manufacturerName;

    @Column(name = "StartDate")
    private Date manufacturerStartDate;

    @OneToMany(mappedBy = "manufacturer")
    Set<Product> setProduct;

    @Override
    public String toString() {
        return manufacturerName;
    }
}

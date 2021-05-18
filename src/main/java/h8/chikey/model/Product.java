package h8.chikey.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int productID;

    @NonNull
    @Column(name = "Title")
    private String productTitle;

    @Column(name = "Cost")
    @NonNull
    private double productCost;

    @Column(name = "Description")
    private String productDescription;

    @Column(name = "MainImagePath")
    private String productMainImagePath;

    @Column(name = "IsActive")
    @NonNull
    private int productIsActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ManufacturerID")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "product")
    Set<ProductSale> setProduct;

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productTitle='" + productTitle + '\'' +
                ", productCost=" + productCost +
                ", productDescription='" + productDescription + '\'' +
                ", productMainImagePath='" + productMainImagePath + '\'' +
                ", productIsActive=" + productIsActive +
                ", manufacturer=" + manufacturer +
                '}';
    }
}

package h8.chikey.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "productsale")
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID")
    private int productSaleID;

    @Column(name = "SaleDate")
    @NonNull
    private Date productSaleSaleDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "Quantity")
    @NonNull
    private int productSaleQuantity;

    @Override
    public String toString() {
        return "ProductSale{" +
                "productSaleID=" + productSaleID +
                ", productSaleSaleDate=" + productSaleSaleDate +
                ", product=" + product +
                ", productSaleQuantity=" + productSaleQuantity +
                '}';
    }
}

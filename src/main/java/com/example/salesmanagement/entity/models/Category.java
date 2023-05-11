package com.example.salesmanagement.entity.models;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.example.salesmanagement.entity.utilities.Time;
import lombok.Data;


@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id",length = 50, nullable = false, updatable = false)
    private String categoryId = "CT-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = false, unique = true)
    private String categoryName;

    @Column(length = 50, nullable = false)
    private Integer  categoryDiscount;  
   
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product; 

    @Column(length = 100, nullable = true)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updateAt = Time.getDeadCurrentDate();

}

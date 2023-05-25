// package com.example.salesmanagement.entity.models;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.ManyToMany;
// import javax.persistence.Table;

// import com.example.salesmanagement.entity.utilities.Time;

// import lombok.Data;

// @Data
// @Entity
// @Table(name = "tag")
// public class Tag {
//     @Id
//     @Column(name = "tag_id",length = 50, nullable = false, updatable = false)
//     private String tagId = "TA-" + UUID.randomUUID().toString();

//     @Column(name = "title", nullable = false)
//     private String title;

//     @Column(name = "meta_title", nullable = false)
//     private String metaTitle;

//     @Column(name = "slug", nullable = false)
//     private String slug;

//     @Column(name = "content")
//     private String content;

//     @ManyToMany(mappedBy = "tags")
//     private List<Product> products;

//     @Column(length = 100, nullable = true)
//     private String createAt = Time.getDeadCurrentDate();

//     @Column(length = 100, nullable = true)
//     private String updateAt = Time.getDeadCurrentDate();

//     public List<Product> getProducts() {
//         if (products == null) {
//             products = new ArrayList<>();
//         }
//         return products;
//     }
// }

package com.example.salesmanagement.entity.models;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OverSpecs{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String key;
    
    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "specification_values")
    private List<String> values;

    
}










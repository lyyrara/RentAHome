package com.example.rentahome.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "users")
public class PropertyDTO {
    @Id
    private int id;
    private int propertyId;
    private String address;
    private String propertyType;
    private String availableFrom;
    private String availableTo;
    private double rating;
    private double price;
    private String features;
    private String pictureLocation;
    
    
}

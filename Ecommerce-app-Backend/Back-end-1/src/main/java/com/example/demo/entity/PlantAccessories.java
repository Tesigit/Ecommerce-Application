package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

import com.example.demo.converter.ListToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "plant_accessories")
public class PlantAccessories {

    @Id
    
    private Long id;  // Your JSON already has id manually (5000, 5001...)

    private String name;

    private String category;

    @Column(name = "img_url", columnDefinition = "TEXT")
    @JsonProperty("img_url")
    private String imgUrl;

    private Double price;

    @Column(name = "selling_price")
    @JsonProperty("selling_price")
    private Double sellingPrice;

    @Column(name = "discount_percent")
    @JsonProperty("discount_percent")
    private Integer discountPercent;

    private Double rating;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Convert(converter = ListToStringConverter.class)
    private List<String> benefits;

    @Convert(converter = ListToStringConverter.class)
    @Column(name = "usage_steps")
    @JsonProperty("usage_steps")
    private List<String> usageSteps;
    
    @Column(name = "qty")
    private Integer qty = 0; // default 0

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }


    // Default constructor
    public PlantAccessories() {}

    // All-args constructor
    public PlantAccessories(Long id, String name, String category, String imgUrl, Double price,
                            Double sellingPrice, Integer discountPercent, Double rating,
                            String description, List<String> benefits, List<String> usageSteps,Integer qty) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.imgUrl = imgUrl;
        this.price = price;
        this.sellingPrice = sellingPrice;
        this.discountPercent = discountPercent;
        this.rating = rating;
        this.description = description;
        this.benefits = benefits;
        this.usageSteps = usageSteps;
        this.qty = qty;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }

    public List<String> getUsageSteps() {
        return usageSteps;
    }

    public void setUsageSteps(List<String> usageSteps) {
        this.usageSteps = usageSteps;
    }

    @Override
    public String toString() {
        return "PlantAccessories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", price=" + price +
                ", sellingPrice=" + sellingPrice +
                ", discountPercent=" + discountPercent +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", benefits=" + benefits +
                ", usageSteps=" + usageSteps +
                ", qty=" + qty +
                '}';
    }

}

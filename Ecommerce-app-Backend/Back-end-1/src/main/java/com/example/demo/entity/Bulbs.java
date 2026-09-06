

package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.Convert;

import com.example.demo.converter.ListToIntegerConverter;
import com.example.demo.converter.ListToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "bulbs_plants")
public class Bulbs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "common_name")
    @JsonProperty("common_name")
    private String commonName;

    @Column(name = "scientific_name")
    @JsonProperty("scientific_name")
    private String scientificName;

    @Column(name = "family")
    private String family;

    @Column(name = "categories")
    private String categories;

    @Column(name = "origin")
    private String origin;

    @Column(name = "climate")
    private String climate;
    

    @Convert(converter = ListToIntegerConverter.class)
    private List<Integer> zone;

    @Column(name = "img_url", columnDefinition = "TEXT")
    @JsonProperty("img_url")
    private String imgUrl;

    @Column(name = "price")
    private Double price;

    @Column(name = "selling_price")
    @JsonProperty("selling_price")
    private Double sellingPrice;

    @Column(name = "discount_percent")
    @JsonProperty("discount_percent")
    private Integer discountPercent;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Convert(converter = ListToStringConverter.class)
    private List<String> benefits;

    @Convert(converter = ListToStringConverter.class)
    @JsonProperty("steps_to_grow")
    private List<String> stepsToGrow;

    @Column(name = "qty")
    private Integer qty = 0; // default 0

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }


	public Bulbs() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Bulbs(Long id, String commonName, String scientificName, String family, String categories, String origin,
			String climate, List<Integer> zone, String imgUrl, Double price, Double sellingPrice,
			Integer discountPercent, Double rating, String description, List<String> benefits,Integer qty,
			List<String> stepsToGrow) {
		super();
		this.id = id;
		this.commonName = commonName;
		this.scientificName = scientificName;
		this.family = family;
		this.categories = categories;
		this.origin = origin;
		this.climate = climate;
		this.zone = zone;
		this.imgUrl = imgUrl;
		this.price = price;
		this.sellingPrice = sellingPrice;
		this.discountPercent = discountPercent;
		this.rating = rating;
		this.description = description;
		this.benefits = benefits;
		this.stepsToGrow = stepsToGrow;
		this.qty = qty;
	}


	@Override
	public String toString() {
		return "Bulbs [id=" + id + ", commonName=" + commonName + ", scientificName=" + scientificName + ", family="
				+ family + ", categories=" + categories + ", origin=" + origin + ", climate=" + climate + ", zone="
				+ zone + ", imgUrl=" + imgUrl + ", price=" + price + ", sellingPrice=" + sellingPrice
				+ ", discountPercent=" + discountPercent + ", rating=" + rating + ", description=" + description
				+ ", benefits=" + benefits + ", stepsToGrow=" + stepsToGrow + ", qty=" + qty +"]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCommonName() {
		return commonName;
	}


	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}


	public String getScientificName() {
		return scientificName;
	}


	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}


	public String getFamily() {
		return family;
	}


	public void setFamily(String family) {
		this.family = family;
	}


	public String getCategories() {
		return categories;
	}


	public void setCategories(String categories) {
		this.categories = categories;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getClimate() {
		return climate;
	}


	public void setClimate(String climate) {
		this.climate = climate;
	}


	public List<Integer> getZone() {
		return zone;
	}


	public void setZone(List<Integer> zone) {
		this.zone = zone;
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


	public List<String> getStepsToGrow() {
		return stepsToGrow;
	}


	public void setStepsToGrow(List<String> stepsToGrow) {
		this.stepsToGrow = stepsToGrow;
	}

		
    
    
    

    }

package ua.ita.smartcarservice.dto.sales;

import java.util.Date;

/**
 * Created by 1 on 27.02.2019.
 */
public class DealerCarDto {

    private Long id;
    private String brand;
    private String model;
    private String graduation_year;
    private String number;
    private Double price;
    private String vin;
    private Date end_guarantee;
    private String usernameDealer;

    public DealerCarDto(String brand, String model, String graduation_year, String number, Double price, String vin, Date end_guarantee, String usernameDealer) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.price = price;
        this.vin = vin;
        this.end_guarantee = end_guarantee;
        this.usernameDealer = usernameDealer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(String graduation_year) {
        this.graduation_year = graduation_year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getEnd_guarantee() {
        return end_guarantee;
    }

    public void setEnd_guarantee(Date end_guarantee) {
        this.end_guarantee = end_guarantee;
    }

    public String getUsernameDealer() {
        return usernameDealer;
    }

    public void setUsernameDealer(String usernameDealer) {
        this.usernameDealer = usernameDealer;
    }

}
package ua.ita.smartcarservice.entity.booking;

import java.time.LocalDateTime;

public class BookingRequest {
    Long id;
    String name;
    String value;
    boolean inProcess;
    String carValue;
    LocalDateTime start;
    LocalDateTime end;
    int price;
    int workers;

   public static BookingRequest getOne(){
        return new BookingRequest();
    }

    public BookingRequest setName(String name){
        this.name = name;
        return this;
    }

    public BookingRequest setValue(String value){
        this.value = value;
        return this;
    }
    public BookingRequest setCarValue(String carValue){
        this.carValue = carValue;
        return this;
    }
    public BookingRequest setPrice(int price){
        this.price = price;
        return this;
    }
    public BookingRequest setWorkers(int workers){
        this.workers = workers;
        return this;
    }
}

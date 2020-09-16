
package dtos;

import entities.Cars;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CarsDTO {
    private Long id;
    private int year;
    private String make;
    private String model;
    private int price;
    private String owner;
    @Temporal(TemporalType.DATE)
    private Date created;
    
    public CarsDTO(Cars cars) {
        this.id = cars.getId();
        this.year = cars.getYear();
        this.make = cars.getMake();
        this.model = cars.getModel();
        this.price = cars.getPrice();
        this.owner = cars.getOwner();
        this.created = cars.getCreated();
    }

    public CarsDTO() {
    }

    public CarsDTO(Long id, int year, String make, String model, int price, String owner, Date created) {
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.price = price;
        this.owner = owner;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public Date getCreated() {
        return created;
    }
    
}

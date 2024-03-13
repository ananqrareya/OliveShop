package www.OliveOnline.com.OliveShop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id")
    private int addId;
    @NotEmpty
    @Column(name = "city",nullable = false)
    private String city;
    @NotEmpty
    @Column(name="region",nullable = false)
    private String region ;
    @NotEmpty
    @Column(name = "street",nullable = false)
    private String street;
    @Column(name = "building_no")
    private int buildingNumber;
    @ManyToMany(mappedBy = "addresses",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Customer> customers=new ArrayList<>();


    public  Address(){

    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addId=" + addId +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                '}';
    }
}

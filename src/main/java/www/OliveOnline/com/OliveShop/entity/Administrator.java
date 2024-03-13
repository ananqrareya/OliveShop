package www.OliveOnline.com.OliveShop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "administrator")
public class Administrator {
    @Id
    private int id;
    @Column(name = "power_description")
    private String powerDescription;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private Users user;


    public Administrator() {

    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        this.powerDescription = powerDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}

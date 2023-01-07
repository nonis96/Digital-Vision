package TM;

import controller.TableFormController;

/**
 * author : W.P.A.M.Nonis <ameshnonis8@gmail.com>
 * contact : 0717730167
 * date : 1/3/2023
 **/
public class ListTM {

   private String wish_id;
   private String brand;
   private String price;
   private String id;

    public ListTM() {
    }

    public ListTM(String wish_id, String brand, String price, String id) {
        this.wish_id = wish_id;
        this.brand = brand;
        this.price = price;
        this.id = id;
    }

    public String getWish_id() {
        return wish_id;
    }

    public void setWish_id(String wish_id) {
        this.wish_id = wish_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ListTM{" +
                "wish_id='" + wish_id + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}



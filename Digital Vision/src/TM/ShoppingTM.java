package TM;

/**
 * author : W.P.A.M.Nonis <ameshnonis8@gmail.com>
 * contact : 0717730167
 * date : 1/6/2023
 **/
public class ShoppingTM {

    private String ItemName;
    private String Prise;

    public ShoppingTM() {
    }

    public ShoppingTM(String itemName, String prise) {
        ItemName = itemName;
        Prise = prise;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getPrise() {
        return Prise;
    }

    public void setPrise(String prise) {
        Prise = prise;
    }
}

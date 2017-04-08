package Model;
public class Book {
    private String titel;
    private String quantity;
    private String uuid;

    public Book(String titel, String quantity, String uuid) {
        this.titel = titel;
        this.quantity = quantity;
        this.uuid = uuid;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitel() {

        return titel;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUuid() {
        return uuid;
    }
}

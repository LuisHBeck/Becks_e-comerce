import java.util.List;

public class Products {
    private String product;
    private String description;
    private String price;

    public Products(String product, String description, String price) {
        this.product = product;
        this.description = description;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public List toString() {
        return [product + " " + " " + description + " "+ price];
    }
}

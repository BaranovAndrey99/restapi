package restapi.repository;

import org.springframework.stereotype.Repository;
import restapi.domain.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This class is imaginary(fake) repository, that simulate a database;
 * This class contains field wit type "List" for product storing;
 * This class contains empty constructor, getter, setter, and methods for
 *      addition and deleting of elements of list.
 */
@Repository
public class ImaginaryRepository {

    private final ArrayList<String> productNames = new ArrayList<>(Arrays.asList(
            "Молоко", "Кефир", "Масло",
            "Колбаса", "Свинина", "Сосиски",
            "Яблоко", "Груша", "Апельсин",
            "Огурец", "Томат", "Капуста")
    );
    private final ArrayList<String> productTypes = new ArrayList<>(Arrays.asList(
            "Молоко",
            "Мясо",
            "Фрукты",
            "Овощи")
    );

    private ArrayList<Product> productList = new ArrayList<>();

    public ImaginaryRepository() {
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public ArrayList<String> getProductNames() {
        return productNames;
    }

    public ArrayList<String> getProductTypes() {
        return productTypes;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    /**
     * Method, which uses in GET request
     * Method for listing of all products.
     */
    public ArrayList<Product> findAllProducts(){
        return productList;
    }

    /**
     * Method, which uses in POST request.
     * @param product - thing, which we would to create
     */
    public void addProduct(Product product){
        this.productList.add(product);
    }

    /**
     * Method, which uses in DELETE request.
     * Here we remove any object, which have sought-for identifier
     * @param id - id of thing, which we would to delete
     */
    public void delProduct(long id){
        Product deletableProduct = new Product(id, "", "");
        productList.removeIf(product -> product.idEquals(deletableProduct));
        /**
         * Тут надо обработать, что не нашли и кинуть исключение.
         */
    }

    /**
     * Method for updating of elements.
     * If list contains object with same name - create new
     * @param product - thing, which we would to update
     */
    public void updProduct(Product product){
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()){
            if(iterator.next().idEquals(product)){
                iterator.remove();
            }
        }
        productList.add(product);

    }

}

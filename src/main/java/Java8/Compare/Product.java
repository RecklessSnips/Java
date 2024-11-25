package Java8.Compare;

public class Product implements Comparable<Product>{
    /*
    自然排序
     */
    private String name;
    private double price;

    public Product(){

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    // 指明按照什么方式排序
    @Override
    public int compareTo(Product product) {
        // 升序， 加一个 - 号降序
        // 先按照价格从高到低，如果价格相同，则按名称从A到Z排序, 然后产品名字从 a 到 z
//        if (0 == Double.compare(this.price, product.price)){
//            // String已经实现了 Comparable 接口，可以直接调用compareTo
//            return this.name.compareTo(product.name);
//        }else{
//            return Double.compare(this.price, product.price);
//        }

        // 先排序名字，如果相同价格低到高
        if(0 == this.name.compareTo(product.name)){
            return Double.compare(this.price, product.price);
        }
        return this.name.compareTo(product.name);
    }
}

public class Price {
    private int valuePrice;
    private int minPrice;

    private int maxPrice;

    public Price(){
        this.valuePrice = valuePrice;
        this.maxPrice = 10;
        this.minPrice = -10;
    }

    public int getValuePrice() {
        return valuePrice;
    }

    public void setValuePrice(int valuePrice) {
        this.valuePrice = valuePrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    @Override
    public String toString() {
        return "Price{" +
                "valuePrice=" + valuePrice +
                '}';
    }
}

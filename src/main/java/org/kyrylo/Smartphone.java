package org.kyrylo;
public class Smartphone {
    private String model;
    private String brand;
    private double price;

    public Smartphone(String model, String brand, double price) {
        this.model = model;
        this.brand = brand;
        this.price = price;
    }

    //getters and setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Smartphone)) return false;
        Smartphone that = (Smartphone) o;
        return model.equals(that.model) && brand.equals(that.brand);
    }

    @Override
    public int hashCode() {
        return model.hashCode() + brand.hashCode();
    }
}

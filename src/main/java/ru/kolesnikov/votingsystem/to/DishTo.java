package ru.kolesnikov.votingsystem.to;

public class DishTo {

    public DishTo(String name, Long price) {
        this.name = name;
        this.price = price;
    }
    public DishTo() {
    }

    private String name;
    private Long price;

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishTo dishTo = (DishTo) o;

        if (name != null ? !name.equals(dishTo.name) : dishTo.name != null) return false;
        return price != null ? price.equals(dishTo.price) : dishTo.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}

package ru.kolesnikov.votingsystem.to;

public class DishTo {

    public DishTo(long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public DishTo() {
    }

    private long id;
    private String name;
    private Long price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishTo dishTo = (DishTo) o;

        if (id != dishTo.id) return false;
        if (!name.equals(dishTo.name)) return false;
        return price.equals(dishTo.price);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}

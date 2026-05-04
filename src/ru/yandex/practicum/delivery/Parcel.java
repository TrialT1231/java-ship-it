package ru.yandex.practicum.delivery;

public abstract class Parcel {

    protected String description;//добавьте реализацию и другие необходимые классы
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;
    public static final int COEFFICIENT = 1;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка " + description + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    public int calculateDeliveryCost() {
        return (int) (weight * getCoefficient());
    }

    protected int getCoefficient() {
        return COEFFICIENT;
    }

    public String getDescription() {
        return description + " (вес: " + weight + " кг)" + "(дата отправки: " + sendDay + ")" + "(адрес доставки: " + deliveryAddress + ")";
    }

    public int getWeight() {
        return weight;
    }
}

package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {

    public static final int COEFFICIENT = 3;

    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {

        if ((sendDay + timeToLive) >= currentDay) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected int getCoefficient() {
        return COEFFICIENT;
    }

    @Override
    public String getDescription() {
        return description + " (вес: " + weight + " кг) (дата отправки: " + sendDay + ") (адрес доставки: " + deliveryAddress + ") (доставить до: " + timeToLive + ")";
    }
}

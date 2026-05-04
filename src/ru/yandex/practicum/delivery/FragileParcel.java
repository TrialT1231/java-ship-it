package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {

    public static final int COEFFICIENT = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        super.packageItem();
        System.out.println("Посылка " + description + " обёрнута в защитную плёнку");
    }

    @Override
    protected int getCoefficient() {
        return COEFFICIENT;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + description + " изменила местоположение на " + newLocation);
        this.deliveryAddress = newLocation;
    }
}

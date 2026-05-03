package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final double maxWeight;
    private final List<T> parcels;
    private double currentWeight;

    public ParcelBox(double maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0.0;
    }

    public void addParcel(T parcel) {
        boolean flag = true;
        if (currentWeight + parcel.weight > maxWeight) {
            flag = false;
        }
        if (flag) {
            parcels.add(parcel);
            currentWeight += parcel.weight;
            System.out.println("Посылка " + parcel.description + " добавлена. \n Текущий вес: " + currentWeight + "/" + maxWeight + " кг");
        }else {
            System.out.println("Коробка переполнена!!!" + "Посылка " + parcel.description + " не добавлена. " +
                    "Свободно: " + (maxWeight - currentWeight) + " кг, " +
                    "вес посылки: " + parcel.weight + " кг");
        }
    }
    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

}

package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> allTrackables = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardBox;
    private static ParcelBox<FragileParcel> fragileBox;
    private static ParcelBox<PerishableParcel> perishableBox;

    public static void main(String[] args) {
        standardBox = new ParcelBox<>(50.0);
        fragileBox = new ParcelBox<>(30.0);
        perishableBox = new ParcelBox<>(40.0);
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    tracking();
                    break;
                case 5:
                    showBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Добавить новый трекинг посылок");
        System.out.println("5 — Показать содержимое коробки ");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная");// Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Выбрана - Стандартная посылка");
                System.out.println("Введите краткое описание: ");
                String description = scanner.nextLine();
                System.out.println("Введите вес( целое число): ");
                int weight = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес места назначения посылки: ");
                String deliveryAddress = scanner.nextLine();
                System.out.println("Введите день отправки посылки: ");
                int sendDay = Integer.parseInt(scanner.nextLine());
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                standardBox.addParcel(standardParcel);
                allParcels.add(standardParcel);
                break;
            case 2:
                System.out.println("Выбрана - Хрупкая посылка");
                System.out.println("Введите краткое описание: ");
                String description1 = scanner.nextLine();
                System.out.println("Введите вес( целое число): ");
                int weight1 = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес места назначения посылки: ");
                String deliveryAddress1 = scanner.nextLine();
                System.out.println("Введите день отправки посылки: ");
                int sendDay1 = Integer.parseInt(scanner.nextLine());
                FragileParcel fragileParcel = new FragileParcel(description1, weight1, deliveryAddress1, sendDay1);
                fragileBox.addParcel(fragileParcel);
                allParcels.add(fragileParcel);
                allTrackables.add(fragileParcel);
                break;
            case 3:
                System.out.println("Выбрана - Скоропортящаяся посылка");
                System.out.println("Введите краткое описание: ");
                String description2 = scanner.nextLine();
                System.out.println("Введите вес( целое число): ");
                int weight2 = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес места назначения посылки: ");
                String deliveryAddress2 = scanner.nextLine();
                System.out.println("Введите день отправки посылки: ");
                int sendDay2 = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите день истекания срока годности: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description2, weight2, deliveryAddress2, sendDay2, timeToLive);
                perishableBox.addParcel(perishableParcel);
                allParcels.add(perishableParcel);
                break;
        }
    }

    private static void sendParcels() {// Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        double allPrice = 0;// Посчитать общую стоимость всех доставок и вывести на экран
        for (Parcel parcel : allParcels) {
            allPrice += parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость доставки будет: " + allPrice);
    }

    private static void tracking() {

        for (Trackable trackable : allTrackables) {
            System.out.println("Введите новое местоположение (для посылки " + ((Parcel) trackable).getDescription() + " ) : ");
            String newLocation = scanner.nextLine();
            trackable.reportStatus(newLocation);
        }
    }

    private static void showBox() {
        System.out.println("Выберите тип коробки для просмотра: ");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                List<StandardParcel> parcelsStandart = standardBox.getAllParcels();
                if (parcelsStandart.isEmpty()) {
                    System.out.println("Коробка пуста.");
                    return;
                }
                for (int i = 0; i < parcelsStandart.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + parcelsStandart.get(i).getDescription());
                }
                break;
            case 2:
                List<FragileParcel> parcelsFragile = fragileBox.getAllParcels();
                if (parcelsFragile.isEmpty()) {
                    System.out.println("Коробка пуста.");
                    return;
                }
                for (int i = 0; i < parcelsFragile.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + parcelsFragile.get(i).getDescription());
                }
                break;
            case 3:
                List<PerishableParcel> parcelsPerishable = perishableBox.getAllParcels();
                if (parcelsPerishable.isEmpty()) {
                    System.out.println("Коробка пуста.");
                    return;
                }
                for (int i = 0; i < parcelsPerishable.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + parcelsPerishable.get(i).getDescription());
                }
                break;
        }
    }
}


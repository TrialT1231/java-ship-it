package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCostTest {

    @Test
    void testStandardParcelCost_Weight5_ShouldReturn10() {
        Parcel parcel = new StandardParcel("Книги", 5, "Москва", 1);
        assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    void testStandardParcelCost_Weight10_ShouldReturn20() {
        Parcel parcel = new StandardParcel("Техника", 10, "СПб", 2);
        assertEquals(20, parcel.calculateDeliveryCost());
    }

    @Test
    void testStandardParcelCost_Weight0_ShouldReturn0() {
        Parcel parcel = new StandardParcel("Пусто", 0, "Казань", 3);
        assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelCost_Weight3_ShouldReturn12() {
        Parcel parcel = new FragileParcel("Ваза", 3, "Москва", 1);
        assertEquals(12, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelCost_Weight7_ShouldReturn28() {
        Parcel parcel = new FragileParcel("Зеркало", 7, "Новгород", 2);
        assertEquals(28, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelCost_Weight0_ShouldReturn0() {
        Parcel parcel = new FragileParcel("Ничего", 0, "Пусто", 3);
        assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelCost_Weight4_ShouldReturn12() {
        Parcel parcel = new PerishableParcel("Молоко", 4, "Москва", 1, 5);
        assertEquals(12, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelCost_Weight10_ShouldReturn30() {
        Parcel parcel = new PerishableParcel("Овощи", 10, "СПб", 2, 3);
        assertEquals(30, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelCost_Weight0_ShouldReturn0() {
        Parcel parcel = new PerishableParcel("Пусто", 0, "Казань", 3, 7);
        assertEquals(0, parcel.calculateDeliveryCost());
    }
}
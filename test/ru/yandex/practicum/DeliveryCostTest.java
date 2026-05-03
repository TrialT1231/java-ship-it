package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @Test
    void testIsExpired_NotExpired_ShouldReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 1, 5);
        assertFalse(parcel.isExpired(4));
    }


    @Test
    void testIsExpired_Expired_ShouldReturnTrue() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 1, 5);
        assertTrue(parcel.isExpired(7));
    }


    @Test
    void testIsExpired_ExactExpirationDay_ShouldReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 1, 5);
        assertFalse(parcel.isExpired(6));
    }

    private ParcelBox<StandardParcel> box;
    private StandardParcel parcel1;
    private StandardParcel parcel2;
    private StandardParcel parcel3;

    @BeforeEach
    void setUp() {
        box = new ParcelBox<>(50.0);
        parcel1 = new StandardParcel("Книги", 15, "Москва", 1);
        parcel2 = new StandardParcel("Техника", 20, "СПб", 2);
        parcel3 = new StandardParcel("Мебель", 30, "Казань", 3);
    }


    @Test
    void testAddParcel_WeightWithinLimit_ShouldAdd() {
        box.addParcel(parcel1);
        assertEquals(1, box.getAllParcels().size());
    }


    @Test
    void testAddParcel_MultipleWithinLimit_ShouldAdd() {
        box.addParcel(parcel1);
        box.addParcel(parcel2);
        assertEquals(2, box.getAllParcels().size());
    }


    @Test
    void testAddParcel_ExactMaxWeight_ShouldAdd() {
        box.addParcel(parcel2);
        StandardParcel exactParcel = new StandardParcel("Точно в лимит", 30, "Воронеж", 4);
        box.addParcel(exactParcel);
        assertEquals(2, box.getAllParcels().size());
    }


    @Test
    void testAddParcel_ExceedsMaxWeight_ShouldNotAdd() {
        box.addParcel(parcel1);
        box.addParcel(parcel2);
        box.addParcel(parcel3);
        assertEquals(2, box.getAllParcels().size());
    }


    @Test
    void testAddParcel_SingleHeavyParcel_ShouldNotAdd() {
        StandardParcel heavyParcel = new StandardParcel("Очень тяжёлое", 60, "Сочи", 5);
        box.addParcel(heavyParcel);
        assertEquals(0, box.getAllParcels().size());
    }
}
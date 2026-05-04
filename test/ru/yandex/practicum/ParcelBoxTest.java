package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {

    private ParcelBox<StandardParcel> box;

    @BeforeEach
    void setUp() {
        box = new ParcelBox<>(50.0);
    }

    @Test
    void testAddParcel_WeightWithinLimit_ShouldAdd() {
        StandardParcel parcel = new StandardParcel("Книги", 15, "Москва", 1);
        box.addParcel(parcel);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_MultipleWithinLimit_ShouldAdd() {
        StandardParcel parcel1 = new StandardParcel("Книги", 15, "Москва", 1);
        StandardParcel parcel2 = new StandardParcel("Техника", 20, "СПб", 2);
        box.addParcel(parcel1);
        box.addParcel(parcel2);
        assertEquals(2, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_ExactMaxWeight_ShouldAdd() {
        StandardParcel exactParcel = new StandardParcel("Точно в лимит", 50, "Воронеж", 4);
        box.addParcel(exactParcel);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_WeightEqualToRemainingCapacity_ShouldAdd() {
        StandardParcel parcel1 = new StandardParcel("Первая", 20, "Москва", 1);
        StandardParcel parcel2 = new StandardParcel("Вторая", 20, "СПб", 2);
        StandardParcel parcel3 = new StandardParcel("Третья", 10, "Казань", 3);

        box.addParcel(parcel1);
        box.addParcel(parcel2);
        box.addParcel(parcel3);

        assertEquals(3, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_WeightOneKgAboveRemainingCapacity_ShouldNotAdd() {
        StandardParcel parcel1 = new StandardParcel("Первая", 20, "Москва", 1);
        StandardParcel parcel2 = new StandardParcel("Вторая", 20, "СПб", 2);
        StandardParcel parcel3 = new StandardParcel("Слишком тяжёлая", 11, "Казань", 3);

        box.addParcel(parcel1);
        box.addParcel(parcel2);
        box.addParcel(parcel3);

        assertEquals(2, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_ExceedsMaxWeight_ShouldNotAdd() {
        StandardParcel parcel1 = new StandardParcel("Книги", 30, "Москва", 1);
        StandardParcel parcel2 = new StandardParcel("Техника", 30, "СПб", 2);

        box.addParcel(parcel1);
        box.addParcel(parcel2);

        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_SingleHeavyParcel_ShouldNotAdd() {
        StandardParcel heavyParcel = new StandardParcel("Очень тяжёлое", 60, "Сочи", 5);
        box.addParcel(heavyParcel);
        assertEquals(0, box.getAllParcels().size());
    }

    @Test
    void testGetAllParcels_EmptyBox_ShouldReturnEmptyList() {
        assertTrue(box.getAllParcels().isEmpty());
    }

    @Test
    void testGetAllParcels_ReturnsCopy_ShouldNotAffectOriginal() {
        StandardParcel parcel = new StandardParcel("Книги", 15, "Москва", 1);
        box.addParcel(parcel);

        List<StandardParcel> returnedList = box.getAllParcels();
        returnedList.clear();

        assertEquals(1, box.getAllParcels().size());
    }
}
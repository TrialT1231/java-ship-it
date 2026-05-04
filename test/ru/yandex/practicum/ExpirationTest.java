package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.*;

class ExpirationTest {

    @Test
    void testIsExpired_DeliveredOnDay4_WhenExpiresOnDay5_ShouldReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 1, 5);
        assertFalse(parcel.isExpired(4));
    }

    @Test
    void testIsExpired_DeliveredOnDay5_WhenExpiresOnDay5_ShouldReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 1, 5);
        assertFalse(parcel.isExpired(5));
    }

    @Test
    void testIsExpired_DeliveredOnDay6_WhenExpiresOnDay5_ShouldReturnTrue() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 1, 5);
        assertTrue(parcel.isExpired(6));
    }

    @Test
    void testIsExpired_DeliveredExactlyOnExpirationDay_ShouldReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 3, "СПб", 2, 3);
        assertFalse(parcel.isExpired(3));
    }

    @Test
    void testIsExpired_DeliveredOneDayBeforeExpiration_ShouldReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Йогурт", 2, "Казань", 5, 7);
        assertFalse(parcel.isExpired(6));
    }

    @Test
    void testIsExpired_DeliveredOneDayAfterExpiration_ShouldReturnTrue() {
        PerishableParcel parcel = new PerishableParcel("Мясо", 8, "Новгород", 3, 4);
        assertTrue(parcel.isExpired(8));
    }
}
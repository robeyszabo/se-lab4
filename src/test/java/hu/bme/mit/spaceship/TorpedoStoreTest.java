package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void getTorpedoCount_InitialCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        int count = store.getTorpedoCount();

        // Assert
        assertEquals(5, count);
    }

    @Test
    void fire_Success_DecreasesCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedoes_DecreasesCountByAmount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        boolean result = store.fire(3);

        // Assert
        assertEquals(true, result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedoes_StoreBecomesEmpty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 0.0);

        // Act
        store.fire(2);

        // Assert
        assertEquals(0, store.getTorpedoCount());
        assertEquals(true, store.isEmpty());
    }

    @Test
    void fire_Failure_KeepsCountAndReturnsFalse() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(false, result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_NegativeTorpedoes_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_FromEmptyStore_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void isEmpty_NonEmptyStore_ReturnsFalse() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        boolean result = store.isEmpty();

        // Assert
        assertEquals(false, result);
    }

    @Test
    void isEmpty_EmptyStore_ReturnsTrue() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0, 0.0);

        // Act
        boolean result = store.isEmpty();

        // Assert
        assertEquals(true, result);
    }
}

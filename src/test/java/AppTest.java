import org.kyrylo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private ShoppingCart cart;
    private Smartphone smartphone1;
    private Smartphone smartphone2;

    @BeforeEach
    public void setUp() {
        cart = new ShoppingCart();
        smartphone1 = new Smartphone("Galaxy S23", "Samsung", 799.99);
        smartphone2 = new Smartphone("iPhone 15", "Apple", 999.99);
    }

    @Test
    public void testAddSmartphone() {
        cart.add(smartphone1, 1);
        assertTrue(cart.getItems().containsKey(smartphone1), "Корзина должна содержать добавленный смартфон");
        assertEquals(1, cart.getItems().get(smartphone1).intValue(), "Количество добавленного смартфона должно быть 1");
    }

    @Test
    public void testAddMultipleSmartphones() {
        cart.add(smartphone1, 1);
        cart.add(smartphone2, 2);
        assertEquals(2, cart.getItems().size(), "Количество разных смартфонов в корзине должно быть 2");
    }

    @Test
    public void testAddSameSmartphoneMultipleTimes() {
        cart.add(smartphone1, 1);
        cart.add(smartphone1, 2);
        assertEquals(3, cart.getItems().get(smartphone1).intValue(), "Количество одного и того же смартфона должно суммироваться");
    }

    @Test
    public void testRemoveSmartphone() {
        cart.add(smartphone1, 1);
        cart.remove(smartphone1);
        assertFalse(cart.getItems().containsKey(smartphone1), "Корзина не должна содержать удаленный смартфон");
    }

    @Test
    public void testRemoveSmartphoneNotInCart() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.remove(smartphone2);
        }, "Должно быть выброшено исключение IllegalArgumentException при удалении смартфона, которого нет в корзине");
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() throws Exception {
        parser = new Parser();
    }

    @Test
    @DisplayName("Currency change should work")
    public void testExchange() {
        assertEquals(22.43, parser.exchanger(5, "PLN"),
                "Currency change should work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    public void testExchangeWithZeroAmount() {
        assertEquals(0, parser.exchanger(0, "PLN"), "Exchange with zero should be zero");
        assertEquals(0, parser.exchanger(0, "GBP"), "Exchange with zero should be zero");
    }

    @Test
    @DisplayName("Checking if amount is greater than zero")
    void exceptionTestingIfAmountGreaterThanZero() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> parser.exchanger(-5,"PLN"));
        assertEquals("Wrong input", exception.getMessage());
    }
    @Test
    @DisplayName("Checking if the input currency is correct")
    void exceptionTestingIfCurrencyCorrect() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> parser.exchanger(5,"PLgfgN"));
        assertEquals("Wrong input", exception.getMessage());
    }

}

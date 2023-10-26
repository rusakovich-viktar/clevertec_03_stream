package by.clevertec;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MainTest {

    @AfterEach
    public void cleanUp() {
        System.setOut(System.out);
    }

    @Test
    public void testTask16_NoContainsExpectedResult() {
        // Given
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "Adams (17 years old)";

        // When
        Main.task16();

        // Then
        assertFalse(expectedOutput.trim().equals(outContent.toString().trim()));
    }
}

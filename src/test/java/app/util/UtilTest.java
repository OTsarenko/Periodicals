package app.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class UtilTest {

    @Test
    void validateDateBedInputTest(){
            assertThrows(IOException.class, () -> Utility.validateData(null));
            assertThrows(IOException.class, () -> Utility.validateData(""));
    }

    @ParameterizedTest
    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("test1", "test1"),
                Arguments.of("String", "String"),
                Arguments.of("5", "5")
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testValidateData(String in, String expected) {
        try {
            Assertions.assertEquals(expected, Utility.validateData(in));
        } catch (IOException e) {
            fail("Cannot validate");
        }
    }
}

package ru.geekbrains.qa.level3.lession4;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Story("Треугольники")
public class TriangleTest {


    // test for method Triangle.isTriangleExist()
    @ParameterizedTest
    @DisplayName("triangle not exist test")
    @CsvFileSource(resources = "/triangles.csv", numLinesToSkip = 1)
    @Step("Проверка существования треугольника")
    @Severity(SeverityLevel.BLOCKER)
    void isTriangleExistTest(int a, int b, int c, boolean result) {
        Triangle triangle = new Triangle(a, b, c);
        Assertions.assertEquals(result, triangle.isTriangleExist());
    }

    // test for exception at method Triangle.triangleSquare() with wrong triangle
    @ParameterizedTest
    @DisplayName("exception for Triangle.triangleSquare()")
    @Step("Проверка выброса исключения при вычислении площади треугольника")
    @Severity(SeverityLevel.BLOCKER)
    @CsvSource({"1,2,4"})
    void triangleSquareWithExceptionTest(int a, int b, int c) {
        Triangle testTriangle = new Triangle(a,b,c);
        assertThrows(TriangleNotExistException.class, testTriangle::triangleSquare);
    }

    // positive test with good data for Triangle.triangleSquare() method
    @ParameterizedTest
    @DisplayName("positive test for Triangle.triangleSquare()")
    @Step("Проверка вычисления площади треугольника")
    @Severity(SeverityLevel.BLOCKER)
    @CsvSource({"5,5,6,12", "1,2,2,0.9682458365518543"})
    void goodSidesSizesGivenOfTriangleSquareTest(int a, int b, int c, double result) throws Exception {
        Triangle testTriangle = new Triangle(a,b,c);
        Assertions.assertEquals(result, testTriangle.triangleSquare());
    }

    // AssertJ usage test for degenerated triangle
    @Test
    @DisplayName("degenerated triangle")
    @Step("Проверка вычисления площади треугольника")
    @Severity(SeverityLevel.MINOR)
    void assertjDegenerateTriangleOfTriangleSquareTest() throws Exception {
        assertThat(new Triangle(1,2,3).triangleSquare()).isEqualTo(0);
    }

}
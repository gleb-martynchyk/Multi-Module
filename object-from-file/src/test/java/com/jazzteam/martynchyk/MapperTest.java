package com.jazzteam.martynchyk;

import com.jazzteam.martynchyk.entity.Car;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class MapperTest {

    private Mapper mapper;
    private Car carExpected;
    private Car carActual;
    private String carString;
    private String filePath = "C:\\Users\\Glyanan\\IdeaProjects\\task1" +
            "\\object-from-file\\src\\main\\resources\\object.txt";

    @BeforeMethod
    public void setUp() {
        mapper = new Mapper();
        carExpected = new Car("Subary", 4, 200f);
        carString = "com.jazzteam.martynchyk.entity.Car\n" +
                "{\"name\":\"Subary\",\"capacity\":4,\"speed\":200.0}";
    }

    @Test
    public void testObjectToString() {
        assertEquals(mapper.objectToString(carExpected), carString);
    }

    @Test
    public void testObjectToStringNegative() {
        assertNotEquals(mapper.objectToString("ooops"), carString);
    }

    @Test
    public void testStringToObject() {
        assertEquals(mapper.stringToObject(carString), carExpected);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testStringToObjectNegative() {
        assertNotEquals(mapper.stringToObject(""), carExpected);
    }

    @Test
    public void testFileToObject() {
        mapper.objectToFile(carExpected, filePath);
        carActual = (Car) mapper.fileToObject(filePath);
        assertEquals(carActual, carExpected);
    }

    @Test
    public void testFileToObjectNegative() {
        mapper.objectToFile("ooops", filePath);
        Object object = mapper.fileToObject(filePath);
        assertNotEquals(object, carExpected);
    }

    @Test
    public void testObjectToFile() {
        mapper.objectToFile(carExpected, filePath);
        assertEquals(mapper.fileToObject(filePath), carExpected);
    }
}
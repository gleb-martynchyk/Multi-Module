package com.jazzteam.martynchyk;

import com.jazzteam.martynchyk.entity.Car;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class MapperTest {

    private Car carActual;
    private Mapper mapper = new Mapper();
    private Car carExpected = new Car("Subary", 4, 200f);
    private String filePath = getClass().getResource("/object.txt").getPath();
    private String carString = "com.jazzteam.martynchyk.entity.Car\n" +
            "{\"name\":\"Subary\",\"capacity\":4,\"speed\":200.0}";


    @Test
    public void testObjectToString() {
        assertEquals(mapper.objectToString(carExpected), carString);
    }

    @Test
    public void testObjectToStringNegative() {
        assertNotEquals(mapper.objectToString(""), carString);
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
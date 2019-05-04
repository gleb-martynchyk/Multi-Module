package org.jazzteam.martynchyk;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UsersJDBCTest {

    @Test
    public void testIsPasswordCorrect() {
        assertTrue(UsersJDBC.isPasswordCorrect("admin", "admin".hashCode()));
    }

    @Test
    public void testIsPasswordCorrectSec() {
        assertTrue(UsersJDBC.isPasswordCorrect("gleb", "1111".hashCode()));
    }

    @Test
    public void testIsPasswordCorrectNegative() {
        assertFalse(UsersJDBC.isPasswordCorrect("admin", "admin1".hashCode()));
    }

    @Test
    public void testIsPasswordCorrectNegativeSec() {
        assertFalse(UsersJDBC.isPasswordCorrect("", "".hashCode()));
    }
}
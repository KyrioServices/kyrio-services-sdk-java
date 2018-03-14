package com.kyrio.services.shared;

import org.junit.*;

import static org.junit.Assert.*;

public class RandomDataTest {
    @Test
    public void testNextInteger() {
        int value1 = RandomData.nextInteger(0, 100);
        int value2 = RandomData.nextInteger(100);
        int value3 = RandomData.nextInteger(100);

        assertTrue(value1 != value2 || value2 != value3);
    }

    @Test
    public void testNextBoolean() {
        boolean value1 = RandomData.nextBoolean();
        boolean value2 = RandomData.nextBoolean();
        boolean value3 = RandomData.nextBoolean();

        //assertTrue(value1 != value2 || value2 != value3);
    }

    @Test
    public void testChance() {
        boolean value1 = RandomData.chance(1, 10);
        boolean value2 = RandomData.chance(1, 10);
        boolean value3 = RandomData.chance(1, 10);

        //assertTrue(value1 != value2 || value2 != value3);
    }

    @Test
    public void testPick() {
        int value1 = RandomData.pick(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        int value2 = RandomData.pick(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        int value3 = RandomData.pick(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });

        assertTrue(value1 != value2 || value2 != value3);
    }

    @Test
    public void testNextProvider() {
        Provider value1 = RandomData.nextProvider();
        Provider value2 = RandomData.nextProvider();
        Provider value3 = RandomData.nextProvider();

        assertTrue(value1.getId() != value2.getId() || value2.getId() != value3.getId());
    }
}

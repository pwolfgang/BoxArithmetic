/*
 * Copyright (C) 2026 Paul
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.pwolfgang.boxarithmetic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul
 */
public class SingletonTest {
    void printIt(String s, Box box) {
        System.out.printf("%s: %s%n", s, box.toString());
        System.out.printf("%s: %s%n", s, box.toIntegerString());
        System.out.printf("%s: %s%n", s, box.asPolyNumber());
    }

    public SingletonTest() {
    }

    @Test
    public void testToString() {
        var test = Box.parse("[0]");
        printIt("test", test);
        var e0 = new Singleton(Box.of(0));
        System.out.printf("e0: %s%n", e0.toRawString());
        printIt("e0", e0);
        var e1 = new Singleton(Box.of(1));
        System.out.printf("e1: %s%n", e1.toRawString());
        printIt("e1", e1);
        var e2 = new Singleton(Box.of(2));
        System.out.printf("e2: %s%n", e2.toRawString());
        printIt("e2", e2);
        var e3 = new Singleton(Box.of(3));
        System.out.printf("e3: %s%n", e3.toRawString());
        printIt("e3", e3);
        var e4 = new Singleton(Box.of(4));
        System.out.printf("e4: %s%n", e4.toRawString());
        printIt("e4", e4);
        System.out.println("\u03b1\u207f id e\u2099");
    }
    
    @Test
    public void testMulPixel() {
        var s = Singleton.of(4);
        var p = Pixel.of(4,5);
        assertEquals(Singleton.of(5), s.mul(p));
        assertNull(s.mul(Pixel.of(5,3)));
    }
    
}

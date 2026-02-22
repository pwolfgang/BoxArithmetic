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
    public void testAlphas() {
        IO.println("\n\n\testAlphas");
        System.out.println("\n\ntestAlphas");
        var e0 = Singleton.of(0);
        printIt("e0", e0);
        var e1 = Singleton.of(1);
        printIt("e1", e1);
        var e2 = Singleton.of(2);
        printIt("e2", e2);
        var e3 = Singleton.of(3);
        printIt("e3", e3);
        var e4 = Singleton.of(4);
        printIt("e4", e4);
        IO.println("\u03B1\u2099 = e\u2099");
        
    }
    
    @Test
    public void testBCD() {
        IO.println("\n\ntestBCD");
        var B = Singleton.of(7);
        var C = Singleton.of(0);
        var D = new Singleton(new EmptyBox());
        printIt("B", B);
        printIt("C", C);
        printIt("D", D);
    }
    
    @Test
    public void testMulPixel() {
        var s = Singleton.of(4);
        var p = Pixel.of(4,5);
        var sXp = s.mul(p);
        var five = Singleton.of(5);
        var areEqual = five.equals(sXp);
        assertEquals(Singleton.of(5), s.mul(p));
        assertNull(s.mul(Pixel.of(5,3)));
    }
    
    @Test
    public void testVexel() {
        IO.println("testVexel");
        var e0 = Singleton.of(0);
        printIt("e0", e0);
        var e1 = Singleton.of(1);
        printIt("e1", e1);
        var e2 = Singleton.of(2);
        printIt("e2", e2);
        var e3 = Singleton.of(3);
        printIt("e3", e3);
        var e4 = Singleton.of(4);
        printIt("e4", e4);
        var vexel = Box.add(e0,e1,e2,e3,e4);
        printIt("vexel: ", vexel);

        
    }
    
}

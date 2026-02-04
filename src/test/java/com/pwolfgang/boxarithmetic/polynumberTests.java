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
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul
 */
public class polynumberTests {
    
    void printIt(String s, Box box) {
        System.out.printf("%s: %s%n", s, box.toString());
        System.out.printf("%s: %s%n", s, box.toIntegerString());
        System.out.printf("%s: %s%n", s, box.asPolyNumber());
    }

    
    @Test
    public void testAddition() {
        var p1 = Box.parse("[0 0 0 0 1 1 1 2 2 2 2 2 2 2]");
        var p2 = Box.parse("[0 0 0 0 0 0 1 1]");
        var p3 = Box.parse("[0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 2 2 2 2 2 2 2]");
        printIt("p1: ", p1);
        printIt("p2: ", p2);
        printIt("p3: ", p3);
        assertEquals(p3, Box.add(p1, p2));
    }
    
    @Test
    public void testMul() {
        var A = Box.parse("[1 3]");
        var B = Box.parse("[0 0 2]");
        var AxB = Box.parse("[1 1 3 3 3 5]");
        printIt("A: ", A);
        printIt("B: ", B);
        printIt("AxB: ", AxB);
        assertEquals(AxB, A.mul(B));
    }
    
    @Test
    public void testPow() {
        Box three = Box.of(3);
        Box two = Box.of(2);
        Box one = Box.of(1);
        assertEquals(Box.of(3), three.pow(one));
        assertEquals(Box.of(9), three.pow(two));
        assertEquals(Box.of(1), three.pow(Box.of(0)));
    }
    
    @Test
    public void testEval() {
        System.out.println("\n\ntestEval");
        var p = Box.parse("[0 1 1 2]");
        printIt("p: ", p);
        var expected = Box.of(36);
        System.out.println(expected.asPolyNumber());
        var actual = p.eval(Box.of(5));
        printIt("actual: ", actual);
        assertEquals(expected, actual);
    }
    
}

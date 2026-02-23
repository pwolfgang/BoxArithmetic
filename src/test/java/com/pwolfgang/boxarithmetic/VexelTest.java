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
public class VexelTest {
    void printIt(String s, Box box) {
        System.out.printf("%s: %s%n", s, box.toString());
        System.out.printf("%s: %s%n", s, box.toIntegerString());
        System.out.printf("%s: %s%n", s, box.asPolyNumber());
    }
    
    public VexelTest() {
    }

    @Test
    public void testMul() {
        
        var maxel = Maxel.of(new int[][]
                {{1,  2,  3,  4},
                 {5,  6,  7,  8},
                 {9, 10, 11, 12},
                 {13, 14, 15, 16}});
        
        var vexel = Vexel.of(2, 2, 2, 2);
        var prod = vexel.mul(maxel);
        var expected = Vexel.of(56, 64, 72, 80);
        System.out.println(prod.toVectorString());
        assertEquals(expected, prod);
    }

    // Tests both ol and toVectorString
    @Test
    public void testOf() {
        System.out.println("\n\ntestOf");
        var vexel = Vexel.of(2,1,0,0,1);
        printIt("(2,1,0,0,1):", vexel);
        System.out.println(vexel.toVectorString());
        var actual = vexel.toVectorString();
        assertEquals("(2, 1, 0, 0, 1)", actual);
    }
    
    @Test
    public void testUnion() {
        System.out.println("\n\ntestUnion");
        var a = Vexel.of(3, 2, 0, 0, 1);
        var b = Vexel.of(1, 0, 2, 0, 1);
        var e = Vexel.of(3, 2, 2, 0, 1);
        var aUb = a.union(b);
        System.out.println(a.toCompressedIntegerString());
        System.out.println(a.asPolyNumber());
        System.out.println(b.toCompressedIntegerString());
        System.out.println(aUb.toCompressedIntegerString());
        System.out.println(e.toCompressedIntegerString());
        assertEquals(e, aUb);
    }

    @Test
    public void testIntersection() {
        System.out.println("\n\ntestIntersection");
        var a = Vexel.of(3, 2, 0, 0, 1);
        var b = Vexel.of(1, 0, 2, 0, 1);
        var e = Vexel.of(1, 0, 0, 0, 1);
        var aUb = a.intersection(b);
        System.out.println(a.toCompressedIntegerString());
        System.out.println(b.toCompressedIntegerString());
        System.out.println(aUb.toCompressedIntegerString());
        System.out.println(e.toCompressedIntegerString());
        assertEquals(e, aUb);
    }
    
    @Test
    public void testEs() {
        System.out.println("\n\nteseEs");
        var vexel = Vexel.of(1,1,1,1,1);
        var asPoly = vexel.asPolyNumber();
        printIt("(1,1,1,1,1)", vexel);
    }
    
    @Test
    public void testExample() {
        IO.println("\n\n\ntestExample");
        var vexel = Vexel.of(3,2,0,0,1);
        IO.println(vexel.toCompressedIntegerString());
        IO.println(vexel.asPolyNumber());
    }
    
    @Test
    public void testExample2() {
        IO.println("Lecture example");
        var alpha = Box.parse("[1]");
        var onePalphaSq = Box.parse("[0 [0 0]]");
        var alpha3 = Box.parse("[[0 0 0]]");
        printIt("alpha", alpha);
        printIt("1 + \u03b1\u00b2", onePalphaSq);
        printIt("\u03b1\u00b3", alpha3);
        var v = new Vexel(
                Singleton.of(2),
                Singleton.of(2),
                Singleton.of(2),
                new Singleton(alpha),
                new Singleton(onePalphaSq));
        System.out.printf("v: %s%n", v.toCompressedIntegerString());
        var w = new Vexel(Singleton.of(2), new Singleton(alpha3));
        System.out.printf("w: %s%n", w.toCompressedIntegerString());
        var result = v.add(Box.of(2).mul(w));
        System.out.printf("v+2w: %s%n", result.toCompressedIntegerString());
        
       

    }

    
}

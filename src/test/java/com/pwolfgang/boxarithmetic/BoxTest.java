/*
 * Copyright (C) 2023 Paul Wolfgang <paul@pwolfgang.com>
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

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BoxTest {
    
    public BoxTest() {
    }
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    void printIt(String s, Box box) {
        System.out.printf("%s: %s%n", s, box.toString());
        System.out.printf("%s: %s%n", s, box.toIntegerString());
        System.out.printf("%s: %s%n", s, box.asPolyNumber());
    }

    
    @Test
    public void testCreateEmptySet() {
        System.out.println("testCreateEmptySet");
        var m = Box.of();
        printIt("m", m);
        assertEquals(0, m.intSize());
        assertEquals("[ ]", m.toString());
        assertEquals("0", m.toIntegerString());
        assertEquals("0", m.asPolyNumber());
    }
    
    @Test
    public void testOne() {
        System.out.println("testOne");
        var zero = Box.of();
        var one = Box.of(zero);
        printIt("one", one);
        assertEquals(1, one.intSize());
        assertEquals("[[ ]]", one.toString());
        assertEquals("1", one.toIntegerString());
        assertEquals("1", one.asPolyNumber());
    }
    
    @Test
    public void testPoly() {
        System.out.println("testPoly");
        var zero = Box.of();
        var two = Box.of(Box.of(), Box.of());
        var oneOneTwo = Box.of(Box.of(zero), Box.of(zero), two);
        printIt("oneOneTwo", oneOneTwo);
        assertEquals("[1 1 2]", oneOneTwo.toIntegerString());
    }
    
    @Test 
    public void testPoly2() {
        System.out.println("testPoly2");
        var boxOf13 = Box.of(Box.of(13));
        printIt("13", boxOf13);
        assertEquals("[13]", boxOf13.toIntegerString());
    }
    
    
    @Test
    public void testMulti() {
        System.out.println("testMulti");
        var oneOne = Box.of(Box.of(1), Box.of(1));
        var threeFiveEleven = Box.of(Box.of(3), Box.of(5), Box.of(11));
        var test1 = Box.of(oneOne, oneOne);
        var test2 = Box.of(threeFiveEleven);
        var test3 = Box.of(Box.of(0));
        var test4 = Box.of(0);
        assertEquals("[[1 1] [1 1]]", test1.toIntegerString());
        printIt("2α₁²", test1);
        assertEquals("[[3 5 11]]", test2.toIntegerString());
        printIt("α₃α₅α₁₁", test2);
        assertEquals("1", test3.toIntegerString());
        assertEquals("0", test4.toIntegerString());
        printIt("1", test3);
        printIt("0", test4);
    }
    
    @Test
    public void testAdd() {
        System.out.println("testAdd");
        var a = Box.of(Box.of(Box.of(4)), Box.of(Box.of(3)));
        var b = Box.of(Box.of(Box.of(1),Box.of(1),Box.of(2)),Box.of(Box.of(4)), Box.of(0));
        var c = Box.of(Box.of(4),Box.of(Box.of(1),Box.of(2),Box.of(1)));
        printIt("a", a);
        printIt("b", b);
        printIt("c", c);
        var sum = Box.add(a, b, c);
        printIt("a+b+c",sum);
        var expected = Box.parse("[0 4 [3] [4] [4] [1 1 2] [1 1 2]]");
        assertEquals(expected, sum);
    }
    
    @Test
    public void testAdd2() {
        System.out.println("testAdd2");
        var a = Box.of(Box.of(3),Box.of(3),Box.of(4));
        var b = Box.of(0);
        var c = Box.of(Box.of(0));
        var d = Box.of(Box.of(3),Box.of(7));
        var sum = Box.add(a, b, c, d);
        printIt("a", a);
        printIt("b", b);
        printIt("C", c);
        printIt("d", d);
        printIt("a+b+c+d",sum);
        var expected = Box.parse("[0 3 3 3 4 7]");
        assertEquals(expected, sum);
    }
    
    @Test
    public void testMulNat() {
        System.out.println("testMulNat");
        var x = Box.of(2);
        var y = Box.of(3);
        var p = Box.mul(x,y);
        printIt("x", x);
        printIt("y", y);
        printIt("x × y", p);
        assertEquals("6", p.toIntegerString());
    }
    
    @Test
    public void testMulPoly() {
        System.out.println("testMulPoly");
        var x = Box.of(Box.of(2), Box.of(3));
        var y = Box.of(Box.of(1), Box.of(1), Box.of(0));
        var z = Box.of(Box.of(3),Box.of(3),Box.of(2),Box.of(4),Box.of(4),Box.of(3));
        printIt("x", x);
        printIt("y", y);
        printIt("x × y", Box.mul(x,y));
        assertEquals(z.toIntegerString(),Box.mul(x,y).toIntegerString());
    }
    
    @Test
    public void testMulPoly2() {
        System.out.println("testMulPoly2");
        var x = Box.of(Box.of(0), Box.of(3));
        var y = Box.of(Box.of(1), Box.of(4));
        var z = Box.of(Box.of(2), Box.of(7));
        var e = Box.of(Box.of(3), Box.of(8), Box.of(6), Box.of(11), Box.of(6), Box.of(11), Box.of(9), Box.of(14));
        var r = Box.mul(x, y, z);
        printIt("x", x);
        printIt("y", y);
        printIt("z", z);
        printIt("x × y × z", r);
        assertEquals(e, r);     
    }
    
    @Test
    public void testMulMulti() {
        System.out.println("testMulMulti");
        var x = Box.of(Box.of(Box.of(0), Box.of(0), Box.of(2)),Box.of(Box.of(3),Box.of(8)));
        var y = Box.of(Box.of(Box.of(11)),Box.of(2),Box.of(Box.of(9)));
        printIt("x", x);
        printIt("y", y);
        var r = Box.mul(x, y);
        var e = Box.parse("[[3 8 11] [3 8 9] [0 0 3 8] [0 0 2 11] [0 0 2 9] [0 0 0 0 2]]");
        printIt("x × y", r);
        assertEquals(e, r);
   }
    
    @Test
    public void testMulMulti2() {
        System.out.println("testMulMulti2");
        var x = Box.of(Box.of(Box.of(2)),Box.of(3));
        var y = Box.of(Box.of(Box.of(4)),Box.of(Box.of(4)));
        var z = Box.of(Box.of(Box.of(1),Box.of(6)),Box.of(0));
        var r = Box.mul(x,y,z);
        var e = Box.parse("[[2 4] [2 4] [0 0 0 4] [0 0 0 4] [1 2 4 6] [1 2 4 6] [0 0 0 1 4 6] [0 0 0 1 4 6]]");
        printIt("x", x);
        printIt("y", y);
        printIt("x", z);
        printIt("x × y × z", r);
        assertEquals(e, r);
    }
                      
    @Test
    public void testEquals() {
        System.out.println("Test Equals");
        var three_1 = Box.of(3);
        var three_2 = Box.of(3);
        var alphaSubOne_1 = Box.of(Box.of(Box.of(1)));
        var alphaSubOne_2 = Box.of(Box.of(Box.of(1)));
        var twoAlphaTwoSq_1 = Box.of(Box.of(Box.of(Box.of(2)),Box.of(Box.of(2))),Box.of(Box.of(Box.of(2)),Box.of(Box.of(2))));
        var twoAlphaTwoSq_2 = Box.of(Box.of(Box.of(Box.of(2)),Box.of(Box.of(2))),Box.of(Box.of(Box.of(2)),Box.of(Box.of(2))));
        System.out.printf("%s %s%n", "alpha sub one", alphaSubOne_1.toString());
        System.out.printf("%s %s%n", "alpha sub one", alphaSubOne_1.toIntegerString());
        System.out.printf("%s %s%n", "two alpha sub two squared", twoAlphaTwoSq_1.toString());
        System.out.printf("%s %s%n", "two alpha sub two squared", twoAlphaTwoSq_1.toIntegerString());
        assertEquals(three_1,three_2);
        assertEquals(alphaSubOne_1,alphaSubOne_2);
        assertEquals(twoAlphaTwoSq_1,twoAlphaTwoSq_2);
        assertNotEquals(alphaSubOne_1,twoAlphaTwoSq_1);
    }
    
    @Test
    public void testTruncation() {
        System.out.println("\n\ntestTruncation");
        Box A = Box.parse("[7 7 [3 5] [[4] 2]]");
        Box B = Box.of(7);
        Box C = Box.parse("[3 5]");
        Box D = Box.of(8);
        printIt("A: ", A);
        printIt("B: ", B);
        printIt("C: ", C);
        printIt("D: ", D);
        Box tBofA = Box.parse("[7 7]");
        Box tCofA = Box.parse("[[3 5]]");
        Box tDofA = Box.of(0);
        assertEquals(tBofA, A.tB(B));
        assertEquals(tCofA, A.tB(C));
        assertEquals(tDofA, A.tB(D));
    }
        
}

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
public class ListBoxTest {
    
    public ListBoxTest() {
    }

    @Test
    public void testPi() {
        var l1 =new Pixel(Box.of(2),Box.of(Box.of(3)));
        var alpha = Box.of(Box.of(1));
        var alphaSq = Box.of(Box.of(2));
        var onePlusAlpha = Box.of(1).add(alpha);
        System.out.printf("1 + \u03b1: %s%n", onePlusAlpha.asPolyNumber());
        System.out.printf("1 + \u03b1: %s%n", onePlusAlpha.toIntegerString());
        assertEquals("1+α₀", onePlusAlpha.asPolyNumber());
        assertEquals("\u230a0, 1\u230b", onePlusAlpha.toIntegerString());        
        var l2 = new BoxList(Box.of(0), Box.of(0), Box.of(alpha), onePlusAlpha);
        var l3 = new BoxList(Box.of(5), Box.of(Box.of(alphaSq)), Box.of(1));
        System.out.printf("l1: %s%n", l1.toIntegerString());
        System.out.printf("l2: %s%n", l2.toIntegerString());
        System.out.printf("l3: %s%n", l3.toIntegerString());
        var X = new ListBox(l1, l2, l3);
        String XasString = X.toCompressedIntegerString();
        System.out.printf("X: %s%n", XasString);
        System.out.printf("X.pi(1) %s%n", X.pi(1).toCompressedIntegerString());
        System.out.printf("X.pi(2) %s%n", X.pi(2).toCompressedIntegerString());
        System.out.printf("X.pi(3) %s%n", X.pi(3).toCompressedIntegerString());
        var Xpi1 = Box.parse("\u230a2 5 0\u230b");
        var Xpi2 = Box.parse("\u230a0 \u230a\u230a\u230a2\u230b\u230b\u230b \u230a3\u230b\u230b");
        var Xpi3 = Box.parse("\u230a1 \u230a\u230a1\u230b\u230b\u230b");
        assertEquals(Xpi1, X.pi(1));
        assertEquals(Xpi2, X.pi(2));
        assertEquals(Xpi3, X.pi(3));
        var M = new Maxel
        (Pixel.of(2,3), 
        Pixel.of(2, 3),
        Pixel.of(0,5),
        Pixel.of(4,5),
        Pixel.of(4,2),
        Pixel.of(2,7));
        System.out.printf("M = %s%n", M.toIntegerString());
        System.out.printf("M.pi(1) = %s%n", M.pi(1).toIntegerString());
        System.out.printf("M.pi(2) = %s%n", M.pi(2).toIntegerString());
        assertEquals(Box.parse("\u230a0 2 2 2 4 4\u230b"), M.pi(1));
        assertEquals(Box.parse("\u230a2 3 3 5 5 7\u230b"), M.pi(2));
    }
    
}

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
        var expected = vexel.of(56, 64, 72, 80);
        System.out.println(prod.toVectorString());
        assertEquals(expected, prod);
    }

    // Tests both ol and toVectorString
    @Test
    public void testOf() {
        System.out.println("\n\ntestOf");
        var vexel = Vexel.of(2,1,0,0,1);
        System.out.println(vexel.toVectorString());
        assertEquals("(2, 1, 0, 0, 1)", vexel.toVectorString());
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

    
}

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
public class MaxelTest {
    
    public MaxelTest() {
    }

    @Test
    public void testMul() {
        var M = new Maxel(Pixel.of(0,0), Pixel.of(1,0));
        var N = new Maxel(Pixel.of(1,0), Pixel.of(0,2), Pixel.of(2,3));
        System.out.println(M.toIntegerString());
        System.out.println(M.toCompressedIntegerString());
        System.out.println(N.toIntegerString());
        System.out.println(N.toCompressedIntegerString());      
        var expected = new Maxel(Pixel.of(0,2),Pixel.of(1,2));
        assertEquals(expected, M.mul(N));
    }
    
    @Test
    public void testExample23() {
        var M = new Maxel(Pixel.of(0,0), Pixel.of(0, 0), Pixel.of(1,0), Pixel.of(0,2),Pixel.of(0,2),Pixel.of(0,2));
        System.out.println(M.toIntegerString());
        System.out.println(M.toCompressedIntegerString());
        var N = Maxel.of(new int[][]
                {{0, 4, 0},
                {1, 0, 0},
                {0, 7, 0},
                {0, 0, 5}
                });
        System.out.println(N.toCompressedIntegerString());
        System.out.println(M.mul(N).toCompressedIntegerString());
    }
    
    @Test
    public void testMulVexel() {
                var maxel = Maxel.of(new int[][]
                {{1,  2,  3,  4},
                 {5,  6,  7,  8},
                 {9, 10, 11, 12},
                 {13, 14, 15, 16}});
        
        var vexel = Vexel.of(2, 2, 2, 2);
        var prod = maxel.mul(vexel);
        var expected = vexel.of(20,52,84,116);
        System.out.println(prod.toVectorString());
        assertEquals(expected, prod);

    }
    
}

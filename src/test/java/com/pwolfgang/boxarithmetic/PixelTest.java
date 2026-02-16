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
public class PixelTest {
    
    public PixelTest() {
    }

    @Test
    public void testPixel() {
        var p = new Pixel(Box.of(3), Box.of(5));
        System.out.println(p.toIntegerString());
    }
    
    @Test
    public void testMul() {
        var A = new Pixel(Box.of(3), Box.of(4));
        var B = new Pixel(Box.of(4), Box.of(11));
        var AxB = A.mul(B);
        var BxA = B.mul(A);
        System.out.println(A.toIntegerString());
        System.out.println(B.toIntegerString());
        System.out.println(AxB.toIntegerString());
        if (BxA == null) {
            System.out.println("null");
        } else {
            System.out.println(BxA.toIntegerString());
        }
        var expected = new Pixel(Box.of(3), Box.of(11));
        assertEquals(expected, AxB);
        assertNull(BxA);  
    }
    
    @Test
    public void testMulSingleton() {
        assertNull(Pixel.of(5,3).mul(Singleton.of(4)));
        assertEquals(Singleton.of(5),Pixel.of(5,4).mul(Singleton.of(4)));
    }
    
}

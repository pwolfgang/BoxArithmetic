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
       
    }
    
}

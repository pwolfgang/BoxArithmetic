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
public class CrtText {
    
    @Test
    public void testCrt() {
        var A = Box.parse("[1 3 2]");
        var B = Box.parse("[2 4]");
        var C = Box.parse("[2 4 6 12 4 8]");
        System.out.printf("A: %s%n", A.toCompressedIntegerString());
        System.out.printf("B: %s%n", B.toCompressedIntegerString());
        System.out.printf("A \u2227 B: %s%n", A.crt(B).toIntegerString());
        assertEquals(C, A.crt(B));
    }
    
    @Test
    public void testMultiNumbers() {
        var M = Box.parse("[[3]]");
        var N = Box.parse("[[4]]");
        var X = Box.parse("[[7]]");
        var McN = M.crt(N);
        System.out.printf("M: %s%n", M.asPolyNumber());
        System.out.printf("N: %s%n", N.asPolyNumber());
        System.out.printf("M \u2227 N %s%n", McN.asPolyNumber());
        assertEquals(X,McN);
    }
    
    @Test
    public void testExample9() {
        var B = Box.parse("[0 [3] [2 4]]");
        var C = Box.parse("[[1 1] [2 4]]");
        var X = Box.parse("[0 0 [4 4] [5 7] [3 3 5 5] [4 6 6 8]]");
        var BcC = B.crt(C);
        System.out.printf("B: %s%n", B.asPolyNumber());
        System.out.printf("C: %s%n", C.asPolyNumber());
        System.out.printf("B\u2227C: %s%n", BcC.asPolyNumber());
        assertEquals(X, BcC);
        
    }
    
    @Test
    public void testExample10() {
        var p = Box.parse("[3 3 4]");
        var q = Box.parse("[1 2]");
        System.out.printf("p %s%n", p.asPolyNumber());
        System.out.printf("s(p) %d%n", p.intSize());
        System.out.printf("\u2211(p) %s%n", p.sigma().toIntegerString());
        System.out.printf("q %s%n", q.asPolyNumber());
        System.out.printf("s(q) %d%n", q.intSize());
        System.out.printf("\u2211(q) %s%n", q.sigma().toIntegerString());
        var pXq = p.mul(q);
        System.out.printf("p×q: %s%n", pXq.toIntegerString());
        assertEquals(Box.of(29), pXq.sigma());
    }
    
    @Test
    public void testTheorum14() {
        System.out.println("\n\nTheroum14");
        var p2 = Box.parse("[1 2 4 8]");
        var p3 = Box.parse("[1 3 9]");
        var p5 = Box.parse("[1 5]");
        var p7 = Box.parse("[1 7]");
        var p11 = Box.parse("[1,11]");
        var s = Box.crt(p2, p3, p5, p7, p11).truncate(12);
        System.out.println(s.toIntegerString());
        var e = Box.parse("[1 2 3 4 5 6 7 8 9 10 11 12]");
        System.out.println(e.toIntegerString());
        assertEquals(e, s);
    }
    
}

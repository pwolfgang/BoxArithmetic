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
        IO.println("\n\ntestMul");
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
        IO.println("\n\ntestExample23");
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
        System.out.println(N.asMatrix());
        var MxN = M.mul(N);
        var MxNexpected = Maxel.of(new int[][]
           {{0, 29},
            {0,  4}
           });
        System.out.println(M.mul(N).toCompressedIntegerString());
        assertEquals(MxNexpected, MxN);
    }
    
    @Test
    public void testMulVexel() {
        IO.println("\n\ntestMulVexel");
                var maxel = Maxel.of(new int[][]
                {{1,  2,  3,  4},
                 {5,  6,  7,  8},
                 {9, 10, 11, 12},
                 {13, 14, 15, 16}});
        
        var vexel = Vexel.of(2, 2, 2, 2);
        var prod = maxel.mul(vexel);
        var expected = Vexel.of(20,52,84,116);
        System.out.println(prod.toVectorString());
        assertEquals(expected, prod);
    }
    
    @Test
    public void simpleExample() {
        System.out.println("\n\nSimpleExample");
        var m = Maxel.of(new int[][]
           {{1,2},
            {3,4}}
        );
        var v = Vexel.of(5,6);
        var vXm = v.mul(m);
        var mXv = m.mul(v);
        System.out.printf("%s: %s%n", "m", m.toCompressedIntegerString());
        System.out.printf("%s: %s%n", "v", v.toCompressedIntegerString());
        System.out.printf("%s: %s%n", "vXm", vXm.toCompressedIntegerString());
        System.out.printf("%s: %s%n", "mXv", mXv.toCompressedIntegerString());
        var vXmExpected = Vexel.of(23, 34);
        var mXvExpected = Vexel.of(17, 39);
        assertEquals(vXmExpected, vXm);
        assertEquals(mXvExpected, mXv);
    }
    
    @Test
    public void lectureExample() {
        IO.println("\n\nLecture Example");
        var p1 = new Pixel(Box.of(0),Box.of(Box.of(7)));
        var p2 = new Pixel(Box.of(1),Box.of(Box.of(1)));
        var p3 = new Pixel(Box.of(Box.of(1)), Box.of(2));
        var p4 = new Pixel(Box.of(Box.of(7)), Box.of(3));
        System.out.printf("p1: %s%n", p1.toIntegerString());
        System.out.printf("p2: %s%n", p2.toIntegerString());
        System.out.printf("p3: %s%n", p3.toIntegerString());
        System.out.printf("p4: %s%n", p4.toIntegerString());
        var M = new Maxel(p1,p2);
        var N = new Maxel(p3,p4);
        var MxN = M.mul(N);
        System.out.printf("M = %s%n", M.toCompressedIntegerString());
        System.out.printf("N = %s%n", N.toCompressedIntegerString());
        System.out.printf("M × N = %s%n", MxN.toCompressedIntegerString());
        var MxNExpected = Box.of(Pixel.of(1,2),Pixel.of(0,3));
        assertEquals(MxNExpected, MxN);
    }
    
    @Test
    public void testTranspose() {
        IO.println("\n\ntest Transpose");
        var p1 = new Pixel(Box.of(0),Box.of(Box.of(7)));
        var p2 = new Pixel(Box.of(1),Box.of(Box.of(1)));
        var p3 = new Pixel(Box.of(Box.of(1)), Box.of(2));
        var p4 = new Pixel(Box.of(Box.of(7)), Box.of(3));
        var p1T = new Pixel(Box.of(Box.of(7)), Box.of(0));
        var p2T = new Pixel(Box.of(Box.of(1)),Box.of(1));
        var p3T = new Pixel(Box.of(2),Box.of(Box.of(1)));
        var p4T = new Pixel(Box.of(3),Box.of(Box.of(7)));
        System.out.printf("p1: %s%n", p1.toIntegerString());
        System.out.printf("p2: %s%n", p2.toIntegerString());
        System.out.printf("p3: %s%n", p3.toIntegerString());
        System.out.printf("p4: %s%n", p4.toIntegerString());
        var M = new Maxel(p1,p2,p3, p4);
        var MtE = new Maxel(p1T,p2T,p3T,p4T);
        var Mt = M.transpose();
        System.out.printf("M: %s%n", M.toCompressedIntegerString());
        System.out.printf("Mt: %s%n", Mt.toCompressedIntegerString());
        assertEquals(MtE,Mt);
    }
    
    @Test
    public void testFunctions() {
        IO.println("\n\ntestFunctions");
        Box alpha = Box.of(Box.of(1));
        Box alphaSq = Box.of(Box.of(2));
        System.out.printf("\u03b1: %s%n", alpha.asPolyNumber());
        System.out.printf("\u03b1\u00b2: %s%n", alphaSq.asPolyNumber());
        var p1 = Pixel.of(0,3);
        var p2 = new Pixel(alpha,Box.of(1));
        var p3 = Pixel.of(3, 1);
        var p4 = new Pixel(Box.of(2),Box.of(6).add(alphaSq));
        var p5 = Pixel.of(4,5);
        var F = new Maxel(p1,p2,p3,p4,p5);
        System.out.printf("F: %s%n", F.toCompressedIntegerString());
        var G = new Maxel(Pixel.of(0,0), Pixel.of(3, 8), Pixel.of(1,8));        
        System.out.printf("G: %s%n", G.toCompressedIntegerString());
        var e = new Maxel(Pixel.of(0,8), new Pixel(alpha,Box.of(8)), Pixel.of(3,8));
        System.out.printf("e: %s%n", e.toCompressedIntegerString());
        var a = F.mul(G);
        System.out.printf("a: %s%n", a.toCompressedIntegerString());
        assertEquals(e,a);       
    }
    
}

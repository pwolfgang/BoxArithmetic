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
package mainPgms;

import java.io.PrintStream;
import com.pwolfgang.boxarithmetic.Box;

/**
 *
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class Main {
    
        static void printIt(String s, Box box) {
        System.out.printf("%s: %s%n", s, box.toString());
        System.out.printf("%s: %s%n", s, box.toIntegerString());
        System.out.printf("%s: %s%n", s, box.asPolyNumber());
    }

    
    public static void main(String... args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        polyNumber();
        multiNumber();
        polySumExample();
        polyMultExample();
        multiSumExample();
        multiMultExample();
        multiNumberVSpolyNumber();
        biPolyNumberExample();
    }
        
    static void polySumExample() {
        System.out.println("Example of sum of polynumbers");
        System.out.printf("%s%n", "\u03b1\u2081\u00b2");
        var a = Box.of(Box.of(3),Box.of(3),Box.of(4));
        var b = Box.of(0);
        var c = Box.of(1);
        var d = Box.of(Box.of(3),Box.of(7));
        var sum = Box.add(a, b, c, d);
        printIt("a", a);
        printIt("b", b);
        printIt("c", c);
        printIt("d", d);
        printIt("a+b+c+c", sum);        
    }
    
    static void polyNumber() {
        System.out.println("Examples of polynumbers");
        var p1 = Box.parse("[1 1 2]");
        var p2 = Box.parse("[13]");
        var p3 = Box.parse("[0 0]");
        var p4 = Box.parse("[ ]");
        printIt("p1", p1);
        printIt("p2", p2);
        printIt("p3", p3);
        printIt("p4", p4);
    }
    
    static void multiNumber() {
        System.out.println("Examples of multinumbers");
        var m1 = Box.parse("[[1 1][1 1]]");
        var m2 = Box.parse("[[3 5 11]]");
        var m3 = Box.parse("[0]");
        var m4 = Box.parse("[ ]");
        printIt("m1", m1);
        printIt("m2", m2);
        printIt("m3", m3);
        printIt("m4", m4);
        
    }
    
    static void polyMultExample(){
        System.out.println("Example of polynumber multiplication");
        var p1 = Box.parse("[2 3]");
        var p2 = Box.parse("[1 1 0]");
        var p = Box.mul(p1, p2);
        printIt("p1", p1);
        printIt("p2", p2);
        printIt("p1 × p1", p);
    }
    
    static void multiSumExample(){
        System.out.println("Example of multinumber addition ");
        var m1 = Box.parse("[[3][4]]");
        var m2 = Box.parse("[[1 1 2][4] 0]");
        var m3 = Box.parse("[4 [1 2 1]]");
        var s = Box.add(m1,m2,m3);
        printIt("m1", m1);
        printIt("m2", m2);
        printIt("m3", m3);
        printIt("m1 + m2 + m3", s);
    }
    
    static void multiMultExample(){
        System.out.println("Example of multinumber multiplication");
        var m1 = Box.parse("[[0 0 2][3 8]]");
        var m2 = Box.parse("[[1 1] 2 [9]]");
        printIt("m1", m1);
        printIt("m2", m2);
        printIt("m1 × m2", Box.mul(m1, m2));
    }
    
    static void multiNumberVSpolyNumber() {
        System.out.println("multiNumber vs polyNumber");
        var polyNumber = Box.parse("[0 0 0 1 2 2 2 2 5]");
        var multiNumber = Box.parse("[0 0 0 [1] [1 1][1 1][1 1][1 1][1 1 1 1 1]]");
        printIt("polyNumber", polyNumber);
        printIt("multiNumber", multiNumber);
    } 
    
    static void biPolyNumberExample() {
        System.out.println("bipolynumber example");
        var b = Box.parse("[0 0 [0 0] [0 0 0][0 0 0][0 0 0][0 0 0][1][1][1][0 1][0 1][0 1][0 1][0 1][0 0 1 1]]");
        printIt("b", b);
    }
    
}

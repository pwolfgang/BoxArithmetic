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
import java.io.UnsupportedEncodingException;
import com.pwolfgang.boxarithmetic.Box;

/**
 *
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class IntegerPolynumbers {

    static void printIt(String s, Box box) {
        String boxToString = box.toString();
        String boxToIntegerString = box.toIntegerString();
        String boxAsPolyNumber = box.asPolyNumber();
        System.out.printf("%s: %s%n", s, boxToString);
        System.out.printf("%s: %s%n", s, boxToIntegerString);
        System.out.printf("%s: %s%n", s, boxAsPolyNumber);
    }


    public static void main(String... args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        var p = Box.of(Box.of(0), Box.of(0), Box.of(1), Box.of(4), Box.of(4), Box.of(4));
        printIt("p", p);
        var q = Box.of(Box.of(-3), Box.of(-3), Box.of(-1), Box.of(0), Box.of(1),Box.of(1), Box.of(1));
        printIt("q", q);
        p = Box.of(Box.of(-2),Box.of(0),Box.of(1),Box.of(1));
        printIt("p", p);       
        var m = Box.parse("[0 1]");
        var mSq = m.mul(m);
        var minusM = m.mul(Box.of(-1));
        var minusMSq = mSq.mul(Box.of(-1));
        printIt("m", m);
        printIt("mSq", mSq);
        printIt("minusM", minusM);
        printIt("minusMSq", minusMSq);
        printIt("m + minusM", m.add(minusM));
        printIt("mSq + minusM", mSq.add(minusM));
        p = Box.parse("[0 0 2 2 2 5]");
        q = Box.parse("[0 1\u1D43 2\u1D43 3]");
        printIt("p", p);
        printIt("q", q);
        printIt("p + q", p.add(q));   
        p = Box.parse("[0 0 1]");
        q = Box.parse("[0 0 0 2\u1D43]");
        printIt("p", p);
        printIt("q", q);
        printIt("p × q", p.mul(q)); 
        p = Box.parse("[-2 -1 -1 -1 0 3 3]");
        printIt("p", p);
        
        p = Box.parse("[-3 -1 2\u1D43]");
        q = Box.parse("[-1\u1D43 0 2 3]");
        printIt("p", p);
        printIt("q", q);
        printIt("p + q", p.add(q));
        p = Box.parse("[-2 1]");
        q = Box.parse("[-1 -1 3\u1D43]");
        printIt("p", p);
        printIt("q", q);
        printIt("p × q", p.mul(q));
        
    }
    
}

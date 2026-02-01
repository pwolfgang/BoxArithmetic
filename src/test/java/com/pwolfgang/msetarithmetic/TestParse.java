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
package com.pwolfgang.msetarithmetic;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pwolfgang.boxarithmetic.Box;

public class TestParse {
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    void printIt(String s, Box mSet) {
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
        assertEquals(s, mSet.asPolyNumber());
    }

    void printIt_noAssert(String s, Box mSet) {
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
    }

    @Test
    public void testEmpty() {
        assertEquals(Box.of(0), Box.parse("[]"));
        assertEquals(Box.of(0), Box.parse("[  ]"));
        assertEquals(Box.of(0), Box.parse("  [ ] "));
    }
    
    @Test
    public void testSingle() {
        assertEquals(Box.of(Box.of(2)), Box.parse("[2]"));
    }

    @Test
    public void testIntList() {
        var p = Box.of(Box.of(2), Box.of(3), Box.of(-5));
        System.out.println(p.toIntegerString());
        var q =  Box.parse("[2 3 -5]");
        assertEquals(p,q);  
    }
    
    @Test
    public void testNested() {
        var p = Box.of(Box.of(2), Box.of(Box.of(3),Box.of(Box.of(3))));
        System.out.println(p.toIntegerString());
        System.out.println(p.asPolyNumber());
        var q = Box.parse("[2 [3 [3]]]");
        System.out.println(q.toIntegerString());
        System.out.println(q.asPolyNumber());
        assertEquals(p, q);
    }
    
}

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
package com.pwolfgang.boxarithmetic;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pwolfgang.boxarithmetic.Box;

public class BoxTest2 {
    
    public BoxTest2() {
        
    }
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }

    void printIt(String s, Box box) {
        System.out.printf("%s: %s%n", s, box.toString());
        System.out.printf("%s: %s%n", s, box.toIntegerString());
        System.out.printf("%s: %s%n", s, box.asPolyNumber());
        assertEquals(s, box.asPolyNumber());
    }
    
    @Test
    public void testPolyNumber() {
        Box p = Box.of(Box.of(0), Box.of(0), Box.of(0), Box.of(1), Box.of(3), Box.of(4), Box.of(4));
        printIt("3+α₀+α₀³+2α₀⁴", p);
    }
        
}

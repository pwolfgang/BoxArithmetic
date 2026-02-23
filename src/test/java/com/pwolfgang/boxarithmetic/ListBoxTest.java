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
    public void testListBox() {
        var listBox = new ListBox(Box.of(1), Box.of(2), Box.of(3));
        var expected = Box.parse("[[1][1 2][1 2 3]]");
        assertEquals (expected,listBox);
    }
    
    @Test void testPi() {
        var listBox = new ListBox(Box.of(1), Box.of(2), Box.of(3));
        assertEquals(Box.of(1), listBox.pi(1));
        assertEquals(Box.of(2), listBox.pi(2));
        assertEquals(Box.of(3), listBox.pi(3));
        
    }
    
}

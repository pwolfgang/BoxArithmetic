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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * A list is an ordered collection of Boxes. The list L is a set of the form
 * L = [[A1][A1 A2][A1 A2 A3] ... [A1 12 A3 ... An]] is the list
 *  A1, A2, A4, ... An. We will use the Java List to represent the list in
 * addition to the Box representation.
 * @author Paul
 */
public class ListBox  extends NonEmptyBox {
    
    final List<? extends Box> theList;
    
    public ListBox(Box... boxes) {
        theList = List.copyOf((Arrays.asList(boxes)));
        List<Box> backingList = new ArrayList<>();
        Box newBox = new EmptyBox();
        for (Box box : boxes) {
            newBox = newBox.add(Box.of(box));
            backingList.add(newBox);
        }
        super(backingList);     
    }
    
    public Box pi(int i) {
        return theList.get(i-1);
    }
    
    public String toListString() {
        var stj = new StringJoiner(", ", "\u2308", "\u2309");
        for (Box box : theList) {
            stj.add(box.toIntegerString());
        }
        return stj.toString();
    }

    
}

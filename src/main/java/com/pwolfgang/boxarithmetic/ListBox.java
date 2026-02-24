package com.pwolfgang.boxarithmetic;

import java.util.ArrayList;
import java.util.List;

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

/**
 *
 * @author Paul
 */
public class ListBox extends NonEmptyBox {
    
    public ListBox(Box... boxes) {
        this(List.of(boxes));
    }
    
    public ListBox(List<? extends Box> boxes) {
        List<Box> filtered = new ArrayList<>();
        for (Box box:boxes) {
            if (box instanceof BoxList list) {
                filtered.add(list);
            }
        }
        super(filtered);
    }
    
    @Override
    public ListBox clone() {
        return new ListBox(getContent());
    }
    
    public Box pi(int k) {
        List<Box> result = new ArrayList<>();
        for (Box box:getContent()) {
            if (box instanceof BoxList list) {
                Box b = list.pi(k);
                if (b != null) {
                    result.add(b);
                }
            }
        }
        return new NonEmptyBox(result);
    }
    
    
    
}

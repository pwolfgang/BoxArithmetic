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

import java.util.List;
import java.util.StringJoiner;

/**
 * A Singleton is a list containing a single box. The paper restricts singletons
 * to boxes of natural numbers, but the lecture removes this restriction.
 * @author Paul
 */
public class Singleton extends NonEmptyBox {
    
    public Singleton(Box box) {
        super(List.of(box));
    }
    
    public static Singleton of(int n) {
        return new Singleton(Box.of(n));
    }
    
    @Override
    public String toString() {
        var stj = new StringJoiner(" ", "\u2308", "\u2309");
        for (Box b : content.getFirst()) {
            stj.add(b.toString());
        }
        return stj.toString();  
    }
    
    @Override
    public String toIntegerString() {
        return "\u2308" + content.getFirst().toIntegerString() + "\u2309";
    }
    
   
    public String toRawString() {
        return super.toString();
    }
    
    public Singleton mul(Pixel p) {
        if (content.getFirst().equals(p.a)){
            return new Singleton(p.b);
        } else {
            return null;
        }
    }
    
}

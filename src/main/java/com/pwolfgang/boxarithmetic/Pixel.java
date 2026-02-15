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
import java.util.List;

/**
 * A Pixel is a list of two boxes. It has a special multiply operation.
 * If ⌈A,B⌉ and ⌈C,D⌉ are pixels, then ⌈A,B⌉ × ⌈C,D⌉ = ⌈A,D⌉ if and only if
 * B = C, otherwise nothing.
 * @author Paul
 */
public class Pixel extends NonEmptyBox {
    
    private final Box a;
    private final Box b;
    
    public static Pixel of(int a, int b) {
        return new Pixel(Box.of(a), Box.of(b));
    }
    
    public Pixel(Box a, Box b) {
        this.a = a;
        this.b = b;
        List<Box> list = new ArrayList<>();
        list.add(a);
        list.add(Box.of(a,b));
        super(list);
    }
    
    public Box mul(Box o) {
        if (o instanceof Pixel p) {
            if (b.equals(p.a)) {
                return new Pixel(a,p.b);
            } else {
                return null;
            }
        } else {
            return super.mul(o);
        }
    }
    
    public Pixel transpose() {
        return new Pixel(b,a);
    }
    
    @Override
    public String toIntegerString() {
        return "\u2308" + a.toIntegerString() + ", " + b.toIntegerString() + "\u2309";
    }
    
    @Override
    public Pixel clone() {
        return new Pixel(a,b);
    }
    
}

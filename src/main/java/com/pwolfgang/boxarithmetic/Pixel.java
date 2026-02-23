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
public class Pixel extends ListBox {
    
    final Box a;
    final Box b;
    
    public static Pixel of(int a, int b) {
        return new Pixel(Box.of(a), Box.of(b));
    }
    
    public Pixel(Box a, Box b) {
        this.a = a;
        this.b = b;
        super(a, b);
    }
    
    public Box mul(Box o) {
        return switch (o) {
                case Pixel p -> b.equals(p.a) ? new Pixel(a,p.b) : null;
                case Singleton s -> b.equals(s.a) ? new Singleton(a): null;
                default -> super.mul(o); 
       };
    }
    
    public Pixel transpose() {
        return new Pixel(b,a);
    }
       
    @Override
    public Pixel clone() {
        return new Pixel(a,b);
    }
    
}

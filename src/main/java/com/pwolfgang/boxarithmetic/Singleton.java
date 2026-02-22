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


/**
 * A Singleton is a list containing a single box. The paper restricts singletons
 * to boxes of natural numbers, but the lecture removes this restriction.
 * @author Paul
 */
public class Singleton extends NonEmptyBox {
    
    final Box a;
    
    public Singleton(Box box) {
        this.a = box;
        super(Box.of(box));
    }
    
    public static Singleton of(int n) {
        return new Singleton(Box.of(n));
    }
    
    public String toListString() {
        return "\u2308" + a.toString() + "\u2309";
    }
    
    @Override
    public Box mul(Box o) {
        return switch (o) {
            case Singleton s -> new Singleton(a.mul(s.a));
            case Pixel p -> a.equals(p.a) ? new Singleton(p.b) : null;
            default -> super.mul(o);       
        };      
    }
       
   public Singleton clone() {
        return new Singleton(a);
    }
    
}

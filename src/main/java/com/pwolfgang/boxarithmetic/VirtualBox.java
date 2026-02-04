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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Paul
 */
public class VirtualBox  implements Box{
    
    private final Box leftBox;
    private final Box rightBox;
    
    public VirtualBox(Box left, Box right) {
        leftBox = left;
        rightBox = right;
    }
    
    @Override
    public String toString() {
        return "(" + leftBox.toIntegerString() + "\u2296" + rightBox.toIntegerString() + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o instanceof VirtualBox other) {
            Box term1 = leftBox.add(other.rightBox);
            Box term2 = rightBox.add(other.leftBox);
            return term1.equals(term2);
        } else {
            return false;
        }
    }
    
    public VirtualBox add(VirtualBox other) {
        return new VirtualBox(leftBox.add(other.leftBox), rightBox.add(other.rightBox));   
    }
    
    public VirtualBox neg() {
        return new VirtualBox(rightBox, leftBox);
    }
    
    public VirtualBox sub(VirtualBox other) {
        return add(other.neg());
    }
    
    public VirtualBox mul(VirtualBox other) {
        var term1 = leftBox.mul(other.leftBox).add(rightBox.mul(other.rightBox));
        var term2 = leftBox.mul(other.rightBox).add(rightBox.mul(other.leftBox));
        return new VirtualBox(term1, term2);
    }

    @Override
    public int intSize() {
        return 2;
    }
    
    @Override
    public Box size() {
        return Box.of(2);
    }
    

    @Override
    public boolean isEmptyBox() {
        return false;
    }

    @Override
    public int getHeight() {
        return leftBox.getHeight();
    }

    @Override
    public String toIntegerString() {
        return toString();
    }

    @Override
    public Box clone() {
        return new VirtualBox(leftBox.clone(), rightBox.clone());
    }

    @Override
    public Iterator<Box> iterator() {
        return Collections.singleton((Box)this).iterator();
    }

    @Override
    public Box add(Box other) {
        return switch(other) {
            case VirtualBox vb -> add(vb);
            default -> add(new VirtualBox(other, new EmptyBox()));
        };
    }

    @Override
    public Box addEmptyBox(EmptyBox other) {
        return this;
    }

    @Override
    public Box addNonEmptyBox(NonEmptyBox other) {
        return add(new VirtualBox(other, new EmptyBox()));
    }

    @Override
    public Box mul(Box other) {
        return switch(other) {
            case VirtualBox vb -> mul(vb);
            default -> mul(new VirtualBox(other, new EmptyBox()));
        };
    }

    @Override
    public Box mulEmptyBox(EmptyBox other) {
        return new EmptyBox();
    }

    @Override
    public Box mulNonEmptyBox(NonEmptyBox other) {
        return mul(new VirtualBox(other, new EmptyBox()));
    }

    @Override
    public String asPolyNumber() {
        return "(" + leftBox.asPolyNumber() + ", " + rightBox.asPolyNumber() + ")";
    }

    @Override
    public List<Box> getContent() {
        return List.of(leftBox, rightBox);
    }

    @Override
    public Box tB(Box b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Box eval(Box A) {
        return new VirtualBox(leftBox.eval(A), rightBox.eval(A));
    }
    
}

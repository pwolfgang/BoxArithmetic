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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class models the empty mset.
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class EmptyBox implements Box {
    
    Box parent;
    
    /**
     * {@inheritDoc}
     * @return Always return 0
     */
    @Override
    public int size() {return 0;}
    
    /**
     * {@inheritDoc}
     * @return Always returns true.
     */
    @Override
    public boolean isEmptyBox() {
        return true;
    }
    
    /**
     * {@inheritDoc}
     * @return Always returns "[]"
     */
    @Override
    public String toString() {
        return "[ ]";
    }
       
    /**
     * {@inheritDoc}
     * @return Always returns "0".
     */
    @Override
    public String toIntegerString() {
        return "0";
    }
    
    /**
     * {@inheritDoc}
     * @return A new EmptyBox.
     */
    @Override
    public EmptyBox clone() {
        return new EmptyBox();
    }
       
    /**
     * {@inheritDoc}
     * @return An empty iterator.
     */
    @Override
    public Iterator<Box> iterator() {
        return Collections.emptyIterator();
    }
    
    /**
     * {@inheritDoc}
     * Invokes addEmptyBox on other.
     * @return The sum of this Box and other
     */
    @Override
    public Box add(Box other) {
        return other.addEmptyBox(this);
    }
    
    /**
     * {@inheritDoc}
The sum of two EmptyBoxs is and EmptyBox.
     * @return The sum of this Box and other
     */
    @Override
    public Box addEmptyBox(EmptyBox other) {
        return new EmptyBox();
    }
    
    
    /**
     * {@inheritDoc}
The sum of an EmptyBox a NonEmptyBox is a copy of the NonEmptyBox.
     * @return The sum of this Box and other
     */
    @Override
    public Box addNonEmptyBox(NonEmptyBox other) {
        return other.clone();
    }
    
    /**
     * {@inheritDoc}
     * Invokes mulEmptyBox on other.
     * @return The product of this Box and other
     */
    @Override
    public Box mul(Box other) {
        return other.mulEmptyBox(this);
    }
       
    /**
     * {@inheritDoc}
The product of a EmptyBox and an EmptyBox is an EmptyBox
     * @return The product of this Box and other
     */
    @Override
    public Box mulEmptyBox(EmptyBox other) {
        return new EmptyBox();
    }
        
    /**
     * {@inheritDoc}
     * @return True if other is either an EmptyBox.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        return (this.getClass() == o.getClass());
    }
    
    /**
     * {@inheritDoc}
     * @return "0"
     */
    @Override
    public String asPolyNumber() {
        return "0";
    }
    
    /**
     * {@inheritDoc}
     * @return An empty list
     */
    @Override
    public List<Box> getContent() {
        return Collections.emptyList();
    }

    @Override
    public Box mulNonEmptyBox(NonEmptyBox other) {
        return new EmptyBox();
    }
    
    @Override
    public int getHeight() {
        return 0;
    }
    
}

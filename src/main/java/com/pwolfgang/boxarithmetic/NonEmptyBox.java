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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * This class models a non-empty mset
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class NonEmptyBox implements Box {
    
    int height;
    
    SortedSet<Box> content;
    
    /**
     * {@inheritDoc}
     * @return Always returns false
    */
    @Override
    public boolean isEmptyBox() {
        return false;
    }
    
    /**
     * Construct a new NonEmptyMset from an array of boxs.
     * @param boxs The array of boxs
     */
    NonEmptyBox(Box... boxs) {
        this(Arrays.asList(boxs));
    }
    
    /**
     * Construct a new NonEmptyMset from a List of boxs.
     * @param boxs The List of boxs
     */
    NonEmptyBox(List<Box> boxs) {
        content = new TreeSet<>(Box::compareTo);
        int maxHeight = 0;
        for (Box m : boxs) {
            var box = m.clone();
            if (maxHeight < box.getHeight()) {
                maxHeight = box.getHeight();
            }
            content.add(box);
        }
        height = maxHeight + 1;
    }
    
    /**
     * Construct a non0empty box that represents the integer n.
     * This box will contain n Emptyboxs if n &gt; 0 or a VirtualBox
     * n &lt; 0
     * @throws IllegalArgumentException if n <= 0
     * @param n The integer to be represented.
     */
    NonEmptyBox(int n) {
        content = new TreeSet<>(Box::compareTo);
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                var box = new EmptyBox();
                content.add(box);
            }
        } else {
            throw new IllegalArgumentException();
        }
        height = 1;
    } 
    
    /**
     * Construct a new NonEmptyMset from a SortedSet of boxs.
     * @param boxs The SortedSet of boxs
     */
    NonEmptyBox(SortedSet<Box> c) {
        content = new TreeSet<>(Box::compareTo);
        int maxHeight = 0;
        for (Box Box : c) {
            Box newBox = Box.clone();
            if (maxHeight < newBox.getHeight()) {
                maxHeight = newBox.getHeight();
            }
            content.add(newBox);
        }
        height = maxHeight + 1;
    }
    
    /**
     * Make a deep copy of this Box.
     * @return a deep copy of this Box
     */
    @Override
    public NonEmptyBox clone() {
        List<Box> contentsList = new ArrayList<>();
        content.forEach(m -> contentsList.add(m.clone()));
        NonEmptyBox theClone = new NonEmptyBox(contentsList);
        return theClone;
    }
        
    /**
     * {@inheritDoc}
     * @return the number of elements in this mset
     */
    @Override
    public int size() {
        return content.size();
    }
    
    /**
     * {@inheritDoc}
     * @return A String consisting of nested [...]
     */
    @Override
    public String toString() {
        var stj = new StringJoiner(" ", "[", "]");
        content.forEach(m -> stj.add(m.toString()));
        return stj.toString();
    }
    
    /**
     * {@inheritDoc}
     * @return A String consisting of nested [...] with the innermost mset
     * replaced by an integer
     */
    @Override
    public String toIntegerString() {
        int countOfEmptySets = 0;
        var itr = content.iterator();
        StringJoiner sj = null;
        while (itr.hasNext()) {
            var c = itr.next();
            if (c.isEmptyBox()) {
                countOfEmptySets++;
            } else {
                sj = new StringJoiner(" ", "[", "]");
                if (countOfEmptySets != 0) {
                    for (int i = 0; i < countOfEmptySets; i++) {
                        sj.add("0");
                    }
                }
                sj.add(c.toIntegerString());
                finishLoop(itr, sj);         
            }      
        }
        if (sj == null) {
            var n = Integer.toString(countOfEmptySets);
            return n;
        } else {
            return sj.toString();
        }
    }
    
    private void finishLoop(Iterator<Box> itr, StringJoiner sj) {
        while (itr.hasNext()) {
            sj.add(itr.next().toIntegerString());
        }
    }
    
    /**
     * {@inheritDoc}
     * @return An iterator to the contents of this mset.
     */
    @Override
    public Iterator<Box> iterator() {
        return content.iterator();
    }
         
    /**
     * {@inheritDoc}
     * Invokes the addNonEmptyBox method on the other parameter
     * @param other The other mset
     * @return the sum of this and other
     */
    @Override
    public Box add(Box other) {
        return other.addNonEmptyBox(this);
    }
    
    /**
     * {@inheritDoc}
     * The sum of an non-empty mset and an empty mset is a copy of this
     * @param other The other mset
     * @return a copy of this
     */
    @Override
    public Box addEmptyBox(EmptyBox other) {
        return this.clone();
    }
       
    /**
     * {@inheritDoc}
     * The sum of two non-empty mset is an mset containing the contents of both
     * with any mset-anti-mset pairs removed.
     * @param other The other non-empty mset
     * @return The sum of this and other.
     */
    @Override
    public Box addNonEmptyBox(NonEmptyBox other) {
        List<Box> list = new LinkedList<>();
        list.addAll(this.content);
        list.addAll(other.getContent());
        Box result;
        if (!list.isEmpty()) {
            result = new NonEmptyBox(list);
        } else {
            result = new EmptyBox();
        }
        return result;
    }
    
    /**
     * {@interitDoc}
     *  Apply mulNonEmptyBox method on other
     * @param other
     * @return The product of this and other. 
     */
    @Override
    public Box mul(Box other) {
        return other.mulNonEmptyBox(this);
    }
    
    /**
     * {@inheritDoc}
     * The product of an empty mset and this is an empty mset.
     * @param other An empty mset
     * @return a new EmptyBox.
     */
    @Override
    public Box mulEmptyBox(EmptyBox other) {
        return new EmptyBox();
    }
       
    /**
     * {@inheritDoc}
     * The product of two non-empty msets is the pair-wise sum of their contents.
     * @param other A non-empty mset
     * @return the product of the two non-empty msets
     */
    @Override
    public Box mulNonEmptyBox(NonEmptyBox other) {
        List<Box> resultList = new ArrayList<>();
        for (var msetX : this) {
            for (var msetY : other) {
                resultList.add(msetX.add(msetY));
            }
        }
        Box result;
        if (!resultList.isEmpty()) {
            result = new NonEmptyBox(resultList);
        } else {
            result = new EmptyBox();
        }
        return result;        
    }
    

    /**
     * Determine of this non-empty mset and the other are equal. 
     * @param o The other non-empty mset
     * @return True if they are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            NonEmptyBox other = (NonEmptyBox)o;
            var thisContent = this.content;
            var otherContent = other.clone().content;
            if (thisContent.size() != otherContent.size()) {
                return false;
            }
            boolean found = false;
            for (var x : thisContent) {
                var itr2 = otherContent.iterator();
                found = false;
                while (!found && itr2.hasNext()) {
                    var y = itr2.next();
                    if (x.equals(y)) {
                        found = true;
                        itr2.remove();
                    }
                }
                if (!found) {
                    return false;
                }
            }
            return found;
        } else {
            return false;
        }
    }
    
    List<List<Box>> groupEquals() {
        List<List<Box>> result = new ArrayList<>();
        var c = new LinkedList<>(content);
        while (!c.isEmpty()) {
            List<Box> subList = new ArrayList<>();
            var itr = c.iterator();
            var m = itr.next();
            subList.add(m);
            itr.remove();
            while (itr.hasNext()) {
                var x = itr.next();
                if (m.equals(x)) {
                    subList.add(x);
                    itr.remove();
                }
            }
            result.add(subList);
        }        
        return result;
    }
    
    
    /**
     * {@inheritDoc }
     * @return A polynumber representation of this mset.
     */
    @Override
    public String asPolyNumber() {
        List<List<Box>> list = groupEquals();
        var stringJoiner = new StringJoiner("+");
        list.forEach(el ->{
            int count = el.size();
            Box first = el.get(0);
            var stringBuilder = new StringBuilder();
            if (first.getHeight() == 0) {
                stringBuilder.append(Integer.toString(count));
            } else {
                if (count > 1) {
                    stringBuilder.append(Integer.toString(count));
                }
                stringBuilder.append(genSupSub((NonEmptyBox)first));
            }
            stringJoiner.add(stringBuilder);
        });
        var result = stringJoiner.toString();
        return result;
    }
    
    String genSupSub(NonEmptyBox m) {
        var ll = m.groupEquals();
        var stb = new StringBuilder();
        ll.forEach(el ->{
            stb.append("\u03B1");
            stb.append(genSub(el.get(0).size()));
            var count = countSets(el);
            if (count < 0) {
                stb.append("\u207B");
                stb.append(genSup(-count));
            } else if (count > 1) {
                stb.append(genSup(count));
            }
        });
        return stb.toString();      
    }
    
    int countSets(List<Box> el) {
        return el.size();
    }

    
    String genSub(int n) {
        var stb = new StringBuilder();
        var s = Integer.toString(n);
        for (int i = 0; i < s.length(); i++) {
            var k = (s.charAt(i) - '0');
            stb.append(subScripts[k]);
        }
        return stb.toString();
    }

    String genSup(int n) {
        var stb = new StringBuilder();
        var s = Integer.toString(n);
        for (int i = 0; i < s.length(); i++) {
            var k = (s.charAt(i) - '0');
            stb.append(superScripts[k]);
        }
        return stb.toString();
    }
    
    /**
     * Return a copy of the content as a list.
     * @return The content as a list.
     */
    @Override
    public List<Box> getContent() {
        return new ArrayList<>(content);
    }
    
    @Override
    public int getHeight() {
        return height;
    }

}

/*
 * Copyright (C) 2026 Paul Wolfgang <paul@pwolfgang.com>
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
import java.util.Iterator;
import java.util.List;

/**
 * An Box is a data structure that contains an unordered collection of objects
 * with duplicates allowed. This specialized Box can only contain other Boxs.
 * This is based on N.J. Wildberger Math Foundations lectures beginning with
 * lecture 227 "Box Arithmetic A multiset approach."
 *
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */

public interface Box extends Comparable<Box>, Cloneable, Iterable<Box> {

    char[] subScripts = {'\u2080', '\u2081', '\u2082', '\u2083',
        '\u2084', '\u2085', '\u2086', '\u2087', '\u2088', '\u2089'};

    char[] superScripts = {'\u2070', '\u00B9', '\u00B2', '\u00B3', '\u2074',
        '\u2075', '\u2076', '\u2077', '\u2078', '\u2079'};

    /**
     * Return the size of this Box.
     * @return the intSize of this Box as an int,.
     */
    int intSize();
    
    /**
     * The size of a box is the box with each element replaced
     * bo the empty box.
     * @return the size of this box as a Box of empty boxes.
     */
    Box size();
    
    /**
     * The B truncation of A is the tox tB obtained by removing
     * all elements of A which are not equal to B.
     * @param b The other box.
     * @return the truncation of this box by b
     */
    Box tB(Box b);
    
    /**
     * Evaluate this box.
     */
    Box eval(Box A);
   
    default Box pow(Box n) {
        Box prod = Box.of(1);
        var itr = n.iterator();
        while (itr.hasNext()) {
            if (itr.next().isEmptyBox()) {
                prod = prod.mul(this);
            }
        }
        return prod;
    }

    /**
     * Indicate that this is an empty Box
     * @return True for empty Boxs
     */
    boolean isEmptyBox();
    
    int getHeight();

    /**
     * Create a String representation of this Box
     * @return a String representation of this Box
     */
    @Override
    public String toString();

    /**
     * Create an integer representation of this Box. The empty Box is zero.
     * An Box that contains only empty Boxs represents the integer which is
     * the count of the empty Boxs. Note that anti empty Boxs represent 
     * negative integers.
     * @return An integer representation of this Box.
     */
    public String toIntegerString();

    /**
     * Compare this Box to another Box. MSEts are ordered by size. 
     * To distinguish possible duplicates the hashCode is used.
     * Two Boxs are never considered equal by this method.
     * @param other The other Box
     * @return -1 if this Box is less than other and +1 if greater.
     */
    @Override
    default public int compareTo(Box other) {
        int compareSize = Integer.compare(this.intSize(), other.intSize());
        if (compareSize != 0) {
            return compareSize;
        } else {
            return Integer.compare(this.hashCode(), other.hashCode());
        }
    }
  
    
    /**
     * Construct an Box from an array of Boxes
     * @param boxes The list of Boxes
     * @return The resulting Box
     */
    public static Box of(Box... boxes) {
        if (boxes == null || boxes.length == 0) {
            return new EmptyBox();
        } else {
            return new NonEmptyBox(boxes);
        }
    }

    /**
     * Construct an Box from a list of Boxes
     * @param boxes The list of Boxs
     * @return The resulting Box
     */
    public static Box of(List<Box> boxes) {
        if (boxes == null || boxes.isEmpty()) {
            return new EmptyBox();
        } else {
            return new NonEmptyBox(boxes);
        }
    }

    /**
     * Construct an Box that represents an integer. The integer n is 
     * represented by an Box containing n empty Boxes. If n is negative
     * the resulting Box contains a VirtualBox
     * @param n
     * @return 
     */
    public static Box of(int n) {
        if (n == 0) {
            return new EmptyBox();
        } else if (n > 0) {
            return new NonEmptyBox(n);
        } else {
            return new VirtualBox(Box.of(0), new NonEmptyBox(-n));
        }
    }

   /**
    * Make a deep copy of this Box.
    * @return A deep copy of this Box.
    */
    Box clone();

    /**
     * Create an Iterator over the contents of this Box
     * @return an Iterator over the contents of this Box
     */
    @Override
    Iterator<Box> iterator();
    
    /** 
     * Return a new Box that is the sum of this Box and another Box.
     * If both Boxs are empty, then return the result of addOrMultEmptySets. 
     * If one of the Boxs is the anti empty Box, then return the anti of the 
     * other Box. Otherwise the result sum of two Boxs is an Box that 
     * contains the contents of the two Boxs. After the combination of the two 
     * Boxs any pairs of object and anti-object are removed.
     * @param other The other Box
     * @return The sum of this and other
     */
    Box add(Box other);
    
    /**
     * Return a new Box that is the sum of this Box an another EmptyBox.
     * @param other The other EmptyBox
     * @return The sum of this and other
     */
    Box addEmptyBox(EmptyBox other);
    /**
     * Return a new Box that is the sum of this Box an another AntiEmptySet.
     * @param other The other AntiEMptySet.
     * @return The sum of this and other
     */
    Box addNonEmptyBox(NonEmptyBox other);
    /**
     * Return a new Box that is the product of this Box an another Box.
     * The result is an Box of the pair-wise sums of the contents of the 
     * input Boxs. 
     * @param other The other Box.
     * @return this times other
     */
    Box mul(Box other);
    /**
     * Return a new Box that is the product of this Box an another EmptyBox.
     * @param other The other EmptyBox.
     * @return this times other
     */
    Box mulEmptyBox(EmptyBox other);
    /**
     * Return a new Box that is the product of this Box an another AntiEmptySet.
     * @param other The other AntiEmptySet.
     * @return this times other
     */
    Box mulNonEmptyBox(NonEmptyBox other);
    /**
     * Return the sum of two Boxs. 
     * @param x one Box
     * @param y the other Box
     * @return x + y
     */
    
    /** Truncation of a box is the result of 
    
    
    public static Box add(Box x, Box y) {
        return x.add(y);
    }

    /**
     * Compute the sum of an array of Boxs. The result is the combination
     * of the contents of the input Boxs. 
     * @param boxes An array of Boxs.
     * @return The sum of the Boxs.
     */
    public static Box add(Box... boxes) {
        switch (boxes.length) {
            case 0 -> {
                return new EmptyBox();
            }
            case 1 -> {
                return boxes[0];
            }
            default -> {
                var x = boxes[0];
                var y = boxes[1];
                var z = x.add(y);
                for (int i = 2; i < boxes.length; i++) {
                    z = z.add(boxes[i]);
                }
                return z;               
            }

        }
    }


    /**
     * Compute the product of two Boxs. The product is formed by adding
     * all possible pairs from the two Boxs.
     * @param x An Box
     * @param y The other Box
     * @return x × y
     */
    public static Box mul(Box x, Box y) {
        return x.mul(y);
    }

    /**
     * Compute the product of an array of Boxs. The product is formed
     * by taking the first two and forming their product. Each subsequent
     * Box is then multiplied by the result.
     * @param boxes A list of Boxs
     * @return The product of the Boxs.
     */
    public static Box mul(Box... boxes) {
        switch (boxes.length) {
            case 0 -> {
                return Box.of(0);
            }
            case 1 -> {
                return boxes[0];
            }
            default -> {
                var x = boxes[0];
                var y = boxes[1];
                var z = x.mul(y);
                for (int i = 2; i < boxes.length; i++) {
                    z = z.mul(boxes[i]);
                }
                return z;
            }
        }
    }

    /**
     * Create a polynumber representation of the Box.
     * @return A Polynumber representation of the Box.
     */
    String asPolyNumber();

    /**
     * The contents of this Box
     * @return The contents of the Box as a List
     */
    List<Box> getContent();
    
    /** Convert an integer String representation of an Box into an Box.
     * 
     * @param s The String to be parsed
     * @return Box equivalent
     */
    static Box parse(String s) {
        List<Box> result = new ArrayList<>();
        int k = 0;
        while (k < s.length() && s.charAt(k) != '[') {k++;}
        if (k == s.length()) return null;
        k = parse1(s, k+1, result);
        return Box.of(result);
    }
    
    /** *  Convert an integer String representation of an Box into an Box
        An Box is a list of Boxs enclosed within '[' and ']'.An integer 
   <i>n</i> represents <i>n</i> empty Boxs. If <i>n</i> is negative
     * @param s The String to be parsed
     * @param k The index one passed the opening '['
     * @param result The list containing the result to date.
     * @return The index of the closing ']'
     */
    private static int parse1(String s, int k, List<Box> result) {
            for (char c = s.charAt(k); c != ']'; c=s.charAt(++k)) {
                switch (c) {
                    case '[' -> {
                        List<Box> list = new ArrayList<>();
                        k = parse1(s, k+1, list);
                            result.add(Box.of(list));
                    }
                    case '-' -> {
                        var i = -parseInt(s, k+1);
                        c = s.charAt(++k);
                        while (Character.isDigit(c)) {
                            c = s.charAt(++k);
                        }
                        result.add(Box.of(i));
                        --k;
                    }
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                        int i = parseInt(s,k);
                        c = s.charAt(k);
                        while (Character.isDigit(c)) {
                            c = s.charAt(++k);
                        }
                        result.add(Box.of(i));
                        --k;
                    }
                    case ' ' -> {
                    }
                }
            }
        return k;
    }
    
    private static int parseInt(String s, int k) {
        int n = 0;
        for (char c = s.charAt(k); Character.isDigit(c); c=s.charAt(++k)) {
            n = n * 10;
            n = n + (c - '0');
        }
        return n;
    }
}

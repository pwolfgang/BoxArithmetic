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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringJoiner;

/**
 *
 * @author Paul
 */
public class DifferencePair {
    
    private final Box leftBox;
    private final Box rightBox;
    
    public DifferencePair(Box left, Box right) {
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
        if (o instanceof DifferencePair other) {
            Box term1 = leftBox.add(other.rightBox);
            Box term2 = rightBox.add(other.leftBox);
            return term1.equals(term2);
        } else {
            return false;
        }
    }
    
    public DifferencePair add(DifferencePair other) {
        return new DifferencePair(leftBox.add(other.leftBox), rightBox.add(other.rightBox));   
    }
    
    public DifferencePair neg() {
        return new DifferencePair(rightBox, leftBox);
    }
    
    public DifferencePair sub(DifferencePair other) {
        return add(other.neg());
    }
    
    public DifferencePair mul(DifferencePair other) {
        var term1 = leftBox.mul(other.leftBox).add(rightBox.mul(other.rightBox));
        var term2 = leftBox.mul(other.rightBox).add(rightBox.mul(other.leftBox));
        return new DifferencePair(term1, term2);
    }   

     public String toIntegerString() {
        return toString();
    }

    @Override
    public DifferencePair clone() {
        return new DifferencePair(leftBox.clone(), rightBox.clone());
    }

    public String asPolyNumber() {
        PriorityQueue<List<Box>> leftList;
        PriorityQueue<List<Box>> rightList;
        if (leftBox.isEmptyBox()) {
            leftList = new PriorityQueue<>();
        } else {
            leftList = groupEquals(leftBox);
        }
        if (rightBox.isEmptyBox()) {
            rightList = new PriorityQueue<>();
        } else {
            rightList = groupEquals(rightBox);
        }
        var result = new StringJoiner(" + ");
        while (!leftList.isEmpty() && !rightList.isEmpty()) {
            var leftTerm = leftList.peek();
            var rightTerm = rightList.peek();
            var leftPower = leftTerm.get(0).intSize();
            var rightPower = rightTerm.get(0).intSize();
            if (leftPower < rightPower) {
                result.add(genTerm(leftTerm));
                leftList.poll();
            } else if (rightPower < leftPower) {
                var term = genTerm(rightTerm);
                term.insert(0, '-');
                result.add(term);
                rightList.poll();            
            } else {
                int leftCoef = leftTerm.size();
                int rightCoef = rightTerm.size();
                if (leftCoef > rightCoef) {
                    for (int i = 0; i < rightCoef; i++) {
                        leftTerm.removeLast();
                    }
                    result.add(genTerm(leftTerm));  
                } else if (rightCoef > leftCoef) {
                    for (int i = 0; i < leftCoef; i++) {
                        rightTerm.removeLast();
                    }
                    var term = genTerm(leftTerm);
                    term.insert(0, '-');
                    result.add(term);
                }
                leftList.poll();
                rightList.poll();
            }
        }
        while (!leftList.isEmpty()) {
            var leftTerm = leftList.poll();
            result.add(genTerm(leftTerm));               
        }
        while (!rightList.isEmpty()) {
            var rightTerm = rightList.poll();
                var term = genTerm(rightTerm);
                term.insert(0, '-');
                result.add(term);                          
            }
        return result.toString();
    }
    
    PriorityQueue<List<Box>> groupEquals(Box box) {
        PriorityQueue<List<Box>> result = 
                new PriorityQueue<>(new CompareFirstBoxSize());
        List<Box> c = box.getContent();
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

    
    StringBuilder genTerm(List<Box> el) {
        int count = el.size();
        Box first = el.get(0);
        var stringBuilder = new StringBuilder();
        if (first.getHeight() == 0) {
            stringBuilder.append(Integer.toString(count));
        } else {
            if (count > 1) {
                stringBuilder.append(Integer.toString(count));
            }
            stringBuilder.append(NonEmptyBox.genSupSub((NonEmptyBox)first));
        }
        return stringBuilder;      
    }
    
    class CompareFirstBoxSize implements Comparator<List<Box>> {
        @Override
        public int compare (List<Box> left, List<Box> right) {
            var leftFirst = left.get(0);
            var rightFirst = right.get(0);
            return Integer.compare(leftFirst.intSize(), rightFirst.intSize());
        }
    }
}

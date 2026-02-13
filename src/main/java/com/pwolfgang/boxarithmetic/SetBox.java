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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * A SetBox is a box in which each element occurs with multiplicity of exactly 1.
 * @author Paul
 */
public class SetBox extends NonEmptyBox {
    
    public static SetBox support(Box other) {
        return new SetBox(other);
    }
    
    public static SetBox of(int k, int n) {
        List<Box> content = new ArrayList<>();
        for (int i = k; i <= n; i++) {
            content.add(Box.of(i));
        }
        return new SetBox(new NonEmptyBox(content));
    }
    
    public static SetBox of(int n) {
        return of(0,n);
    }
    
    public SetBox(Box source) {
        Set<Box> uniqueContent = new HashSet<>(source.getContent());
        super(new ArrayList<>(uniqueContent));       
    }
    
    /**
     * {@inheritDoc}
     * @return A String consisting of nested [...]
     */
    @Override
    public String toString() {
        var stj = new StringJoiner(", ", "{", "}");
        content.forEach(m -> stj.add(m.toString()));
        return stj.toString();
    }
    
    /**
     * {@inheritDoc}
     * @return A String consisting of nested [...] with the innermost box
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
                sj = new StringJoiner(", ", "{", "}");
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
}

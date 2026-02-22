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
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;

/**
 *
 * @author Paul
 */
public class Vexel extends NonEmptyBox {
    
    public Vexel() {
        super();
    }
    
    public Vexel(Singleton... singletons) {
        super(singletons);
    }
    
    public Vexel(List<Singleton> singletons) {
        super(singletons);
    }
    
    public Vexel mul(Maxel maxel) {
        List<Singleton> result = new ArrayList<>();
        for (Box x : getContent()) {
            if (x instanceof Singleton s) {
                for (Box y : maxel.getContent()) {
                    if (y instanceof Pixel m) {
                        var sxm = s.mul(m);
                        if (sxm != null) {
                            result.add((Singleton)sxm);
                        }
                    }
                }
            }         
        }
        return new Vexel(result);        
    }
    
    public static Vexel of(int... ints) {
        List<Singleton> result = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            int c = ints[i];
            if (c != 0) {
                for (int k = 0; k < c; k++) {
                    result.add(Singleton.of(i));
                }
            }
        }
        return new Vexel(result);
    }    
    
    public String toVectorString() {
        SortedMap<Integer, Integer> result = new TreeMap<>();
        List<List<Box>> grouped = groupEquals();
        for (var boxList:grouped) {
            int coef = boxList.size();
            var first = boxList.getFirst();
            int index = ((Singleton)first).a.intSize();
            result.put(index,coef);
        }
        int maxIndex = result.lastKey();
        int[] vector = new int[maxIndex+1];
        result.forEach((k,v) -> vector[k] = v);
        var stj = new StringJoiner(", ", "(", ")");
        for (int j = 0; j < vector.length; j++) {
            stj.add(Integer.toString(vector[j]));
        }
        return stj.toString();
    }
    
    public String toRawString() {
        return super.toString();
    }
            
}

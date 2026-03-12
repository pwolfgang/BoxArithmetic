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
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author Paul
 */
public class Maxel extends ListBox {
    
    public Maxel() {
        super();
    }
    
    public Maxel(Pixel... pixels) {
        super(pixels);
    }

    public Maxel(List<Pixel> pixels) {
        super(pixels);
    }
    
    @Override
    public Box mul(Box o) {
        if (o instanceof Maxel other) {
            List<Pixel> result = new ArrayList<>();
            for (var pixelX: content) {
                for (var pixelY:other.content) {
                    var XxY =((Pixel)pixelX).mul((Pixel)pixelY);
                    if (XxY != null) {
                        result.add((Pixel)XxY);
                    }
                }
            }
            return new Maxel(result);
        } else {
            return ((NonEmptyBox)this).mul(o);
        }
    }
    
    @Override
    public Box add(Box o) {
        if (o instanceof Maxel other) {
            List<Pixel> result = new ArrayList<>();
            for (var pixelX:content) {
                result.add((Pixel)pixelX);
            }
            for (var pixelY:other.content) {
                result.add((Pixel)pixelY);
            }
            return new Maxel(result);           
        } else {
            return ((NonEmptyBox)this).add(o);
        }  
    }
    
    public static Maxel of(int[][] matrix) {
        List<Pixel> pixels = new ArrayList<>();
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j<matrix[i].length; j++) {
                int count = matrix[i][j];
                if (count != 0) {
                    for (int k = 0; k < count; k++) {
                        pixels.add(Pixel.of(i,j));
                    }
                }
            }
        }
        return new Maxel(pixels);       
    }
    
        public Vexel mul(Vexel vexel) {
        List<Singleton> result = new ArrayList<>();
        for (Box x : getContent()) {
            if (x instanceof Pixel p) {
                for (Box y : vexel.getContent()) {
                    if (y instanceof Singleton s) {
                        var pxs = p.mul(s);
                        if (pxs != null) {
                            result.add((Singleton)pxs);
                        }
                    }
                }
            }         
        }
        return new Vexel(result);        
    }
        
    public Maxel transpose() {
        List<Pixel> result = new ArrayList<>();
        for (Box b : getContent()) {
            if (b instanceof Pixel p) {
                result.add(p.transpose());
            }
        }
        return new Maxel(result);        
    }
    
    public String asMatrix() {
        int maxRow = -1;
        int maxCol = -1;
        var countMap = buildCount(this);
        for (Map.Entry<?, Integer> entry : countMap.entrySet()) {            
            int value = entry.getValue();
            var k = entry.getKey();
            if (k instanceof Pixel p) {
                var row = p.a.intSize();
                var col = p.b.intSize();
                if (row > maxRow) {
                    maxRow = row;
                }
                if (col > maxCol) {
                    maxCol = col;
                }
            }
        }
        int[][] matrix = new int[maxRow+1][maxCol+1];
        int maxValue = -1;
        for (Map.Entry<?, Integer> entry : countMap.entrySet()) {            
            int value = entry.getValue();
            if (value > maxValue) {
                maxValue = value;
            }
            var k = entry.getKey();
            if (k instanceof Pixel p) {
                var row = p.a.intSize();
                var col = p.b.intSize();
                matrix[row][col] = value;
            }
        }
        int numDigits = (int)Math.ceil(Math.log10(maxValue));
        var format = "%" + String.format("%d", numDigits+1) + "d";
        var outerStj = new StringJoiner("\n");
        for (int row = 0; row < maxRow+1; row++) {
            var innerStj = new StringJoiner(" ");
            for (int col = 0; col < maxCol+1; col++) {
               innerStj.add(String.format(format,matrix[row][col]));
            }
            outerStj.add(innerStj.toString());
        }
        return outerStj.toString();
    }
    
 }

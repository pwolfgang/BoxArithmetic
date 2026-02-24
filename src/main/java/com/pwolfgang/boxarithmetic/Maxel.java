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
 *
 * @author Paul
 */
public class Maxel extends ListBox {
    
    public Maxel(Pixel... pixels) {
        super(pixels);
    }

    public Maxel(List<Pixel> pixels) {
        super(pixels);
    }
    
    public Maxel mul(Maxel other) {
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
    
 }

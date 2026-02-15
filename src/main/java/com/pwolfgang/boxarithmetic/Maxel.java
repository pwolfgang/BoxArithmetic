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
public class Maxel extends NonEmptyBox {
    
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
        
}

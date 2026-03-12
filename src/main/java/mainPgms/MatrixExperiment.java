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
package mainPgms;

import com.pwolfgang.boxarithmetic.*;

/**
 * This program demonstrates matrix multiplication where one matrix contains
 * negative integers. The positive values are stored in one maxel and the negative
 * ones in another. The matrix is then represented by a DifferencePair.
 * This example shows the multiplication of a matrix by its inverse. The result
 * is a DifferencePair where the non-diagonal values appear in both sides thus
 * resulting in a identity matrix which the two are subtracted.
 * @author Paul
 */
public class MatrixExperiment {
    
    public static void main(String... args) {
        Maxel m = Maxel.of(new int[][]
        {{ 1, 0, 0, 0, 0, 0},
         { 1, 1, 0, 0, 0, 0},
         { 1, 2, 1, 0, 0, 0},
         { 1, 3, 3, 1, 0, 0},
         { 1, 4, 6, 4, 1, 0},
         { 1, 5,10,10, 5, 1}                   
        });
   
        Maxel mIl = Maxel.of(new int[][]
        {{ 1, 0, 0, 0, 0, 0},
         { 0, 1, 0, 0, 0, 0},
         { 1, 0, 1, 0, 0, 0},
         { 0, 3, 0, 1, 0, 0},
         { 1, 0, 6, 0, 1, 0},
         { 0, 5, 0,10, 0, 1}                   
        });
        
        Maxel mIr = Maxel.of(new int[][]
        {{ 0, 0, 0, 0, 0, 0},
         { 1, 0, 0, 0, 0, 0},
         { 0, 2, 0, 0, 0, 0},
         { 1, 0, 3, 0, 0, 0},
         { 0, 4, 0, 4, 0, 0},
         { 1, 0,10, 0, 5, 0}                   
        });
        
        var MM = new DifferencePair(m,new Maxel());
        var MI = new DifferencePair(mIl, mIr);
        
        var P = MM.mul(MI);
        
        System.out.println("Matrix M");
        System.out.println(m.asMatrix());
        System.out.println("\n\nMatrix M inverse left");
        System.out.println(mIl.asMatrix());
        System.out.println("\n\nMatris M inverst right");
        System.out.println(mIr.asMatrix());
        System.out.println("\n\nM × MI left");
        System.out.println(((Maxel)P.getLeftBox()).asMatrix());
        System.out.println("\n\nM × MI right");
        System.out.println(((Maxel)P.getRightBox()).asMatrix());

    }
    
}

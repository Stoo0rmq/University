/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsociety;

import java.util.Comparator;

/**
 *
 * @author b
 */
public class criterioCola implements Comparator<celda> {
//  Negativo si el primero es menos prioritario
// Zero si son iguales
// Positivo si el primero es mas prioritario
// a es 5 y b es 3 , el mas prioritario es 3
    @Override
    public int compare(celda one, celda two) {                        
        return (int) (two.dEuclidea - one.dEuclidea);
    }
}

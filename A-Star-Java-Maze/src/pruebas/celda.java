/*
 * Definicion de celda
 */
package pruebas;

import static java.lang.Math.sqrt;

/**
 *
 * @author Borja
 */
public class celda {
    public int x;
    public int y;
    public int valor;
    public double dEuclidea;
    public int xanterior,yanterior;
    celda(int x,int y,int valor,int finalx,int finaly,int xanterior,int yanterior){
        this.x = x;
        this.y = y;

        int difx,dify;
        difx = finalx-x;
        dify = finaly-y;
        this.dEuclidea = sqrt((difx*difx)+(dify*dify));
        this.valor = valor;
        this.xanterior = xanterior;
        this.yanterior = yanterior;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

/**
 *
 * @author borja & balti
 */

public class AnilloTorus extends Shape3D{
    private ArrayList<Point3f> vertices ;
    private ArrayList<Integer> indices ;
    
    public AnilloTorus (int tipo, float radiointerior, float radioexterior, int resolucion, Appearance app){
        vertices = new ArrayList<Point3f>() ;
        indices = new ArrayList<Integer>() ;
        
        Transform3D te = new Transform3D() ;
        
        for (int i = 0 ; i < resolucion ; i++){
            Point3f pe = new Point3f(radioexterior, 0, 0) ;
            double ang = ((Math.PI*2.0f) / resolucion) * i ;
            te.rotY(ang) ;
            te.transform(pe) ;
            vertices.add(pe) ;
        }
        
        Transform3D ti = new Transform3D() ;
        
        for (int i = 0 ; i < resolucion ; i++){
            Point3f pi = new Point3f(radiointerior, 0, 0) ;
            double ang = ((Math.PI*2.0f) / resolucion) * i ;
            ti.rotY(ang) ;
            ti.transform(pi) ;
            vertices.add(pi) ;
        }
        
        int[] vectorindices = new int[resolucion*4] ;
        
        if (tipo == 0){
            for (int i = 0 ; i < resolucion ; i++){
                vectorindices[i*4] = i ;
                vectorindices[i*4+1] = (i+1) % resolucion ;
                vectorindices[i*4+3] = i+resolucion ;
                vectorindices[i*4+2] = vectorindices[i*4+3]+vectorindices[i*4+1]-vectorindices[i*4] ;
            }
        }
        
        else if (tipo == 1){
            for (int i = 0 ; i < resolucion ; i++){
                vectorindices[i*4] = (i+1) % resolucion ;
                vectorindices[i*4+1] = i ;
                vectorindices[i*4+2] = i+resolucion ;
                vectorindices[i*4+3] = vectorindices[i*4+2]+vectorindices[i*4]-vectorindices[i*4+1] ;
            }
        }
        
        Point3f[] vectorvertices = new Point3f[resolucion*2] ;
        Vector3f[] vectornormales = new Vector3f[resolucion*2] ;
        
        for (int i = 0 ; i < resolucion*2 ; i++){
            if (tipo == 0)
                vectornormales[i] = new Vector3f(0.0f, 1.0f, 0.0f) ;
            
            else if (tipo == 1)
                vectornormales[i] = new Vector3f(0.0f, -1.0f, 0.0f) ;
        }
        
        IndexedGeometryArray iga = new IndexedQuadArray(resolucion*2, GeometryArray.COORDINATES | GeometryArray.NORMALS, resolucion*4) ;
        iga.setCoordinates(0, vertices.toArray(vectorvertices)) ;
        iga.setNormals(0, vectornormales) ;
        iga.setCoordinateIndices(0, vectorindices) ;
        iga.setNormalIndices(0, vectorindices) ;
        
        TexCoordGeneration generadortexturas = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_2, new Vector4f(1/(2*radioexterior), 0.0f, 0.0f, 1/2), new Vector4f(0.0f, 0.0f, 1/(2*radioexterior), 1/2)) ; 
        app.setTexCoordGeneration(generadortexturas) ;
        
        this.setGeometry(iga) ;
        this.setAppearance(app) ;
    }
}
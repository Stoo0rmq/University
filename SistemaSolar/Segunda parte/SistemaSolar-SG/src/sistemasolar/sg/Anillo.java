/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

/**
 *
 * @author borja & balti
 */

public class Anillo extends BranchGroup {
    private float radiointerior ;
    private float radioexterior ;
    private int resolucion ;
    
    public Anillo(float radiointerior, float radioexterior, int resolucion, String textura){
        this.radiointerior = radiointerior ;
        this.radioexterior = radioexterior ;
        this.resolucion = resolucion ;
        
        Appearance app = new Appearance () ;
        Texture texture = new TextureLoader (textura, null).getTexture() ;
        
        app.setTexture (texture) ;
                
        TextureAttributes ta = new TextureAttributes() ;
        ta.setTextureMode(TextureAttributes.MODULATE) ;
        app.setTextureAttributes(ta) ;
                
        AnilloTorus anilloarriba = new AnilloTorus(0, radiointerior, radioexterior, resolucion, app) ;
        AnilloTorus anilloabajo = new AnilloTorus(1, radiointerior, radioexterior, resolucion, app) ;
        
        TransformGroup nodorotacion = new TransformGroup() ;

        nodorotacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE) ;
        
        Alpha timer = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 6000, 0, 0, 0, 0, 0) ;
        
        Transform3D transRotator = new Transform3D() ;
        
        RotationInterpolator interpolator = new RotationInterpolator (timer, nodorotacion, transRotator, 0.0f, (float) Math.PI * 2.0f) ;
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 10000000.0f)) ;
        interpolator.setEnable(true) ;
        
        nodorotacion.addChild(interpolator) ;
        
        nodorotacion.addChild(anilloarriba) ;
        nodorotacion.addChild(anilloabajo) ;
        this.addChild(nodorotacion) ;
    }
}

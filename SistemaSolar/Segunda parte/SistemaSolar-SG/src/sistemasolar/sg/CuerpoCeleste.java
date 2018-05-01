/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 *
 * @author borja & balti
 */

public abstract class CuerpoCeleste extends BranchGroup {
    protected long velocidadrotacion ;
    protected long velocidadtraslacion ;
    protected float diametro ;
    protected float radiodistancia ;
   
    protected boolean rotacion ;
    
    protected TransformGroup nodorotacion1, nodotraslacion, nodorotacion2 ;
    
    protected Alpha timertraslacion, timerrotacion ;
    
    public CuerpoCeleste(long vr, long vt, float d, float rdistancia, String textura){
        velocidadtraslacion = vt ;
        velocidadrotacion = vr ;
        diametro = d ;
        radiodistancia = rdistancia ;
        
        rotacion = true ;
        
        nodorotacion1 = new TransformGroup() ;
        nodorotacion2 = new TransformGroup() ;
        
        this.setCapability(Node.ENABLE_PICK_REPORTING) ;
        
        Appearance app = new Appearance () ;
        Texture texture = new TextureLoader (textura, null).getTexture() ;
        
        app.setTexture (texture) ;
        
        app.setMaterial(new Material(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (0.0f, 0.0f, 0.0f),
        new Color3f (1f, 1f, 1f), new Color3f (1f, 1f, 1f), 17.0f)) ; 
        
        TextureAttributes ta = new TextureAttributes() ;
        ta.setTextureMode(TextureAttributes.MODULATE) ;
        app.setTextureAttributes(ta) ;

        Primitive sphere = new Sphere (diametro/2, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, 100, app) ;
        nodorotacion2.addChild(sphere) ;
        
        crearTraslacion() ;
        crearDistanciaSol() ;
        crearRotacion() ;
    }
  
    private void crearTraslacion(){
        nodorotacion1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE) ;
        
        this.addChild(nodorotacion1) ;
        
        timertraslacion = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velocidadtraslacion, 0, 0, 0, 0, 0) ;
        
        Transform3D transRotator = new Transform3D() ;
        
        RotationInterpolator interpolator = new RotationInterpolator (timertraslacion, nodorotacion1, transRotator, 0.0f, (float) Math.PI * 2.0f) ;
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 10000000.0f)) ;
        interpolator.setEnable(true) ;
        
        nodorotacion1.addChild(interpolator) ;
    }
    
    protected abstract void crearDistanciaSol() ;
    
    private void crearRotacion(){
        nodorotacion2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE) ;
        
        nodotraslacion.addChild(nodorotacion2) ;
        
        timerrotacion = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velocidadrotacion, 0, 0, 0, 0, 0) ;
        
        Transform3D transRotator = new Transform3D() ;
        
        RotationInterpolator interpolator = new RotationInterpolator (timerrotacion, nodorotacion2, transRotator, 0.0f, (float) Math.PI * 2.0f) ;
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 10000000.0f)) ;
        interpolator.setEnable(true) ;
        
        nodorotacion2.addChild(interpolator) ;
    }
    
    protected void cambiarRotacion(){
        rotacion = !rotacion ;
        
        if (rotacion){
            timertraslacion.resume() ;
            timerrotacion.resume() ;
        }
        
        else{
            timertraslacion.pause() ;
            timerrotacion.pause() ;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author borja & balti
 */

public class Satelite extends CuerpoCeleste{
    
    private View viewPersp ;
    private Canvas3D canvas ;

    public Satelite(long vr, long vt, float diametro, float radiodistancia, String textura, Canvas3D canvas){
        super(vr, vt, diametro, radiodistancia, textura) ;
        
        this.canvas = canvas ;
        viewPersp = new View() ;
        
        if (canvas != null)
            ponerCamaraLuna() ;
    }
    
    @Override
    protected void crearDistanciaSol(){
        nodotraslacion = new TransformGroup() ;
        nodotraslacion.setCapability(TransformGroup.ALLOW_TRANSFORM_READ) ;
        nodotraslacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE) ;
        
        nodorotacion1.addChild(nodotraslacion) ;
        
        Transform3D trans = new Transform3D() ;
        
        trans.setTranslation(new Vector3f(radiodistancia, 0, 0)) ;
        
        nodotraslacion.setTransform(trans) ;

    }
 
    private void ponerCamaraLuna(){
        Transform3D trans = new Transform3D() ;
        trans.lookAt(new Point3d(8, 1.0, -1.0), new Point3d(0, 0, 0), new Vector3d(0, 1, 0)) ;
        trans.invert() ;
        
        TransformGroup tg = new TransformGroup(trans) ;
        
        ViewPlatform vplatform = new ViewPlatform() ;
        tg.addChild(vplatform) ;

        viewPersp.setPhysicalBody(new PhysicalBody()) ;
        viewPersp.setPhysicalEnvironment(new PhysicalEnvironment()) ;
        
        viewPersp.setProjectionPolicy(View.PERSPECTIVE_PROJECTION) ;
        viewPersp.setBackClipDistance(500.0) ;
        
        nodotraslacion.addChild(tg) ;

        viewPersp.attachViewPlatform(vplatform) ;     
    }
    
    public void activarCamaraLuna(){
        viewPersp.addCanvas3D(canvas) ;
    }
      
    public void quitarCamaraLuna(){
        viewPersp.removeCanvas3D(canvas) ;
    }
}

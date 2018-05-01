/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import com.sun.j3d.loaders.Scene;
import java.util.ArrayList;
import javax.vecmath.Point3d;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

/**
 *
 * @author borja & balti
 */

public class Nave extends BranchGroup{
    
    private ArrayList<Point3f> vectorpuntos ;
    private ArrayList<Quat4f> vectorrotaciones ;
    private TransformGroup movimiento ;
    
    private View viewPersp ;
    private Canvas3D canvas ;
    
    public Nave(Canvas3D canvas){       
        movimiento = new TransformGroup() ;
        movimiento.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE) ;
        this.addChild(movimiento) ;
        
        FijarPosOriginal() ;
        FijarTraslacion() ;
        
        Scene modelo = null ; 
        ObjectFile file = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE ) ;        
    
        try{
            modelo = file.load ("models/naveEspacial/naveEspacial.obj") ; 
        }
        
        catch (FileNotFoundException e) {
            System.err.println (e) ;
            System.exit(1) ;
        }
        
        catch (ParsingErrorException e) {
            System.err.println (e) ;
            System.exit(1) ;
        }
        
        catch (IncorrectFormatException e) {
            System.err.println (e) ;
            System.exit(1) ;
        }
        
        movimiento.addChild(modelo.getSceneGroup()) ;
        
        this.canvas = canvas ;
        viewPersp = new View() ;
        
        ponerCamaraNave() ;
    }
   
    private void FijarPosOriginal(){
        vectorpuntos = new ArrayList<Point3f>() ;
           
        vectorpuntos.add(new Point3f(-20f, 0f, -20f)) ;
        vectorpuntos.add(new Point3f(-20f, 0f, 20f)) ;
        vectorpuntos.add(new Point3f(-20f, 0f, 20f)) ;
        vectorpuntos.add(new Point3f(20f, 0f, 20f)) ;
        vectorpuntos.add(new Point3f(20f, 0f, 20f)) ;
        vectorpuntos.add(new Point3f(20f, 0f, -20f)) ;
        vectorpuntos.add(new Point3f(20f, 0f, -20f)) ;
        vectorpuntos.add(new Point3f(-20f, 0f, -20f)) ;
        vectorpuntos.add(new Point3f(-20f, 0f, -20f)) ;
    }
    
    private void FijarTraslacion(){
        Transform3D transRotator = new Transform3D() ;
        Alpha timer = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 10000, 0, 0, 0, 0, 0) ;
        float[] alphas = {0.0f, 0.20f, 0.25f, 0.45f, 0.50f, 0.70f, 0.75f, 0.95f, 1.0f} ;
        
        vectorrotaciones = new ArrayList<Quat4f>() ;
        
        for (int i = 0 ; i < 4 ; i++){
            float ang = (i*90) % 360 ;
            AxisAngle4f axis = new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(ang)) ;
            Quat4f quat = new Quat4f() ;
            quat.set(axis) ;
            vectorrotaciones.add(quat) ;
            vectorrotaciones.add(quat) ;
        }
        
        AxisAngle4f axis = new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0.0f)) ;
        Quat4f quat = new Quat4f() ;
        quat.set(axis) ;
        vectorrotaciones.add(quat) ;
        
        RotPosPathInterpolator interpolator = new RotPosPathInterpolator (timer, movimiento, transRotator, alphas, vectorrotaciones.toArray(new Quat4f[0]), vectorpuntos.toArray(new Point3f[0])) ;
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 100000.0f)) ;
        interpolator.setEnable(true) ;
        movimiento.addChild(interpolator) ;
    }
  
    private void ponerCamaraNave(){
        Transform3D transformPersp = new Transform3D() ;
        transformPersp.lookAt(new Point3d(0.0, 1.5, -2.5), new Point3d(0, 1.0, 0.0), new Vector3d(0, 1, 0)) ;
        transformPersp.invert() ;
        
        TransformGroup tgPersp = new TransformGroup(transformPersp) ;
        
        ViewPlatform vpPersp = new ViewPlatform() ;
        tgPersp.addChild(vpPersp) ;

        viewPersp.setPhysicalBody(new PhysicalBody()) ;
        viewPersp.setPhysicalEnvironment(new PhysicalEnvironment()) ;
        
        viewPersp.setProjectionPolicy(View.PERSPECTIVE_PROJECTION) ;
        viewPersp.setBackClipDistance(500.0) ;
        
        movimiento.addChild(tgPersp) ;

        viewPersp.attachViewPlatform(vpPersp) ;
    }
    
    public void activarCamaraNave(){
        viewPersp.addCanvas3D(canvas) ;
    }
    
    public void quitarCamaraNave(){
        viewPersp.removeCanvas3D(canvas) ;
    }
}
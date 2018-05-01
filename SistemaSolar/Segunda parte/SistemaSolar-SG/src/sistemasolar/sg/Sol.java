/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PointLight;
import javax.media.j3d.Texture;
import javax.vecmath.Point3d;

/**
 *
 * @author borja & balti
 */

public class Sol extends BranchGroup {
    private PointLight luz ;
    private float diametro ;
    
    public Sol(String textura, PointLight luz, float diametro){
        this.luz = luz ;
        this.diametro = diametro ;
        
        Appearance app = new Appearance () ;
        Texture texture = new TextureLoader (textura, null).getTexture() ;
        
        app.setTexture (texture) ;
        
        Primitive sphere = new Sphere (diametro/2, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, 100, app) ;
        this.addChild(sphere) ;
        
        BoundingSphere boundingsphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100000.0) ;
        
        luz.setInfluencingBounds(boundingsphere) ;
        luz.setEnable(true) ;
        
        this.addChild(luz) ;
    }
    
    public void addPlaneta(Planeta planeta){
        this.addChild(planeta) ;
    }
}

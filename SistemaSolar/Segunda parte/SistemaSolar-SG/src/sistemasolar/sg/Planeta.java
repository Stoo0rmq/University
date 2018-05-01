/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author borja & balti
 */

public class Planeta extends CuerpoCeleste{
    private BranchGroup rama ;
    
    public Planeta(long vr, long vt, float diametro, float radiodistancia, String textura){
        super(vr, vt, diametro, radiodistancia, textura) ;
    }
    
    @Override
    protected void crearDistanciaSol(){
        nodotraslacion = new TransformGroup() ;
        nodotraslacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE) ;
        
        nodorotacion1.addChild(nodotraslacion) ;
        
        Transform3D trans = new Transform3D() ;
        
        trans.setTranslation(new Vector3f(radiodistancia, 0, 0)) ;
        
        nodotraslacion.setTransform(trans) ;
        
        rama = new BranchGroup() ;
        
        nodotraslacion.addChild(rama) ;
    }
    
    public void addSatelite(Satelite satelite){
        rama.addChild(satelite) ;
    }
    
    public void addAnillo(Anillo anillo){
        rama.addChild(anillo) ;
    }
}

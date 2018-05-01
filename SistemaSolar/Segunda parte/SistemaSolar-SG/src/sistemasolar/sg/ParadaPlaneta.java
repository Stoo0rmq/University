/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.WakeupOnAWTEvent;

/**
 *
 * @author balti
 */

public class ParadaPlaneta extends Behavior{
    private WakeupOnAWTEvent condition ;
    private PickCanvas pickCanvas ;
    private Canvas3D canvas ;
    
    public ParadaPlaneta (Canvas3D aCanvas, BranchGroup bg){
        canvas = aCanvas ;
        pickCanvas = new PickCanvas(aCanvas, bg) ;
        condition = new WakeupOnAWTEvent (MouseEvent.MOUSE_CLICKED) ;
    }
    
    @Override
    public void initialize(){
        setEnable(true) ;
        wakeupOn(condition) ;
    }
    
    @Override
    public void processStimulus (Enumeration cond){
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement() ;
        AWTEvent[] e = c.getAWTEvent() ;
        MouseEvent mouse = (MouseEvent) e[0] ;
        pickCanvas.setShapeLocation(mouse) ;
        
        PickResult pr = pickCanvas.pickClosest() ;
        
        if (pr != null){
            BranchGroup branch = (BranchGroup) pr.getNode(PickResult.BRANCH_GROUP) ;
        
            if (branch instanceof CuerpoCeleste)
                ((CuerpoCeleste) branch).cambiarRotacion() ;
        }
        
        setEnable(true) ;
        wakeupOn(condition) ;
    }
}

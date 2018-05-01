/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar.sg;

import GUI.Control;
import GUI.Visualization;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PointLight;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author borja & balti
 */

public class SistemaSolarSG {
    private final Canvas3D canvasfijo, canvascambiante ;
    private final SimpleUniverse universe ;
    private final BranchGroup raiz ;
    private final BranchGroup scene ;
    private final BranchGroup fondo ;
    private final AmbientLight luzambiental ;
    private Nave nave ;
    private Satelite luna ;
    private View view ;
    
    public SistemaSolarSG() {
        raiz = new BranchGroup() ;
        
        canvasfijo = new Canvas3D (SimpleUniverse.getPreferredConfiguration()) ;
        
        createViewPlanta() ;
        Visualization visualizationfija = new Visualization("Sistema Solar: Vista en planta", 500, 500, 100, 100, canvasfijo) ;
        visualizationfija.setVisible(true) ;
        
        canvascambiante = new Canvas3D (SimpleUniverse.getPreferredConfiguration()) ;
        universe = new SimpleUniverse(canvascambiante) ;

        createViewPerspectiva() ;
        Visualization visualizationcambiante = new Visualization("S. Solar: Vista cambiante", 500, 500, 750, 100, canvascambiante) ;
       visualizationcambiante.setVisible(true) ;
        
        fondo = createBackground() ;
        raiz.addChild(fondo) ;
    
        luzambiental = createAmbientLight() ;
        raiz.addChild(luzambiental) ;
    
        scene = createScene() ;
        raiz.addChild(scene) ;
        
        ParadaPlaneta paradaplaneta = createPick() ;
        raiz.addChild(paradaplaneta) ;
        
        raiz.compile() ;
        universe.addBranchGraph(raiz) ;
        
        
    }

    private void createViewPlanta(){
        Transform3D transformPlanta = new Transform3D() ;
        transformPlanta.lookAt (new Point3d (0, 700, 0), new Point3d (0, 0, 0), new Vector3d (1, 0, 0)) ;
        transformPlanta.invert() ;
        
        TransformGroup tgPlanta = new TransformGroup(transformPlanta) ;
    
        ViewPlatform vpPlanta = new ViewPlatform() ;
        tgPlanta.addChild(vpPlanta) ;
        
        View viewPlanta = new View() ;
        viewPlanta.setPhysicalBody(new PhysicalBody()) ;
        viewPlanta.setPhysicalEnvironment(new PhysicalEnvironment()) ;

        viewPlanta.setFieldOfView(Math.toRadians(45));
        viewPlanta.setBackClipDistance(5000.0) ;
        
        viewPlanta.addCanvas3D(canvasfijo) ;
        viewPlanta.attachViewPlatform(vpPlanta) ;
        
        raiz.addChild(tgPlanta) ;
    }

    private void createViewPerspectiva(){
        ViewingPlatform viewingPlatform = universe.getViewingPlatform() ;
        viewingPlatform.getViewPlatform().setActivationRadius(100f) ;
    
        Transform3D viewTransform3D = new Transform3D() ;
        viewTransform3D.lookAt (new Point3d (100, 100, 100), new Point3d (0, 0, 0), new Vector3d (0, 1, 0)) ;
        viewTransform3D.invert() ;

        TransformGroup tgPersp = viewingPlatform.getViewPlatformTransform() ;
        tgPersp.setTransform(viewTransform3D) ;
        
        OrbitBehavior orbit = new OrbitBehavior(canvascambiante, OrbitBehavior.REVERSE_ALL) ;
        orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 10000.0f)) ;
        orbit.setZoomFactor(2.0f) ;
        viewingPlatform.setViewPlatformBehavior(orbit) ;
    
        view = universe.getViewer().getView() ;
        view.setFieldOfView(Math.toRadians(45)) ;
        view.setBackClipDistance(1000.0) ;
    }

    public void selectViewPerspectiva(){
        view.removeCanvas3D(canvascambiante) ;
        nave.quitarCamaraNave() ;
        luna.quitarCamaraLuna() ;
        view.addCanvas3D(canvascambiante) ;
    }
    
    public void selectViewNave(){
        view.removeCanvas3D(canvascambiante) ;
        nave.quitarCamaraNave() ;
        luna.quitarCamaraLuna() ;
        nave.activarCamaraNave() ;
    }
    
    public void selectViewLuna(){
        view.removeCanvas3D(canvascambiante) ; 
        luna.quitarCamaraLuna() ;
        nave.quitarCamaraNave() ; 
        luna.activarCamaraLuna() ;
    }

    private BranchGroup createScene() {
        PointLight luzsolar = new PointLight(new Color3f(0.9f, 0.9f, 0.9f), new Point3f(0.0f, 0.0f, 0.0f), new Point3f(1.0f, 0.0f, 0.0f)) ;

        Sol sol = new Sol("imgs/Sol.jpg", luzsolar, 30) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta mercurio = new Planeta(50000, 10000, 3, 30, "imgs/Mercurio.jpg") ;

        sol.addPlaneta(mercurio) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta venus = new Planeta(150000, 13000, 8, 55, "imgs/Venus.jpg") ;

        sol.addPlaneta(venus) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta tierra = new Planeta(15000, 15000, 9, 90, "imgs/Tierra.jpg") ;

        luna = new Satelite(5000, 5000, 2, 10, "imgs/deathstar.jpg", canvascambiante) ;
        tierra.addSatelite(luna) ;

        sol.addPlaneta(tierra) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta marte = new Planeta(15000, 17000, 5, 130, "imgs/Marte.jpg") ;

        Satelite fobos = new Satelite(5000, 5000, 2, 7, "imgs/Fobos.jpg", null) ;
        marte.addSatelite(fobos) ;

        Satelite deimos = new Satelite(5500, 5500, 1, 13, "imgs/Deimos.jpg", null) ;
        marte.addSatelite(deimos) ;

        sol.addPlaneta(marte) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta jupiter = new Planeta(10000, 20000, 20, 200, "imgs/Jupiter.jpg") ;

        Satelite io = new Satelite(5000, 5000, 2, 15, "imgs/Io.jpg", null) ;
        jupiter.addSatelite(io) ;

        Satelite europa = new Satelite(5500, 5500, 1, 20, "imgs/Europa.jpg", null) ;
        jupiter.addSatelite(europa) ;

        Satelite calisto = new Satelite(6000, 6000, 2, 25, "imgs/Calisto.jpg", null) ;
        jupiter.addSatelite(calisto) ;

        sol.addPlaneta(jupiter) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta saturno = new Planeta(11000, 23000, 18, 300, "imgs/Saturno.jpg") ;

        Anillo anillo1 = new Anillo(14, 18, 100, "imgs/Anillo.png") ;
        Anillo anillo2 = new Anillo(20, 22, 100, "imgs/Anillo.png") ;
        Anillo anillo3 = new Anillo(24, 28, 100, "imgs/Anillo.png") ;

        saturno.addAnillo(anillo1) ;
        saturno.addAnillo(anillo2) ;
        saturno.addAnillo(anillo3) ;
        sol.addPlaneta(saturno) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta urano = new Planeta(12000, 25000, 14, 400, "imgs/Urano.jpg") ;

        Satelite miranda = new Satelite(5000, 5000, 1, 12, "imgs/Miranda.jpg", null) ;
        urano.addSatelite(miranda) ;

        Satelite ariel = new Satelite(5500, 5500, 2, 18, "imgs/Ariel.jpg", null) ;
        urano.addSatelite(ariel) ;

        Satelite titania = new Satelite(6000, 6000, 2, 25, "imgs/Titania.jpg", null) ;
        urano.addSatelite(titania) ;

        sol.addPlaneta(urano) ;

        ////////////////////////////////////////////////////////////////////////////

        Planeta neptuno = new Planeta(13000, 27000, 13, 500, "imgs/Neptuno.jpg") ;

        Satelite triton = new Satelite(6000, 6000, 2, 11, "imgs/Triton.png", null) ;
        neptuno.addSatelite(triton) ;

        sol.addPlaneta(neptuno) ;

        ////////////////////////////////////////////////////////////////////////////

        BranchGroup root = new BranchGroup() ;

        nave = new Nave(canvascambiante);
        root.addChild(nave);

        root.addChild(sol) ;

        return root ;
    }

    private BranchGroup createBackground(){
        Background backgroundNode = new Background ();
        backgroundNode.setApplicationBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 1000.0));

        Appearance app = new Appearance ();
        Texture texture = new TextureLoader ("imgs/universe_background.png", null).getTexture();
        app.setTexture (texture);

        Primitive sphere = new Sphere (1.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD, app);

        BranchGroup bgSphere = new BranchGroup();
        bgSphere.addChild(sphere);
        backgroundNode.setGeometry(bgSphere);

        BranchGroup bgBackground = new BranchGroup();
        bgBackground.addChild(backgroundNode);

        return bgBackground;    
    }

    private AmbientLight createAmbientLight(){
        AmbientLight ambientlight = new AmbientLight(new Color3f(0.7f, 0.7f, 0.7f)) ;
        ambientlight.setInfluencingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100000.0)) ;
        ambientlight.setEnable(true) ;

        return ambientlight ;
    }
    
    private ParadaPlaneta createPick(){
        ParadaPlaneta paradaplaneta = new ParadaPlaneta(canvasfijo, raiz) ;
        paradaplaneta.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100000.0)) ;
        
        return paradaplaneta ;
    }
    

    public SimpleUniverse getUniverse(){
        return universe ;
    }

    public static void main(String[] args) {        
         Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration()) ;

        canvas.setSize(800, 600) ;
        SistemaSolarSG universe = new SistemaSolarSG () ;
        //Creamos y añadimos el panel de control
        Control controlWindow = new Control (universe) ;
        controlWindow.setVisible(true) ;
       
    }
}
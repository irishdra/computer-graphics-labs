package com.company;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Background;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import javax.swing.JFrame;

public class Main extends JFrame {
    private static SimpleUniverse universe;
    private static BranchGroup root;
    private static TransformGroup horse;
    private static Canvas3D canvas;
    private static int TextureFlags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

    public static void main(String[] args) {
        try {
            Main window = new Main();
            Animation action = new Animation(horse);
            canvas.addKeyListener(action);
            window.setVisible(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Main() throws IOException {
        configurations();

        root = new BranchGroup();

        addBackground();
        addLight();

        horse = getHorseGroup();

        root.addChild(horse);
        root.compile();
        universe.addBranchGraph(root);
    }

    private void configurations() throws IOException {
        //configure window
        setTitle("lab5");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //configure canvas
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas, BorderLayout.CENTER);

        //configure universe
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addBackground() {
        TextureLoader tl = new TextureLoader("C:\\Users\\rocke\\IdeaProjects\\lab5\\assets\\back.jpg", canvas);
        Background back = new Background(tl.getImage());
        back.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bs = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        back.setApplicationBounds(bs);
        root.addChild(back);
    }

    private void addLight() {
        //directional light
        BoundingSphere bs = new BoundingSphere();
        bs.setRadius(100);
        DirectionalLight dl = new DirectionalLight(new Color3f(1, 1, 1), new Vector3f(-1, -1, -1));
        dl.setInfluencingBounds(bs);
        root.addChild(dl);

        //ambient light
        AmbientLight al = new AmbientLight(new Color3f(1, 1, 1));
        al.setInfluencingBounds(new BoundingSphere());
        root.addChild(al);
    }

    private void addTexture(Shape3D shape, String path) {
        TextureLoader tl = new TextureLoader(path, "RGP", new Container());

        Texture t = tl.getTexture();
        t.setBoundaryModeS(Texture.WRAP);
        t.setBoundaryModeT(Texture.WRAP);
        t.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));

        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);

        Appearance a = new Appearance();
        a.setTexture(t);
        a.setTextureAttributes(ta);

        shape.setAppearance(a);
    }

    private TransformGroup getHorseGroup() throws IOException {
        ObjectFile of = new ObjectFile(ObjectFile.RESIZE);
        of.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        Scene s = of.load(new FileReader("C:\\Users\\rocke\\IdeaProjects\\lab5\\assets\\horse.obj"));

        Map<String, Shape3D> map = s.getNamedObjects();
        Shape3D s3d = map.get("horse");
        s.getSceneGroup().removeChild(s3d);

        addTexture(s3d, "C:\\Users\\rocke\\IdeaProjects\\lab5\\assets\\texture.jpg");

        Transform3D t3D = new Transform3D();
        t3D.setScale(new Vector3d(0.4, 0.4, 0.4));
        Transform3D rY = new Transform3D();
        rY.rotY(150);
        t3D.mul(rY);

        TransformGroup tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.addChild(s3d);

        tg.setTransform(t3D);
        return tg;
    }
}
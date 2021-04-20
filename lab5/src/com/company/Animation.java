package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class Animation implements ActionListener, KeyListener {
    private Transform3D t3d = new Transform3D();
    private TransformGroup horse;

    private boolean w = false;
    private boolean a = false;
    private boolean s = false;
    private boolean d = false;
    private boolean e = false;
    private boolean q = false;

    private float x = 0;
    private float y = 0;

    Animation(TransformGroup horse) {
        this.horse = horse;
        this.horse.getTransform(this.t3d);

        Timer t = new Timer(40, this);
        t.start();
    }

    private void Action() {
        if (w) {
            y += 0.03f;
            if (y > 0.4f) {
                y = 0.4f;
            }
        }
        if (s) {
            y -= 0.03f;
            if (y < -0.4f) {
                y = -0.4f;
            }
        }
        if (a) {
            x -= 0.03f;
            if (x < -0.7f) {
                x = -0.7f;
            }
        }
        if (d) {
            x += 0.03f;
            if (x > 0.8f) {
                x = 0.8f;
            }
        }

        t3d.setTranslation(new Vector3f(x, y, 0));

        if (e) {
            Transform3D rotation = new Transform3D();
            rotation.rotY(0.05f);
            t3d.mul(rotation);
        }
        if (q) {
            Transform3D rotation = new Transform3D();
            rotation.rotY(-0.05f);
            t3d.mul(rotation);
        }

        horse.setTransform(t3d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Action();
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w':
                w = true;
                break;
            case 's':
                s = true;
                break;
            case 'a':
                a = true;
                break;
            case 'd':
                d = true;
                break;
            case 'e':
                e = true;
                break;
            case 'q':
                q = true;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w':
                w = false;
                break;
            case 's':
                s = false;
                break;
            case 'a':
                a = false;
                break;
            case 'd':
                d = false;
                break;
            case 'e':
                e = false;
                break;
            case 'q':
                q = false;
                break;
        }
    }
}
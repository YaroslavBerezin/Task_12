package ru.sc.vsu.berezin_y_a;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class Draw {

    public static void paintRectangle(Graphics2D g2d,int windth, int height,int levelCount,boolean flag){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,windth,height);
        g2d.setColor(color);
        if (flag) {
            paintRectangleLevelUp(g2d, windth / 2, height / 2, windth / 2, levelCount);
        }
        else{
            paintRectangleLevelDown(g2d, windth / 2, height / 2, windth / 2, levelCount);
        }
    }
    private static void paintRectangleLevelUp(Graphics2D g2d, double x, double y, double a,int level){
        if (level>0){
            paintRectangleLevelUp(g2d,x+a/2,y+a/2,a/2.25,level-1);
            paintRectangleLevelUp(g2d,x-a/2,y+a/2,a/2.25,level-1);
            paintRectangleLevelUp(g2d,x+a/2,y-a/2,a/2.25,level-1);
            paintRectangleLevelUp(g2d,x-a/2,y-a/2,a/2.25,level-1);
            g2d.setColor(Color.white);
            g2d.fill(new Rectangle2D.Double((x-a/2)+1, (y-a/2)+1, a-2, a-2));
            g2d.setColor(Color.black);
            g2d.draw(new Rectangle2D.Double(x-a/2, y-a/2, a, a) );
        }

    }
    private static void paintRectangleLevelDown(Graphics2D g2d, double x, double y, double a,int level){
        if (level>0){
            g2d.setColor(Color.white);
            g2d.fill(new Rectangle2D.Double((x-a/2)+1, (y-a/2)+1, a-2, a-2));
            g2d.setColor(Color.black);
            g2d.draw(new Rectangle2D.Double(x-a/2, y-a/2, a, a) );
            paintRectangleLevelDown(g2d,x+a/2,y+a/2,a/2.25,level-1);
            paintRectangleLevelDown(g2d,x-a/2,y+a/2,a/2.25,level-1);
            paintRectangleLevelDown(g2d,x+a/2,y-a/2,a/2.25,level-1);
            paintRectangleLevelDown(g2d,x-a/2,y-a/2,a/2.25,level-1);

        }

    }
    public static void writeImageToFile(String fileName, BufferedImage img)
    {
        try{
            File f = new File(fileName);
            ImageIO.write(img, "PNG", f);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}


package maxiso;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import maxiso.datalayer.merch.Plushie;


public class PlushieTest {
    
    private static final double DELTA = 0.0001;

   @Test
    public void testPlushie()
    {
        String type = "brand 1";
        String expecting="ANG123,brands,13.0,brand 1,blue,4,wool,cotton,false";
        Plushie p = new Plushie("ANG123","brands",13,type,"blue",4,"wool","cotton",false);
        String result= p.getQRCode()+","+p.getCategories()+","+p.getRetailPrice()+","+p.getCollection()+","+ p.getColor()+","+p.getSize()+","+p.getMaterial()+","+p.getStuffing()+","+p.getWashable();
        assertEquals(expecting,result);
    }

    @Test
    public void testColor()
    {
        String type = "brand 1";
        String expecting="blue";
        Plushie p = new Plushie("ANG123","brands",13,type,"blue",4,"wool","cotton",false);
        String result=  p.getColor();
        assertEquals(expecting,result);
    }
    
    @Test
    public void testSize()
    {
        String type = "brand 1";
        int expecting=4;
        Plushie p = new Plushie("ANG123","brands",13,type,"blue",4,"wool","cotton",false);
        int result= p.getSize();
        assertEquals(expecting,result);
    }
    
    @Test
    public void testMaterial()
    {
        String type = "brand 1";
        String expecting="wool";
        Plushie p = new Plushie("ANG123","brands",13,type,"blue",4,"wool","cotton",false);
        String result= p.getMaterial();
        assertEquals(expecting,result);
    }
    
    @Test
    public void testStuffing()
    {
        String type = "brand 1";
        String expecting="cotton";
        Plushie p = new Plushie("ANG123","brands",13,type,"blue",4,"wool","cotton",false);
        String result= p.getStuffing();
        assertEquals(expecting,result);
    }

    public void testWashable()
    {
        String type = "brand 1";
        boolean expecting=false;
        Plushie p = new Plushie("ANG123","brands",13,type,"blue",4,"wool","cotton",false);
        boolean result= p.getWashable();
        assertEquals(expecting,result);
    }
}

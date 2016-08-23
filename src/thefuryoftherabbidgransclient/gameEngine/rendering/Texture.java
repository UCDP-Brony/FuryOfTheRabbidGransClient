/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import thefuryoftherabbidgransclient.gameEngine.core.Util;
import thefuryoftherabbidgransclient.gameEngine.rendering.resourceManagement.TextureResource;

/**
 *
 * @author TheDoctor
 */
public class Texture {
    private static HashMap<String, TextureResource> loadedTextures = new HashMap<>();
    private TextureResource resource;
    private String fileName;
    
    public Texture(String fileName){
        this.fileName = fileName;
        TextureResource oldResource = loadedTextures.get(fileName);
        
        if(oldResource != null){
            resource = oldResource;
            resource.addReference();
        }
        else{            
            resource = loadTexture(fileName);
            loadedTextures.put(fileName, resource);
        }
    }
    
    public void bind(){
        bind(0);
    }
    
    public void bind(int samplerSlot){
        assert(samplerSlot >= 0 && samplerSlot <= 31);
        glActiveTexture(GL_TEXTURE0 + samplerSlot);
        glBindTexture(GL_TEXTURE_2D, resource.getId());
    }
    
    public int getID(){
        return resource.getId();
    }
    
    private static TextureResource loadTexture(String fileName){        
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length -1];
        
        try{            
            BufferedImage image = ImageIO.read(new File("./res/textures/"+fileName));
            int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
            
            ByteBuffer buffer = Util.getInstance().createByteBuffer(image.getHeight() * image.getWidth() * 4); 
            boolean hasAlpha = image.getColorModel().hasAlpha();
            
            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    int pixel = pixels[y * image.getWidth() + x];
                    
                    buffer.put((byte)(pixel >> 16 & 0xFF));
                    buffer.put((byte)(pixel >> 8 & 0xFF));
                    buffer.put((byte)(pixel & 0xFF));
                    if(hasAlpha){
                        buffer.put((byte)(pixel >> 24 & 0xFF));
                    } else {
                        buffer.put((byte)(0xFF));
                    }
                }
            }
            buffer.flip();
            TextureResource res = new TextureResource();
            glBindTexture(GL_TEXTURE_2D, res.getId());
            
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
            
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            
            return res;
        } catch (IOException ex) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
     
    @Override
    protected void finalize(){
        if(resource.removeReference()&&!fileName.isEmpty()){
            loadedTextures.remove(fileName);
        }
    }
}

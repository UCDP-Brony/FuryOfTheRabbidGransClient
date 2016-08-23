/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering.meshLoading;

import java.util.ArrayList;
import thefuryoftherabbidgransclient.gameEngine.core.Vector2f;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.Vertex;

/**
 *
 * @author TheDoctor
 */
public class IndexedModel {
    private ArrayList<Vector3f> positions;
    private ArrayList<Vector2f> texCoords;
    private ArrayList<Vector3f> normals;
    private ArrayList<Integer> indices;

    public IndexedModel() {
        this.positions = new ArrayList<>();
        this.texCoords = new ArrayList<>();
        this.normals = new ArrayList<>();
        this.indices = new ArrayList<>();
    }
    
    
    public void calcNormals(){
        for(int i = 0; i < indices.size(); i+=3){
            int i0 = indices.get(i);
            int i1 = indices.get(i+1);
            int i2 = indices.get(i+2);
            
            Vector3f v1 = positions.get(i1).sub(positions.get(i0));
            Vector3f v2 = positions.get(i2).sub(positions.get(i0));
            
            Vector3f normal = v1.cross(v2).normalized();
            
            normals.get(i0).set(normals.get(i0).add(normal));
            normals.get(i1).set(normals.get(i1).add(normal));
            normals.get(i2).set(normals.get(i2).add(normal));
        }
        
        for(int i = 0; i < normals.size(); i++){
            normals.get(i).set(normals.get(i).normalized());
        }
    }
    

    public ArrayList<Vector3f> getPositions() {
        return positions;
    }

    public ArrayList<Vector2f> getTexCoords() {
        return texCoords;
    }

    public ArrayList<Vector3f> getNormals() {
        return normals;
    }

    public ArrayList<Integer> getIndices() {
        return indices;
    }
}

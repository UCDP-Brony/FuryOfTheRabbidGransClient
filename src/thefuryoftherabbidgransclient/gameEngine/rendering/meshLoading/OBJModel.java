/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering.meshLoading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import thefuryoftherabbidgransclient.gameEngine.core.Util;
import thefuryoftherabbidgransclient.gameEngine.core.Vector2f;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.Mesh;

/**
 *
 * @author TheDoctor
 */
public class OBJModel {    
    private ArrayList<Vector3f> positions;
    private ArrayList<Vector2f> texCoords;
    private ArrayList<Vector3f> normals;
    private ArrayList<OBJIndex> indices;
    private boolean hasTexCoords;
    private boolean hasNormals;

    public OBJModel(String fileName) {
        this.positions = new ArrayList<>();
        this.texCoords = new ArrayList<>();
        this.normals = new ArrayList<>();
        this.indices = new ArrayList<>();
        this.hasTexCoords = false;
        this.hasNormals = false;
        
        BufferedReader meshReader = null;
        
        try{
            meshReader = new BufferedReader(new FileReader(fileName));
            String line;
            
            while((line = meshReader.readLine()) != null){
                String[] tokens = line.split(" ");
                tokens = Util.getInstance().removeEmptyStrings(tokens);
                
                if(tokens.length == 0 || tokens[0].equals("#")){                    
                    continue;
                } else if(tokens[0].equals("v")){
                    positions.add(new Vector3f(Float.valueOf(tokens[1]),
                                                         Float.valueOf(tokens[2]),
                                                         Float.valueOf(tokens[3])));
                } else if(tokens[0].equals("vt")){                    
                    texCoords.add(new Vector2f(Float.valueOf(tokens[1]),
                                               Float.valueOf(tokens[2])));
                } else if(tokens[0].equals("vn")){
                    normals.add(new Vector3f(Float.valueOf(tokens[1]),
                                                         Float.valueOf(tokens[2]),
                                                         Float.valueOf(tokens[3])));
                }
                
                else if(tokens[0].equals("f")){
                    for(int i = 0; i < tokens.length-3;i++){
                        indices.add(parseOBJIndex(tokens[1]));
                        indices.add(parseOBJIndex(tokens[2 + i]));
                        indices.add(parseOBJIndex(tokens[3 + i]));                        
                    }
                }
            }            
            meshReader.close();            
        }       
        catch (IOException ex) {
            Logger.getLogger(Mesh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public IndexedModel toIndexedModel(){
        IndexedModel result = new IndexedModel();
        IndexedModel normalModel = new IndexedModel();
        HashMap<OBJIndex, Integer> resultIndexMap = new HashMap<>();
        HashMap<Integer, Integer> normalIndexMap = new HashMap<>();
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        
        for(int i = 0; i < indices.size(); i++){
            OBJIndex currentIndex = indices.get(i);
            
            Vector3f currentPosition = positions.get(currentIndex.vertexIndex);
            Vector2f currentTexCoords = hasTexCoords ? texCoords.get(currentIndex.texCoordIndex) : new Vector2f(0,0);
            Vector3f currentNormals = hasNormals ? normals.get(currentIndex.normalIndex) : new Vector3f(0,0,0);
            
            Integer modelVertexIndex = resultIndexMap.get(currentIndex);
            
            if(modelVertexIndex == null){                
                modelVertexIndex = result.getPositions().size();
                resultIndexMap.put(currentIndex, result.getPositions().size());
               
                result.getPositions().add(currentPosition);
                result.getTexCoords().add(currentTexCoords);
                if(hasNormals){
                    result.getNormals().add(currentNormals);
                }
            }
            
            Integer normalModelIndex = normalIndexMap.get(currentIndex.vertexIndex);
            
            if(normalModelIndex == null){ 
                normalModelIndex = normalModel.getPositions().size();
                normalIndexMap.put(currentIndex.vertexIndex, normalModel.getPositions().size());
               
                normalModel.getPositions().add(currentPosition);
                normalModel.getTexCoords().add(currentTexCoords);
                normalModel.getNormals().add(currentNormals);
            }            
            result.getIndices().add(modelVertexIndex);
            normalModel.getIndices().add(normalModelIndex);
            indexMap.put(modelVertexIndex, normalModelIndex);
        }        
        
        if(!hasNormals){
            normalModel.calcNormals();
            
            for(int i = 0; i < result.getPositions().size(); i++){
                result.getNormals().add(normalModel.getNormals().get(indexMap.get(i)));
                        //get(i).set(normalModel.getNormals().get(indexMap.get(i)));
            }
        }
        return result;
    }
    
    private OBJIndex parseOBJIndex(String token){
        String[] values = token.split("/");
        OBJIndex result = new OBJIndex();
        result.vertexIndex = Integer.parseInt(values[0])-1;
        if(values.length > 1){
            hasTexCoords = true;
            result.texCoordIndex = Integer.parseInt(values[1])-1;
            if(values.length > 2){
                hasNormals = true;
                result.normalIndex = Integer.parseInt(values[2])-1;
            }
        }
        return result;
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

    public ArrayList<OBJIndex> getIndices() {
        return indices;
    }
}

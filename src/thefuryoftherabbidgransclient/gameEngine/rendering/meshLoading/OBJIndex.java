/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.rendering.meshLoading;

/**
 *
 * @author TheDoctor
 */
public class OBJIndex {
    public int vertexIndex;
    public int texCoordIndex;
    public int normalIndex;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OBJIndex other = (OBJIndex) obj;
        if (this.vertexIndex != other.vertexIndex) {
            return false;
        }
        if (this.texCoordIndex != other.texCoordIndex) {
            return false;
        }
        return this.normalIndex == other.normalIndex;
    }
    
    @Override
    public int hashCode(){
        final int BASE = 17;
        final int MULTIPLIER = 31;        
        int result = BASE;
        result*= MULTIPLIER * result + vertexIndex;
        result*= MULTIPLIER * result + texCoordIndex;
        result*= MULTIPLIER * result + normalIndex;
        return result;
    }

    
}

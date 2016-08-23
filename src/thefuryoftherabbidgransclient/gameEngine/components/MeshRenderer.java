/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.gameEngine.components;

import thefuryoftherabbidgransclient.gameEngine.rendering.Material;
import thefuryoftherabbidgransclient.gameEngine.rendering.Mesh;
import thefuryoftherabbidgransclient.gameEngine.rendering.RenderingEngine;
import thefuryoftherabbidgransclient.gameEngine.rendering.Shader;

/**
 *
 * @author TheDoctor
 */
public class MeshRenderer extends GameComponent{
    
    private Mesh mesh;
    private Material material;

    public MeshRenderer(Mesh mesh, Material material) {
        this.mesh = mesh;
        this.material = material;
    }
    
    @Override
    public void render(Shader shader, RenderingEngine renderingEngine){
        shader.bind();
        shader.updateUniforms(getTransform(), material, renderingEngine);
        mesh.draw();
    }
}

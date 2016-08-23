/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefuryoftherabbidgransclient.game;

import thefuryoftherabbidgransclient.gameEngine.components.Camera;
import thefuryoftherabbidgransclient.gameEngine.components.DirectionalLight;
import thefuryoftherabbidgransclient.gameEngine.components.FreeLook;
import thefuryoftherabbidgransclient.gameEngine.components.FreeMove;
import thefuryoftherabbidgransclient.gameEngine.components.GameComponent;
import thefuryoftherabbidgransclient.gameEngine.components.MeshRenderer;
import thefuryoftherabbidgransclient.gameEngine.components.PointLight;
import thefuryoftherabbidgransclient.gameEngine.components.SpotLight;
import thefuryoftherabbidgransclient.gameEngine.core.Game;
import thefuryoftherabbidgransclient.gameEngine.core.GameObject;
import thefuryoftherabbidgransclient.gameEngine.core.Quaternion;
import thefuryoftherabbidgransclient.gameEngine.core.Vector2f;
import thefuryoftherabbidgransclient.gameEngine.core.Vector3f;
import thefuryoftherabbidgransclient.gameEngine.rendering.Attenuation;
import thefuryoftherabbidgransclient.gameEngine.rendering.Material;
import thefuryoftherabbidgransclient.gameEngine.rendering.Mesh;
import thefuryoftherabbidgransclient.gameEngine.rendering.Texture;
import thefuryoftherabbidgransclient.gameEngine.rendering.Vertex;
import thefuryoftherabbidgransclient.gameEngine.rendering.Window;


/**
 *
 * @author TheDoctor
 */
public class TestGame extends Game {    
    public TestGame(){
        
    }
    
    @Override
    public void init(){
        GameObject planeObject = new GameObject();

        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                                                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                                                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                                                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = { 0, 1, 2,
		          2, 1, 3};
        
        
        Vertex[] vertices2 = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth/10, 0.0f, -fieldDepth/10), new Vector2f(0.0f, 0.0f)),
                                                new Vertex( new Vector3f(-fieldWidth/10, 0.0f, fieldDepth/10 * 3), new Vector2f(0.0f, 1.0f)),
                                                new Vertex( new Vector3f(fieldWidth/10 * 3, 0.0f, -fieldDepth/10), new Vector2f(1.0f, 0.0f)),
                                                new Vertex( new Vector3f(fieldWidth/10 * 3, 0.0f, fieldDepth/10 * 3), new Vector2f(1.0f, 1.0f))};

        int indices2[] = { 0, 1, 2,
		          2, 1, 3};
        

        
        Mesh mesh = new Mesh(vertices, indices, true);
        Mesh mesh2 = new Mesh(vertices2, indices2, true);
        Material material = new Material();
        Material material2 = new Material();
        material.addTexture("diffuse", new Texture("alien_ground_texture_05-1024x1024.png"));
        material.addFloat("specularIntensity", 1); 
        material.addFloat("specularPower", 8); 
        material2.addTexture("diffuse", new Texture("test.jpg"));
        material2.addFloat("specularIntensity", 1); 
        material2.addFloat("specularPower", 8); 
        MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
        planeObject.addComponent(meshRenderer);
        planeObject.getTransform().getPos().set(0, -1, 5);
        
        Mesh tempMesh = new Mesh("monkey3.obj");
        
        GameComponent directionalLight = new DirectionalLight(new Vector3f(1,1,1),1f);
        GameObject directionalLightObject = new GameObject().addComponent(directionalLight);        
        
        GameObject pointLightObject = new GameObject().addComponent(new PointLight(new Vector3f(0,1,0),0.4f,new Attenuation(0,0,1)));
        
        GameObject spotLightObject = new GameObject().addComponent(new SpotLight(new Vector3f(0,1,1),0.9f,new Attenuation(0,0,0.1f), 0.3f));
        
        spotLightObject.getTransform().getPos().set(5,0,5);
        spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float) Math.toRadians(90.0f)));
        
        addObject(planeObject);                
        addObject(directionalLightObject);
        addObject(pointLightObject);
        addObject(spotLightObject);
        
        GameObject testMesh1 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
        GameObject testMesh2 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
        GameObject testMesh3 = new GameObject().addComponent(new MeshRenderer(tempMesh, material));
        testMesh1.getTransform().getPos().set(0, 2, 0);
        testMesh1.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), 0.4f));
        testMesh2.getTransform().getPos().set(0, 0, 5);
        testMesh1.addChild(testMesh2);
        testMesh2.addChild(new GameObject().addComponent(new FreeLook(0.5f)).addComponent(new FreeMove(10)).addComponent(new Camera((float)Math.toRadians(70f), (float)Window.getWidth()/(float)Window.getHeight(), 0.1f, 1000.0f)));
        addObject(testMesh1);
        addObject(testMesh3);
        
        testMesh3.getTransform().getPos().set(5,5,5);
        testMesh3.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float) Math.toRadians(-70.0f)));
        addObject(new GameObject().addComponent(new MeshRenderer(new Mesh("monkey3.obj"), material2)));
        directionalLight.getTransform().setRot(new Quaternion(new Vector3f(1,0,0),(float) Math.toRadians(-45)));
    }
}

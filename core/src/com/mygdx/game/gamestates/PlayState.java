package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.GameStateManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.utils.TiledObjectUtil;


/**
 * Created by englund on 10/09/15.
 */
public class PlayState extends GameState {

    ShapeRenderer playerObject;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    Texture playerTexture, closedChestTexture, openChestTexture;
    SpriteBatch batch;
    ShapeRenderer sr;
    Sound sound;
    boolean playedBefore = false;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Body player, object;




    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        playerObject = new ShapeRenderer();

        tiledMap = new TmxMapLoader().load("assets/levelOne.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        camera.update();
        batch = new SpriteBatch();


        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();
        playerTexture = new Texture("assets/dwarf.png");
        closedChestTexture = new Texture("assets/chestclosed.png");
        openChestTexture = new Texture("assets/chestopen.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("assets/win.mp3"));



        player = createBox(150f, 200f, 16f, 16f, false);
        object = createBox(150f, 184f, 16f, 16f, false);

        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("collision-layer").getObjects());



    }

    @Override
    public void update(float dt) {
        //handleInput();
        world.step(1 / 60f, 6, 2);
        inputUpdate(dt);

        tiledMapRenderer.setView(camera);
        batch.setProjectionMatrix(camera.combined);
        checkCorrectPostion();

    }

    @Override
    public void draw() {
        update(Gdx.graphics.getDeltaTime());


        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        batch.begin();
            batch.draw(playerTexture, player.getPosition().x * 70 - (playerTexture.getWidth() / 2), player.getPosition().y * 70 - (playerTexture.getHeight() / 2));
            if(!checkCorrectPostion())
                batch.draw(closedChestTexture, object.getPosition().x * 70 - (closedChestTexture.getWidth() / 2), object.getPosition().y * 70 - (closedChestTexture.getHeight() / 2));
            else {
                batch.draw(openChestTexture, object.getPosition().x * 70 - (openChestTexture.getWidth() / 2), object.getPosition().y * 70 - (openChestTexture.getHeight() / 2));
                if(!playedBefore) {
                    sound.play(1.0f);
                    playedBefore = true;
                }
            }
        batch.end();
        camera.update();
        //b2dr.render(world, camera.combined.scl(70));




    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                playerObject.translate(-5, 0, 0);
            else
                playerObject.translate(-5, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                playerObject.translate(5, 0, 0);
            else
                playerObject.translate(5, 0, 0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerObject.translate(0, 5, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            playerObject.translate(0, -5, 0);
        }
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    public Body createBox(float x, float y, float width, float height, boolean isStatic) {
        Body pBody;

        // pBody properties
        BodyDef bdef = new BodyDef();
        if(isStatic)
            bdef.type = BodyDef.BodyType.StaticBody;
        else
            bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(x / 70, y / 70);
        bdef.fixedRotation = true;

        // init pBody in world
        pBody = world.createBody(bdef);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 70, height / 70); // set from center, we want 32x32 box so we divide by 2

        // add fixture to body and dispose
        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;
    }

    public void inputUpdate(float dt) {

        player.setGravityScale(0);
        object.setGravityScale(0);
        object.setLinearDamping(100f);


        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {


            player.applyForceToCenter(new Vector2(-184f, 0), false);



        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

            System.out.println("RIGHT");
            player.applyForceToCenter(new Vector2(184f, 0), false);
        }
        // only update once on key press
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.applyForceToCenter(new Vector2(0, 184f), false);
            System.out.println("UP");
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            player.applyForceToCenter(new Vector2(0, -184f), false);
            System.out.println("DOWN");
        }
        player.setLinearVelocity(0, 0);

    }

    public boolean checkCorrectPostion() {
        Vector2 vectorObject = object.getPosition();
        return(vectorObject.x < 0.7 && vectorObject.y < 6.8);
    }

}


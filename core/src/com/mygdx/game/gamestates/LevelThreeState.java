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
public class LevelThreeState extends GameState {

    ShapeRenderer playerObject;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    Texture playerTexture, redTexture, greenTexture, purpleTexture;
    SpriteBatch batch;
    ShapeRenderer sr;
    Sound sound;
    boolean playedBefore = false;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Body player, redChest, greenChest, purpleChest;

    public LevelThreeState(GameStateManager gsm) {
        super(gsm);
    }



    @Override
    public void init() {
        playerObject = new ShapeRenderer();

        tiledMap = new TmxMapLoader().load("assets/levelThree.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        camera.update();
        batch = new SpriteBatch();


        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();
        playerTexture = new Texture("assets/dwarf.png");
        redTexture = new Texture("assets/chestclosed.png");
        greenTexture = new Texture("assets/green.png");
        purpleTexture = new Texture("assets/purple.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("assets/win.mp3"));

        player = createBox(210f, 240f, 16f, 16f, false);
        redChest = createBox(32f, 192f, 16f, 16f, false);
        greenChest = createBox(235f, 300f, 16f, 16f, false);
        purpleChest = createBox(260f, 200f, 16f, 16f, false);

        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("Boundaries").getObjects());

    }

    @Override
    public void update(float dt) {
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
        if(!checkCorrectPostion()) {
            batch.draw(redTexture, redChest.getPosition().x * 70 - (redTexture.getWidth() / 2), redChest.getPosition().y * 70 - (redTexture.getHeight() / 2));
            batch.draw(greenTexture, greenChest.getPosition().x * 70 - (greenTexture.getWidth() / 2), greenChest.getPosition().y * 70 - (greenTexture.getHeight() / 2));
            batch.draw(purpleTexture, purpleChest.getPosition().x * 70 - (purpleTexture.getWidth() / 2), purpleChest.getPosition().y * 70 - (purpleTexture.getHeight() / 2));

        }
        else {
            if(!playedBefore) {
                sound.play(1.0f);
                playedBefore = true;
                gsm.setState(GameStateManager.MENU);
            }
        }
        batch.end();
        camera.update();

        //b2dr.render(world, camera.combined.scl(70));
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
        pBody.setLinearDamping(100f);
        pBody.setGravityScale(0f);
        return pBody;
    }

    public void inputUpdate(float dt) {
        player.setLinearDamping(0f);


        //only update once on key press
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            player.applyForceToCenter(new Vector2(-184f, 0), false);

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            System.out.println("RIGHT");
            player.applyForceToCenter(new Vector2(184f, 0), false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.applyForceToCenter(new Vector2(0, 184f), false);
            System.out.println("UP");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            player.applyForceToCenter(new Vector2(0, -184f), false);
            System.out.println("DOWN");
        }
        player.setLinearVelocity(0, 0);
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            restart();
        }

    }

    public boolean checkCorrectPostion() {
        Vector2 red = redChest.getPosition();
        Vector2 green = greenChest.getPosition();
        Vector2 purple = purpleChest.getPosition();

        float l1 = Vector2.len(redChest.getPosition().x, redChest.getPosition().y);
        float l2 = Vector2.len(greenChest.getPosition().x, greenChest.getPosition().y);
        float l3 = Vector2.len(purpleChest.getPosition().x, purpleChest.getPosition().y);

        int tot =Math.round(redChest.getWorldPoint(red).x) + Math.round(redChest.getWorldPoint(red).y) +
        Math.round(greenChest.getWorldPoint(green).x) +
        Math.round(greenChest.getWorldPoint(green).y) +
        Math.round(purpleChest.getWorldPoint(purple).x)+
        Math.round(purpleChest.getWorldPoint(purple).y);

        System.out.println("L1: " + purpleChest.getPosition().x);
        System.out.println("L2: " + purpleChest.getPosition().y);

        if((Math.round(redChest.getPosition().x) == 1 && Math.round(redChest.getPosition().y) == 1)
                && (Math.round(greenChest.getPosition().x) == 1 && Math.round(greenChest.getPosition().y) == 7)
                && (Math.round(purpleChest.getPosition().x) == 7 && Math.round(purpleChest.getPosition().y) == 7))
            return true;

        return false;


    }

    public void restart() {
        //Set everything back to start
        world.dispose();
        world = new World(new Vector2(0, -9.8f), false);
        player = createBox(150f, 200f, 16f, 16f, false);
        redChest = createBox(150f, 184f, 16f, 16f, false);
        greenChest = createBox(235f, 300f, 16f, 16f, false);
        purpleChest = createBox(260f, 200f, 16f, 16f, false);
        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("Boundaries").getObjects());

    }

}


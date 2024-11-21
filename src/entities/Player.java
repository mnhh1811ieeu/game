package entities;

import static utilz.Constains.Directions.DOWN;
import static utilz.Constains.Directions.LEFT;
import static utilz.Constains.Directions.RIGHT;
import static utilz.Constains.Directions.UP;
import static utilz.Constains.PlayerConstants.GetSpriteAmount;
import static utilz.Constains.PlayerConstants.IDLE;
import static utilz.Constains.PlayerConstants.RUNNING;
import static utilz.Constains.PlayerConstants.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.HelpMethods.*;
import javax.imageio.ImageIO;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int playerAction = IDLE;
	private boolean left, up, right, down, jump;
	private boolean moving = false, attacking = false;
	private int aniTick, aniIndex, aniSpeed = 25;// anispeed càng lớn càng chậm
	private float playerSpeed = 1f;
	private int[][] lvData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	// jump gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -3.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		updatePos();

		updateAnimationTick();
		setAnimation();

	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset),
				width, height, null);
		drawHitbox(g);
	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

			}
	}

	// TODO Auto-generated method stub
	public void loadLvData(int[][] lvData) {
		this.lvData = lvData;
		if(!IsEntityOnFloor(hitBox, lvData))
			inAir=true;
	}

	private void updatePos() {
		moving = false;
		if(jump)
			jump();
		if (!left && !right && !inAir)
			return;

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;
		if(!inAir) {
			if(!IsEntityOnFloor(hitBox,lvData)) 
				inAir=true;
			
		}
		
		if (inAir) {
			if (CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvData)) {
				hitBox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}
		} else
			updateXPos(xSpeed);
		moving = true;

	}

	private void jump() {
		// TODO Auto-generated method stub
		if(inAir)
			return;
		inAir=true;
		airSpeed=jumpSpeed;
	}

	private void resetInAir() {
		// TODO Auto-generated method stub
		inAir = false;
		airSpeed = 0;

	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvData)) {
			hitBox.x += xSpeed;
		} else {
			hitBox.x = GetEntityXPosNextToWall(hitBox, xSpeed);
		}
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	private void updateAnimationTick() {
		// TODO Auto-generated method stub
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}

	}

	private void setAnimation() {
		int startAni = playerAction;
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
		if(inAir) {
			if(airSpeed<0)
				playerAction=JUMP;
			else
				playerAction=FALLING;
		}
		if (attacking)
			playerAction = ATTACK_1;
		if (startAni != playerAction)
			resetAniTick();
	}
	
	public void setJump(boolean jump) {
		this.jump=jump;
	}

	private void resetAniTick() {
		// TODO Auto-generated method stub
		aniTick = 0;
		aniIndex = 0;
	}
}

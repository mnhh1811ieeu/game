package utilz;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvData) {
		if (!IsSolid(x, y, lvData))
			if (!IsSolid(x + width, y + height, lvData))
				if (!IsSolid(x + width, y, lvData))
					if (!IsSolid(x, y + height, lvData))
						return true;
		return false;

	}

	private static boolean IsSolid(float x, float y, int[][] lvData) {
		if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;
		float xIndex = x / Game.TITLES_SIZE;
		float yIndex = y / Game.TITLES_SIZE;
		int value = lvData[(int) yIndex][(int) xIndex];
		if (value >= 48 || value < 0 || value != 11)
			return true;
		return false;
	}

	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
		int currentTile = (int) (hitBox.x / Game.TITLES_SIZE);
		if (xSpeed > 0) {
			// right
			int tileXPos = currentTile * Game.TITLES_SIZE;
			int xOffset = (int) (Game.TITLES_SIZE - hitBox.width);
			return tileXPos + xOffset - 1;
		} else {
			// left
			return currentTile * Game.TITLES_SIZE;
		}
	}

	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
		int currentTile = (int) (hitBox.y / Game.TITLES_SIZE);
		if (airSpeed > 0) {
			// faling-touch floor
			int tileYPos = currentTile * Game.TITLES_SIZE;
			int yOffset = (int) (Game.TITLES_SIZE - hitBox.height);
			return tileYPos + yOffset - 1;
		} else {
			// jumping
			return currentTile * Game.TITLES_SIZE;
		}
	}

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvData) {
		// check the pixel below btm left right
		if (!IsSolid(hitBox.x, hitBox.y + hitBox.height + 1, lvData))
			if (!IsSolid(hitBox.x, hitBox.y + hitBox.height + 1, lvData))
				return false;
		return true;

	}
}

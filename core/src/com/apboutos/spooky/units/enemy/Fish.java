package com.apboutos.spooky.units.enemy;

import com.apboutos.spooky.level.TextureLoader;
import com.apboutos.spooky.utilities.Direction;
import com.apboutos.spooky.utilities.EnemyType;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.apboutos.spooky.level.TextureLoader;
import com.apboutos.spooky.utilities.BlockType;
import com.apboutos.spooky.utilities.Direction;
import com.apboutos.spooky.utilities.EnemyType;

/**
 * 
 * This class represents the fish enemy. This is the most basic enemy. It has
 * very a simple AI which just moves and turns when it collides with a block.
 * 
 * @author Apostolis Boutos
 *
 */
public class Fish extends Enemy{

	public Fish(float x, float y, SpriteBatch batch, EnemyType enemyType, TextureLoader textureLoader) {
		
		
		super(x, y, batch, enemyType);
		this.textureLoader = textureLoader;
		atlasLeft = textureLoader.getFishMovingLeft();
		atlasRight = textureLoader.getFishMovingRight();
		atlasUp = textureLoader.getFishMovingUp();
		atlasDown = textureLoader.getFishMovingDown();
		
		animationLeft  = new Animation(1/10f, atlasLeft.getRegions());
		animationRight = new Animation(1/10f,atlasRight.getRegions());
		animationUp    = new Animation(1/10f,atlasUp.getRegions());
		animationDown  = new Animation(1/10f,atlasDown.getRegions());
		
		squash = new Sprite(textureLoader.getSquash());
		speed  = new Vector2(3,3); 
	}
	
	/** 
	 *  Updates the player's character logic and draws him on the screen
	 *  
	 *  WARNING Must be placed between
	 *  SpriteBatch.begin()
	 *  and
	 *  SpriteBatch.end()
	 * 
	 */
	@ Override
	public void update(float delta){
		
		this.delta = delta;
		artificialIntelligence();
		collisionDetect();
		draw();
	}
	
	/**
	 * Generates the logic which the unit will be using in order to move,
	 * push blocks and generally try to antagonize the player.
	 */
	private void artificialIntelligence(){
		iAmMoving = true;
		/* The logic of the fish is very simple. It will randomly change directions
		 * when it collides with another unit like a block, or when it has moved 4 times.
		 * It will not push any blocks.
		 */
		if (numberOfBlocksMoved%400 == 0)
		{
			plotNewCourse();
		}
		if (iHaveCollidedWithMap == true )
		{
			plotNewCourse();
			iHaveCollidedWithMap = false;
		}	
		if(iHaveCollidedWithBlock == true)
		{	
			if(tmpCollisionBlock.getBlockType() == BlockType.Standard && deathTimerStarted == false  && tmpCollisionBlock.isMoving() == false)
			{
				if(tmpCollisionBlock.canMove(direction) == true) 
				{
					tmpCollisionBlock.kill();
				}
				else
				{
					plotNewCourse();
				}
			}
			else
			{
				plotNewCourse();
			}
			iHaveCollidedWithBlock = false;
		}
	}
	
	private void plotNewCourse(){
		
		Direction tmpDirection = direction;
		if (tmpDirection == Direction.LEFT)
		{
			int b = ((int)Math.round(Math.random()*100))%2 + 1;
			if (b == 1)
			{
				tmpDirection = Direction.UP;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.DOWN;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.RIGHT;
				}
			}
			else
			{
				tmpDirection = Direction.DOWN;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.UP;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.RIGHT;
				}
			}
		}
		else if (tmpDirection == Direction.RIGHT)
		{
			int b = ((int)Math.round(Math.random()*100))%2 + 1;
			if (b == 1)
			{
				tmpDirection = Direction.UP;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.DOWN;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.RIGHT;
				}
			}
			else
			{
				tmpDirection = Direction.DOWN;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.UP;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.LEFT;
				}
			}
		}
		else if (tmpDirection == Direction.UP)
		{
			int b = ((int)Math.round(Math.random()*100))%2 + 1;
			if (b == 1)
			{
				tmpDirection = Direction.LEFT;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.RIGHT;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.DOWN;
				}
			}
			else
			{
				tmpDirection = Direction.RIGHT;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.LEFT;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.DOWN;
				}
			}
		}
		else if (tmpDirection == Direction.DOWN)
		{
			int b = ((int)Math.round(Math.random()*100))%2 + 1;
			if (b == 1)
			{
				tmpDirection = Direction.LEFT;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.RIGHT;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.UP;
				}
			}
			else
			{
				tmpDirection = Direction.RIGHT;
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.LEFT;
				}
				if (this.canMove(tmpDirection) == false)
				{
					tmpDirection = Direction.DOWN;
				}
			}
		}
		direction = tmpDirection;
	}

}//Class ends here.
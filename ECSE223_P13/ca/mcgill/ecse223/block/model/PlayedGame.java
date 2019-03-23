/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.awt.geom.Line2D;
import ca.mcgill.ecse223.block.application.Block223Application;
import java.lang.Math.*;
import java.awt.geom.*;
import java.awt.Point;
import ca.mcgill.ecse223.block.model.BouncePoint;
import ca.mcgill.ecse223.block.model.BouncePoint.BounceDirection;
import java.util.*;

// line 11 "../../../../../Block223PlayMode.ump"
// line 104 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 1000;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to PlayedGame, Game, and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to PlayedGame, Game, and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 1;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 60 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top right corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 19 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 20 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 21 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 22 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 23 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 24 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 25 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 13 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 31 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 47 "../../../../../Block223PlayMode.ump"
   public boolean isBallOutOfBounds(){
    double x1 = this.currentBallX;
	   double y1 = this.currentBallY;
	   double x2 = this.currentBallX + (this.ballDirectionX) * getWaitTime();
	   double y2 = this.currentBallY + (this.ballDirectionY) * getWaitTime();
	   double x3 = 0;
	   double y3 = Game.WALL_PADDING + Game.PLAY_AREA_SIDE;
	   double x4 = Game.PLAY_AREA_SIDE;
	   double y4 = Game.WALL_PADDING + Game.PLAY_AREA_SIDE;
	   
	  return Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
  }


  /**
   * Guards
   */
  // line 38 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
    this.setBounce(bp);
    return bp != null;
  }

  // line 44 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    Rectangle2D paddleRect = new Rectangle2D.Double();
		Line2D l = new Line2D.Double();
		ArrayList<BouncePoint> intersect = new ArrayList<>();
		int counter = 0;
		l.setLine(getCurrentBallX(), getCurrentBallY(), getCurrentBallX() + ballDirectionX,
				getCurrentBallY() + ballDirectionY);
		paddleRect.setFrame(getCurrentPaddleX() - getCurrentPaddleLength() - Ball.BALL_DIAMETER / 2,
				getCurrentPaddleY() - Ball.BALL_DIAMETER / 2, getCurrentPaddleLength() + Ball.BALL_DIAMETER,
				Ball.BALL_DIAMETER / 2 + Paddle.PADDLE_WIDTH);
		if (paddleRect.intersectsLine(l)) {
			Line2D A = new Line2D.Double();
			A.setLine(getCurrentPaddleX() - getCurrentPaddleLength(), getCurrentPaddleY() - Ball.BALL_DIAMETER / 2,
					getCurrentPaddleLength(), getCurrentPaddleY() - Ball.BALL_DIAMETER / 2);
			Line2D B = new Line2D.Double();
			B.setLine(getCurrentPaddleX() - getCurrentPaddleLength() - Ball.BALL_DIAMETER / 2, getCurrentPaddleY(),
					getCurrentPaddleX() - getCurrentPaddleLength() - Ball.BALL_DIAMETER / 2,
					getCurrentPaddleY() - Ball.BALL_DIAMETER / 2);
			Line2D C = new Line2D.Double();
			C.setLine(getCurrentPaddleX() + Ball.BALL_DIAMETER / 2, getCurrentPaddleY(),
					getCurrentPaddleX() + Ball.BALL_DIAMETER / 2, getCurrentPaddleY() - Ball.BALL_DIAMETER / 2);
			if (C.intersectsLine(l)) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(C, l).x, calculateIntersectionPoint(C, l).y,
						BounceDirection.FLIP_X));
				counter++;
			}
			if (A.intersectsLine(l)) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(A, l).x, calculateIntersectionPoint(A, l).y,
						BounceDirection.FLIP_Y));
				counter++;
			}
			if (B.intersectsLine(l)) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(B, l).x, calculateIntersectionPoint(B, l).y,
						BounceDirection.FLIP_X));
				counter++;
			}

			BouncePoint closest = null;
			if (counter == 1) {
				closest = intersect.get(0);
			} else {
				for (int a = 0; a < counter - 1; a++) {
					if (isCloser(intersect.get(a), intersect.get(a + 1))) {
						closest = intersect.get(a);
					} else {
						closest = intersect.get(a + 1);
					}
				}
			}
			return closest;

		} else {
			return null;
		}
  }

  // line 100 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointBlock(){
    // TODO Auto-generated method stub
	return null;
  }

  // line 105 "../../../../../Block223States.ump"
   private void bounceBall(){
    // get params
		double distanceX = getBallDirectionX();
		double distanceY = getBallDirectionY();
		double positionX = getCurrentBallX();
		double positionY = getCurrentBallY();
		double bouncePointX = getBounce().getX();
		double bouncePointY = getBounce().getY();
		double distanceLeftX = distanceX - Math.abs(bouncePointX - positionX);
		double distanceLeftY = distanceY - Math.abs(bouncePointY - positionY);

		BounceDirection BD = getBounce().getDirection();
		// flip the direction
		if (distanceLeftX != 0 || distanceLeftY != 0) {
			if (BD.equals(BounceDirection.FLIP_BOTH)) {

				
				ballDirectionX*=-1;
				ballDirectionY*=-1;
				currentBallX = bouncePointX + distanceLeftX * Math.signum(ballDirectionX);
				currentBallY = bouncePointY + distanceLeftY * Math.signum(ballDirectionY);
			}
			if (BD.equals(BounceDirection.FLIP_Y)) {

				ballDirectionX+= 0.1 * Math.abs(ballDirectionY) * Math.signum(ballDirectionX);
				ballDirectionY*=-1;
				currentBallY = bouncePointY + distanceLeftY * Math.signum(ballDirectionY);
				currentBallX = bouncePointX + (distanceLeftX + 0.1 * distanceLeftY)* Math.signum(ballDirectionX);
			}
			if (BD.equals(BounceDirection.FLIP_X)) {

				ballDirectionY += 0.1 * Math.abs(ballDirectionX) * Math.signum(ballDirectionY);
				ballDirectionX*=-1;
				currentBallX = bouncePointX + distanceLeftX * Math.signum(ballDirectionX);
				currentBallY = bouncePointY + (distanceLeftY + 0.1 * distanceLeftX)* Math.signum(ballDirectionY);
				
			}
		}
  }

  // line 147 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    
    if (lives == 1) {
    	outOfBounds = this.isBallOutOfBounds();
    }
    return outOfBounds;
  }

  // line 156 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = this.isBallOutOfBounds();
    return outOfBounds;
  }

  // line 160 "../../../../../Block223States.ump"
   private Point calculateIntersectionPoint(Line2D s, Line2D d){
    double sNumerator = s.getY1() * d.getX1() + s.getX2() * d.getY1() - s.getX1() * d.getY1()
				- s.getY2() * d.getX1();
		double sDenominator = d.getY2() * d.getX1() - d.getX2() * d.getY1();

		// parallel ... 0 or infinite points, or one of the vectors is 0|0
		if (sDenominator == 0) {
			return null;
		}

		double sz = sNumerator / sDenominator;

		double t;
		if (d.getX1() != 0) {
			t = (s.getX2() + sz * d.getX2() - s.getX1()) / d.getX1();
		} else {
			t = (s.getY2() + sz * d.getY2() - s.getY1()) / d.getY1();
		}

		Point i1 = new Point();
		i1.setLocation(s.getX1() + t * d.getX1(), s.getY1() + t * d.getY1());

		return i1;
  }

  // line 185 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = this.getGame();
    	int nrLevels = game.numberOfLevels();
    	this.setBounce(null);
    	if(nrLevels==currentLevel){
    		int nrBlocks = this.numberOfBlocks();
    		if(nrBlocks ==1){
    			PlayedBlockAssignment block = this.getBlock(0);
    			BouncePoint bp = this.calculateBouncePointBlock();
    			if(bp == null){
    				return false;
    			}
    			else{
    				this.setBounce(bp);
    				return true;
    			}	
    		}
    	}
    return false;
  }

  // line 206 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    int nrBlocks = this.numberOfBlocks();
    	this.setBounce(null);
    	if(nrBlocks == 1){
    		PlayedBlockAssignment block = this.getBlock(0);
    		BouncePoint bp = this.calculateBouncePointBlock();
    			if(bp == null){
    				return false;
    			}
    			else{
    				this.setBounce(bp);
    				return true;
    			}	
    	}
    return false;
  }

  // line 223 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    int nrBlocks = this.numberOfBlocks();
    	this.setBounce(null);
    	for(int i=0; i<nrBlocks; i++){
    		PlayedBlockAssignment block = getBlock(i);
    		if(block == null){
    				return false;
    			}
    			else{
    				BouncePoint bp = this.calculateBouncePointBlock();
    				BouncePoint bounce = this.getBounce();
    				boolean closer = this.isCloser(bp, bounce);
    				if(closer){
    					this.setBounce(bp);
    					return true;
    				}
    				
    			}
    		
    	}
    	return this.getBounce() != null;
  }

  // line 246 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
    this.setBounce(bp);
    return bp != null;
  }

  // line 252 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointWall(){
    BouncePoint bp = null;
		Line2D l = new Line2D.Double();
		l.setLine(getCurrentBallX(), getCurrentBallY(), getCurrentBallX() + ballDirectionX,
				getCurrentBallY() + ballDirectionY);
		Line2D A = new Line2D.Double();
		Line2D B = new Line2D.Double();
		Line2D C = new Line2D.Double();
		A.setLine(Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER / 2, Game.PLAY_AREA_SIDE);
		B.setLine(Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER / 2, Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER / 2,
				Ball.BALL_DIAMETER / 2);
		C.setLine(Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER / 2,
				Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER / 2, Game.PLAY_AREA_SIDE);

		if (A.intersectsLine(l) && B.intersectsLine(l)) {

			if (calculateIntersectionPoint(A, l).equals(calculateIntersectionPoint(B, l))) {
				bp = new BouncePoint(calculateIntersectionPoint(A, l).x, calculateIntersectionPoint(A, l).y,
						BounceDirection.FLIP_BOTH);
			} else {
				bp = new BouncePoint(Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER / 2, BounceDirection.FLIP_BOTH);
			}

		} else if (B.intersectsLine(l) && C.intersectsLine(l)) {
			if (calculateIntersectionPoint(C, l).equals(calculateIntersectionPoint(B, l))) {
				bp = new BouncePoint(calculateIntersectionPoint(C, l).x, calculateIntersectionPoint(C, l).y,
						BounceDirection.FLIP_BOTH);
			} else {
				bp = new BouncePoint(Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER / 2,
						BounceDirection.FLIP_BOTH);
			}

		} else if (A.intersectsLine(l)) {

			bp = new BouncePoint(calculateIntersectionPoint(A, l).x, calculateIntersectionPoint(A, l).y,
					BounceDirection.FLIP_X);

		} else if (C.intersectsLine(l)) {

			bp = new BouncePoint(calculateIntersectionPoint(C, l).x, calculateIntersectionPoint(C, l).y,
					BounceDirection.FLIP_X);

		} else if (B.intersectsLine(l)) {

			bp = new BouncePoint(calculateIntersectionPoint(B, l).x, calculateIntersectionPoint(B, l).y,
					BounceDirection.FLIP_Y);
		}

		return bp;
  }


  /**
   * Actions
   */
  // line 306 "../../../../../Block223States.ump"
   private void doSetup(){
    this.resetCurrentBallX();
	   this.resetCurrentBallY();
	   this.resetBallDirectionX();
	   this.resetBallDirectionY();	   
	   this.resetCurrentPaddleX();
	   Game game = this.getGame();
	   Level level = game.getLevel(this.getCurrentLevel() - 1);
	   List<BlockAssignment> assignments = level.getBlockAssignments();
	   
	   for(BlockAssignment assignment : assignments) {
		   PlayedBlockAssignment pblock = new PlayedBlockAssignment(
				   Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (assignment.getGridHorizontalPosition() - 1),
				   Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (assignment.getGridVerticalPosition() - 1),
				   assignment.getBlock(),
				   this
				   );
	   }
	   
	   int numberOfBlocks = assignments.size();
	   int maxHor = (1+(Game.PLAY_AREA_SIDE-2*Game.WALL_PADDING-Block.SIZE)/(Block.SIZE+Game.COLUMNS_PADDING));
	   int maxVer = (1+(Game.PLAY_AREA_SIDE-Paddle.VERTICAL_DISTANCE-Game.WALL_PADDING-Paddle.PADDLE_WIDTH-Ball.BALL_DIAMETER-Block.SIZE)/(Block.SIZE+Game.ROW_PADDING));
	   int x;
	   int y;
	   
	   while(numberOfBlocks < game.getNrBlocksPerLevel()) {
		   Random rand = new Random();
		   x = rand.nextInt(maxHor);
		   ++x;
		   y = rand.nextInt(maxVer);
		   ++y;
		   
		   BlockAssignment foundAssignment = level.findBlockAssignment(x, y);
		   while(foundAssignment != null) {
			   if(y < maxVer) {
				   if(x < maxHor) x++;
				   if(x >= maxHor) {
					   x = 1;
					   y++;
				   }
			   }
			   
			   else if(y >= maxVer) {
				   x = 1;
				   y = 1;
			   }  
			   foundAssignment = level.findBlockAssignment(x, y);
		   }
		   
		   new PlayedBlockAssignment(
				   x,
				   y,
				   game.getRandomBlock(),
				   this
				   );
	   }
  }

  // line 365 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    this.bounceBall();
  }

  // line 369 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    this.setLives(lives - 1);
    this.resetCurrentBallX();
    this.resetCurrentBallY();
    this.resetBallDirectionX();
    this.resetBallDirectionY();
    this.resetCurrentPaddleX();
  }

  // line 378 "../../../../../Block223States.ump"
   private void doHitBlock(){
    int score = this.getScore();
    BouncePoint bounce = this.getBounce();
    PlayedBlockAssignment pblock = bounce.getHitBlock();
    Block block = pblock.getBlock();
    int bscore = block.getPoints();
    this.setScore(score + bscore);
    pblock.delete();
    this.bounceBall();
  }

  // line 389 "../../../../../Block223States.ump"
   private boolean isCloser(BouncePoint first, BouncePoint second){
    if (second == null){
  		return true;
  	}
  	if (first == null){
  		return false;
  	}
  	if (Math.sqrt(Math.pow((this.getCurrentBallX()-first.getX()),2.0)+Math.pow((this.getCurrentBallY()-first.getY()),2.0)) <
  		Math.sqrt(Math.pow((this.getCurrentBallX()-second.getX()),2.0)+Math.pow((this.getCurrentBallY()-second.getY()),2.0))){
  			return true;
  	}
  	else{
  	 return false;
  	}
  }

  // line 405 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    this.doHitBlock();
    int level = this.getCurrentLevel();
    this.setCurrentLevel(level+1);
    this.setCurrentPaddleLength(this.getGame().getPaddle().getMaxPaddleLength()-(this.getGame().getPaddle().getMaxPaddleLength()
    							-this.getGame().getPaddle().getMaxPaddleLength())/(this.getGame().numberOfLevels()-1)*(this.getCurrentLevel()-1));
    this.setWaitTime(INITIAL_WAIT_TIME*Math.pow(this.getGame().getBall().getBallSpeedIncreaseFactor(),(double)(this.getCurrentLevel()-1)));
  }

  // line 415 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = getCurrentBallX();
	double y = getCurrentBallY();
	double dx = getBallDirectionX();
	double dy = getBallDirectionY();
	
	setCurrentBallX(x + dx);
	setCurrentBallY(y + dy);
  }

  // line 425 "../../../../../Block223States.ump"
   private void doGameOver(){
    Block223 block223 = Block223Application.getBlock223();
    
    Player p = this.getPlayer();
    
    if (p != null) {
    	
    	game = this.getGame();
    	HallOfFameEntry hof = new HallOfFameEntry(score, playername, p, game, block223);
    	game.setMostRecentEntry(hof);
    	
    }
    
    this.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 107 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}
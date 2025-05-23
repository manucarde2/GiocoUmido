public class GameStateManager
{
    private GameState[] gameStates;
    private int currentState;
    public GamePanel gamePanel;

    public static int CURRENTLEVEL;
    public static final int NUMGAMESTATES = 11;
    public static final int MENUSTATE = 0;
    public static final int GAMEOVERSTATE = 1;
    public static final int WINSTATE = 2;
    public static final int SETTINGSSTATE = 3;
    public static final int TUTORIALSTATE = 4;
    public static final int LEVEL1STATE = 5;
    public static final int LEVEL2STATE = 6;
    public static final int LEVEL3STATE = 7;
    public static final int LEVEL4STATE = 8;
    public static final int LEVEL5STATE = 9;
    public static final int LEVEL6STATE = 10;

    public static int scale;
    public static int musicVolume;
    public static int effectVolume;

    public GameStateManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        gameStates = new GameState[NUMGAMESTATES];
        CURRENTLEVEL = 0;
        musicVolume = 50;
        effectVolume = 50;

        MenuState.caricamento("BobFile");
        resizeScale(GameStateManager.scale);

        currentState = MENUSTATE;
        loadState(currentState);

        scale = GamePanel.SCALE;
    }

    private void loadState(int state)
    {
        if(state == MENUSTATE)
        {
            gameStates[state] = new MenuState(this);
        }

        if(state == SETTINGSSTATE)
        {
            gameStates[state] = new SettingsState(this);
        }

        if(state == LEVEL1STATE)
        {
            gameStates[state] = new Level1State(this);
            CURRENTLEVEL = LEVEL1STATE;
        }

        if(state == LEVEL2STATE)
        {
            gameStates[state] = new Level2State(this);
            CURRENTLEVEL = LEVEL2STATE;
        }

        if(state == LEVEL3STATE)
        {
            gameStates[state] = new Level3State(this);
            CURRENTLEVEL = LEVEL3STATE;
        }

        if(state == LEVEL4STATE)
        {
            gameStates[state] = new Level4State(this);
            CURRENTLEVEL = LEVEL4STATE;
        }

        if(state == LEVEL5STATE)
        {
            gameStates[state] = new Level5State(this);
            CURRENTLEVEL = LEVEL5STATE;
        }

        if(state == LEVEL6STATE)
        {
            gameStates[state] = new Level6State(this);
            CURRENTLEVEL = LEVEL6STATE;
        }

        if(state == GAMEOVERSTATE)
        {
            gameStates[state] = new GameOverState(this);
        }

        if(state == WINSTATE)
        {
            gameStates[state] = new WinState(this);
            CURRENTLEVEL = LEVEL1STATE;
        }

        if(state == TUTORIALSTATE)
        {
            gameStates[state] = new TutorialState(this);
            CURRENTLEVEL = LEVEL1STATE;
        }

        MenuState.salvataggio("BobFile");
    }

    private void unloadState(int state)
    {
        gameStates[state] = null;
    }

    public void setState(int state)
    {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
        //gameStates[currentState].init();
    }

    public void resizeScale(int newScale)
    {
        scale = newScale;
        GamePanel.SCALE = scale;
        gamePanel.resize();
    }

    public void update()
    {
        try
        {
            if(gameStates[currentState] != null && !gameStates[currentState].pause)
                gameStates[currentState].update();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g)
    {
        try
        {
            if(gameStates[currentState] != null)
                gameStates[currentState].draw(g);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void keyPressed(int k)
    {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k)
    {
        try
        {
            if(gameStates[currentState] != null)
                gameStates[currentState].keyReleased(k);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

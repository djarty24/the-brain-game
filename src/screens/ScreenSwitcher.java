package screens;


public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int INSTRUCTIONS = 1;
	public static final int LEVEL_SELECTOR = 2;
	public static final int LEVEL_ONE = 3;
	public static final int LEVEL_TWO = 4;
	public static final int LEVEL_THREE = 5;
	public static final int LEVEL_FOUR = 6;
	public static final int WIN_SCREEN = 7;
	public static final int GAME_OVER_SCREEN = 8;
	
	public void switchScreen(int i);
}

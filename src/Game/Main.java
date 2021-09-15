package Game;

import GUI.MyFrame;
import GUI.Menu;
import Data.Loader;

public class Main {
	public static void main(String[] agrs) {
		MyFrame frame = new MyFrame();
		Loader loader = new Loader(frame);
		Menu menu = new Menu(frame,loader);
	}
}
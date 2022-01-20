package application.game;

import java.util.Random;

public class Encount {
		Random Ran = new Random();
		
		
		public boolean GenerateNum() {
			 if(5<=Ran.nextInt(10)){
				return true;
			}
				return false;
		
		}
		

		
		
}

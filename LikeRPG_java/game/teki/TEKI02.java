package application.game.teki;

import application.game.Enemy;

public class TEKI02 extends Enemy{
		int NameID =1;
		String NameOfEnemy = "ちょっと危ないやつ";
		int HP = 15;
		int ATC = 3;
		int EXP = 5;
		
	
	
	
	public TEKI02() {
		
		super.setting(NameID,HP,ATC,EXP,NameOfEnemy);
		
	}
	
	

}

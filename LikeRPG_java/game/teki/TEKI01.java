package application.game.teki;

import application.game.Enemy;

public class TEKI01 extends Enemy{
		int NameID =1;
		String NameOfEnemy = "黄色のあくま";
		int HP = 10;
		int ATC = 2;
		int EXP = 5;
		
	
	
	
	public TEKI01() {
		
		super.setting(NameID,HP,ATC,EXP,NameOfEnemy);
		
	}
	
	

}

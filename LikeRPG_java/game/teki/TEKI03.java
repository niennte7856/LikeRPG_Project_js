package application.game.teki;

import application.game.Enemy;

public class TEKI03 extends Enemy{
		int NameID =1;
		String NameOfEnemy = "わるいうさぎ";
		int HP = 10;
		int ATC = 5;
		int EXP = 5;
		
	
	
	
	public TEKI03() {
		
		super.setting(NameID,HP,ATC,EXP,NameOfEnemy);
		
	}
	
	

}

package application.game;

public class Enemy {
	

	int NameID;
	String NameOfEnemy;
	int HP;
	int ATC;
	int EXP;
	
	
	public void setting(int a,int b,int c,int d,String e) {
		this.NameID = a;
		this.HP=b;
		this.ATC=c;
		this.EXP=d;
		this.NameOfEnemy=e;
		
	}
	
	
	public int EnemyATC(int x) {
		
		x = x-ATC;
		//if ( x <=0) {
			//System.out.println("YOU LOSE");
		//	endofgame();
		//}
		return x;
		
	}
	

	public int GetHP() {
		return HP;
	}
	public void SetHP(int x) {
		if(x<=0)x=0;
		this.HP=x;
	}
	
	public int GetATC() {
		return ATC;
	}
	public String getName() {
		return NameOfEnemy;
	}

}

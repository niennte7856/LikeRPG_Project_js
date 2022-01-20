package application.game;

	public class Hero{

	String Name="HERO";
	int HP =10;
	int HP_MAX=10;
	int LV=1;
	int ATC=5;
	int total_EXP=0;
	int advance_EXP=10;
	
	
	
	public int HeroATC(int x) {
		
		
		x=x-ATC;
		return x;
	}
	
	public void calc(int EXPOfEnemy) {
		this.total_EXP+=EXPOfEnemy;
		if(total_EXP>=advance_EXP) {
			
		}
		
	}
	
	public int getHP() {
		return HP;
	}
	public void SetHP(int x) {
		if(x<=0){
			x=0;
		}
		this.HP=x;
	}
	
	public int getATC() {
		return ATC;
	}
	
	
	
}


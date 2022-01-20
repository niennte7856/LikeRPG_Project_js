package application.game;

public class message {
	
	
	public String hero_atk(int x,String y) {
		
		return "勇者の攻撃!!"+y+"に"+x+"のダメージ!!";
		
	}
	
	public String enemy_atk(int x,String y) {
		
		return y+"の攻撃！！\n" + "勇者は"+x+"のダメージを受けた！！";
		
	}
	
	
	public String Item() {
		return "アイテムを持っていません";
		
	}
	
	
	public String Sleep() {
		return "こんなところで寝て大丈夫？";
		
	}
	
	
	public String Run() {
		return "逃げられそうにない";
		
	}
	
	public String win() {
		
		return "勇者は敵を倒した";
		
		
	}
	
	public String lose() {
		
		return "勇者はやられてしまった";
		
	}
	

}

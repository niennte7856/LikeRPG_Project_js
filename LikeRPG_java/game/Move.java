package application.game;

public class Move {
	int newx,newy;
	
	
	public int[] Idou(int x,int y,int n) {
			newx = x;
			newy = y;
			int[] array= {newx,newy};
		// 1 ue 2 sita 3 hidari 4 migi
		switch(n) {
		case 1 : newx = newx -1;
			if(newx== -1) {newx = 0;}
			break;
		case 2 : newx = newx+1;
			if(newx== 5) {newx = 4;}
			break;
		case 3 : newy = newy-1;
			if(newy== -1) {newy = 0;}
			break;
		case 4 : newy = newy+1;
			if(newy==5) {newy = 4;}
			break;
		}
		array[0]=newx;
		array[1]=newy;
		return array;
		
	}
	
		
		
	}



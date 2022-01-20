
package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.game.Enemy;
import application.game.Hero;
import application.game.Move;
import application.game.message;
import application.game.teki.TEKI01;
import application.game.teki.TEKI02;
import application.game.teki.TEKI03;
import application.game.teki.TEKI04;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//aaaaa
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;




public class SampleController {

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	public ImageView[][] ImageView;
	public int Hero_locate_x=2;
	public int Hero_locate_y=2;
	public int Hero_locate_after[];
	public boolean IsItSample0=true;
	public boolean AfterEncount=false;
	private int i;
	
	
	private Random Ran = new Random();
	Move move = new Move();
	Hero hero = new Hero();
	message  message = new message();
	Enemy[] enemy = {new TEKI01(),new TEKI02(),new TEKI03(),new TEKI04()};
	private int enemy_num;
	private Button	unableButton;
	private boolean battleresult;
	 
	
	
	
	
	@FXML
	Image imagegrass = new Image(getClass().getResourceAsStream("image/grass.jpeg"));
	Image imagehero = new Image(getClass().getResourceAsStream("image/hero.jpeg"));
	Image Field = new Image(getClass().getResourceAsStream("image/Battlefield.jpeg"));
	Image lose = new Image(getClass().getResourceAsStream("image/lose.png"));
	Image[] TEKI =  {new Image(getClass().getResourceAsStream("image/TEKI01.jpeg")),
					new Image(getClass().getResourceAsStream("image/TEKI02.jpeg")),
					new Image(getClass().getResourceAsStream("image/TEKI03.jpeg")),
					new Image(getClass().getResourceAsStream("image/TEKI04.jpeg"))};
	
	
	
	//*Image Teki[] = {new Image(getClass().getResourceAsStream("TEKI01.jpeg"))};

			
	
	
	
	 @FXML
	    void initialize() {
	       
	        initialize_else();
	        
	        String filename = location.toString();
	    		
	        	if(filename.indexOf("Sample.fxml")!=-1){
	        
	        		SetFieldNameToArray();
	        		preparefield();
	        
	        		}else if(filename.indexOf("Sample2.fxml")!=-1){
	        			this.enemy_num = GenerateEnemy();        			
	        			Endbutton.setDisable(true);
	        			Enemy_field.setImage(TEKI[enemy_num]);
	        			Showtext(enemy[enemy_num].getName()+"が現れた\n 勇者はどうする？？", EventLavel);
	        			
	    		
	        		}
	    }
	

	public void Switchto1(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
		
	public void Switchto2(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sample2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	public void testmethod() {
		System.out.println("aaaaa");
	}
	

	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button button1;
    @FXML
    private Label EventLavel;
    @FXML
    private Button Endbutton;
    

    
    
    @FXML
    void onButton1Action(ActionEvent event) {
        	System.exit(0);
    	}
    @FXML
    void ButtonEnd(ActionEvent event) {
    	System.exit(0);
    }
    
    
    
    @FXML
    void Button_Up(ActionEvent event) throws IOException {
    		
 
    		if(NotSample2()) {
    		
    			Hero_locate_after=move.Idou(Hero_locate_x, Hero_locate_y,1);
    			changeImage(Hero_locate_after);
    			DiscoverEnemy(event);
    			
    			}else {
    				
    				Button_Up.setDisable(true);
    	    		ableButton();
    	    		unableButton = Button_Up;
    				
    				if(Fighting(event)) {
    				
    					afterbattle();
    					
    				}
    			
    			}
    
    	
    }
    
    @FXML
    void Button_Down(ActionEvent event) throws IOException {
    	

		if(NotSample2()) {
    	
    	Hero_locate_after=move.Idou(Hero_locate_x, Hero_locate_y,2);
    	changeImage(Hero_locate_after);
    	
    	DiscoverEnemy(event);
		} else {
			
			Button_Down.setDisable(true);
			ableButton();
			unableButton = Button_Down;
			Showtext(message.Run(),EventLavel);
			
		}
    	
    	
    }
    
    @FXML
    void Button_Left(ActionEvent event) throws IOException {
    
    	
    	if(NotSample2()) {
    		Hero_locate_after=move.Idou(Hero_locate_x, Hero_locate_y,3);
    		changeImage(Hero_locate_after);
    	
    		DiscoverEnemy(event);
    	
    	}else {
    		
    		Button_Left.setDisable(true);
    		ableButton();
    		unableButton = Button_Left;
    		
    		Showtext(message.Sleep(),EventLavel);
    		
    	}
    }
    
    @FXML
    void Button_Right(ActionEvent event) throws IOException {
		
		if(NotSample2()) {
			Hero_locate_after=move.Idou(Hero_locate_x, Hero_locate_y,4);
			changeImage(Hero_locate_after);
    	
			DiscoverEnemy(event);
		
		}else {
			
			Button_Right.setDisable(true);
			ableButton();
			unableButton = Button_Right;
			Showtext("・・・",EventLavel);
			
				
			}
    	
    }
    
    
    private boolean Fighting(ActionEvent event) {

    	
    		String x =message.hero_atk(hero.getATC(),enemy[enemy_num].getName());
    		String y = message.enemy_atk(enemy[enemy_num].GetATC(), enemy[enemy_num].getName());
    		
    		
    		enemy[enemy_num].SetHP(hero.HeroATC(enemy[enemy_num].GetHP()));
    		
    			if(0==enemy[enemy_num].GetHP()) {
    				
    				Showtext(x+"\n   \n"+message.win(),EventLavel);
    				battleresult=true;
 
    			return true;
    			
			} 
			
    		
    			hero.SetHP(enemy[enemy_num].EnemyATC(hero.getHP()));
    		
    		
    			if(0==hero.getHP()) {
    				
    				Showtext(y+"\n   \n"+message.lose(),EventLavel);
    				battleresult=false;
    				
    			return true;
    			}
    			
    			Showtext(x+"\n   \n"+y,EventLavel);
    			
    			System.out.println("hp"+hero.getHP());
    			System.out.println("atc"+hero.getATC());
    			System.out.println("enemy"+enemy[enemy_num].GetHP());

    			
    			return false;  		
 
    	}
    
    	void afterbattle() {
    		
    		if(battleresult==true) {
    			Enemy_field.setImage(Field);
    			
    			
    		}else {
    			Enemy_field.setImage(lose);
    			
    		}
    		
    		Button_Up.setDisable(true);
    		Button_Down.setDisable(true);
    		Button_Left.setDisable(true);
    		Button_Right.setDisable(true);
    		Endbutton.setDisable(false);
    		
  
    	}
    
    
    void showPerText(String showText, int i,Label label){
        label.setText(showText.substring(0, i));
    }
    
   
    
    void Showtext(String Showtext,Label label) {
    	    	
    	i = 0;
    	Timeline timeline = new Timeline(new KeyFrame(
    			new Duration(100),
    			(event) ->{ i+=1; showPerText(Showtext,i,label);}
    			));		
    			timeline.setCycleCount(Showtext.length());
    			timeline.play();
    			
    }
    
  
    
    private void changeImage(int x[]) {
    	
    	if(Hero_locate_x==x[0] && Hero_locate_y==x[1]) {
    		Showtext("これ以上端には動けないようだ", EventLavel);
    	}else {
    		
    		ImageView[x[0]][x[1]].setImage(null);
    		ImageView[x[0]][x[1]].setImage(imagehero);
    		ImageView[Hero_locate_x][Hero_locate_y].setImage(null);
    		ImageView[Hero_locate_x][Hero_locate_y].setImage(imagegrass);
    	}
    	
    	Hero_locate_x=x[0];
    	Hero_locate_y=x[1];
    	System.out.println(Hero_locate_x+""+Hero_locate_y);
    	
    	
    }
    
    public void preparefield() {
    
    	for(int x=0;x<=ImageView.length-1;x++){
    		for(int y=0;y<=ImageView[0].length-1;y++) {
    			ImageView[x][y].setImage(imagegrass);
    		}
    	}
	
    	String filename = location.toString();
    	if(filename.indexOf("Sample0.fxml")== -1){
    			ImageView[Hero_locate_x][Hero_locate_y].setImage(null);
    			ImageView[Hero_locate_x][Hero_locate_y].setImage(imagehero);
    			}
	
	}
    
    private void DiscoverEnemy(ActionEvent event) throws IOException {
    	
    	if(GenerateNum()==true) {
    		Switchto2(event);
    	}
    }
   
   
    
    public boolean GenerateNum() {
		 if(7<=Ran.nextInt(10)){
			return true;
			}
			return false;
	}
    
    private int GenerateEnemy() {
    	
    		int x= Ran.nextInt(10);
    		
    			if(9==x) {
    				return 3;
    				} else if(7<=x) {
    					return 2;
    					} else if (4<=x) {
    						return 1;
    						} else {
    							return 0;
    				}
    }
    
    private boolean NotSample2() {
    	
    	String filename = location.toString(); 	
    	
    		if(filename.indexOf("Sample2.fxml")== -1){
    			
    			return true;	
    			
    			}else{
    				
    			return false;
    			
    		}
    }
    
    
    
 
    
    private void ableButton() {
    	
    	
    		if(unableButton!=null) {
    				unableButton.setDisable(false);
    		}	
   
    }
    
    
    //
    @FXML
    private TextField textfield1;
    
    @FXML
    private Button Button_Down;
    @FXML
    private Button Button_Left;
    @FXML
    private Button Button_Right;
    @FXML
    private Button Button_Up;
    
    @FXML
    private ImageView ImageView11;
    @FXML
    private ImageView ImageView12;
    @FXML
    private ImageView ImageView13;
    @FXML
    private ImageView ImageView14;
    @FXML
    private ImageView ImageView15;
    @FXML
    private ImageView ImageView21;
    @FXML
    private ImageView ImageView22;
    @FXML
    private ImageView ImageView23;
    @FXML
    private ImageView ImageView24;
    @FXML
    private ImageView ImageView25;
    @FXML
    private ImageView ImageView31;
    @FXML
    private ImageView ImageView32;
    @FXML
    private ImageView ImageView33;
    @FXML
    private ImageView ImageView34;
    @FXML
    private ImageView ImageView35;
    @FXML
    private ImageView ImageView41;
    @FXML
    private ImageView ImageView42;
    @FXML
    private ImageView ImageView43;
    @FXML
    private ImageView ImageView44;
    @FXML
    private ImageView ImageView45;
    @FXML
    private ImageView ImageView51;
    @FXML
    private ImageView ImageView52;
    @FXML
    private ImageView ImageView53;
    @FXML
    private ImageView ImageView54;
    @FXML
    private ImageView ImageView55;
    @FXML
    private ImageView Enemy_field;


    
    	void SetFieldNameToArray() {
    		
    		ImageView=null;
            ImageView = new ImageView[5][5];
            ImageView[0][0] = ImageView11;
            ImageView[0][1] = ImageView12;
            ImageView[0][2] = ImageView13;
            ImageView[0][3] = ImageView14;  
            ImageView[0][4] = ImageView15;
            ImageView[1][0] = ImageView21;
            ImageView[1][1] = ImageView22;
            ImageView[1][2] = ImageView23;
            ImageView[1][3] = ImageView24;
            ImageView[1][4] = ImageView25;
            ImageView[2][0] = ImageView31;
            ImageView[2][1] = ImageView32;
            ImageView[2][2] = ImageView33;
            ImageView[2][3] = ImageView34;
            ImageView[2][4] = ImageView35;
            ImageView[3][0] = ImageView41;
            ImageView[3][1] = ImageView42;
            ImageView[3][2] = ImageView43;
            ImageView[3][3] = ImageView44;
            ImageView[3][4] = ImageView45;
            ImageView[4][0] = ImageView51;
            ImageView[4][1] = ImageView52;
            ImageView[4][2] = ImageView53;
            ImageView[4][3] = ImageView54;
            ImageView[4][4] = ImageView55;
    		
    	}

    
    	void initialize_else(){
    			assert EventLavel != null : "fx:id=\"EventLavel\" was not injected: check your FXML file 'Sample.fxml'.";
    
    			assert Button_Down != null : "fx:id=\"Button_Down\" was not injected: check your FXML file 'Sample0.fxml'.";
    	        assert Button_Left != null : "fx:id=\"Button_Left\" was not injected: check your FXML file 'Sample0.fxml'.";
    	        assert Button_Right != null : "fx:id=\"Button_Right\" was not injected: check your FXML file 'Sample0.fxml'.";
    	        assert Button_Up != null : "fx:id=\"Button_Up\" was not injected: check your FXML file 'Sample0.fxml'.";
    	        assert Endbutton != null : "fx:id=\"Endbutton\" was not injected: check your FXML file 'Sample2.fxml'.";
    	        assert ImageView11 != null : "fx:id=\"ImageView11\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView12 != null : "fx:id=\"ImageView12\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView13 != null : "fx:id=\"ImageView13\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView14 != null : "fx:id=\"ImageView14\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView15 != null : "fx:id=\"ImageView15\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView21 != null : "fx:id=\"ImageView21\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView22 != null : "fx:id=\"ImageView22\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView23 != null : "fx:id=\"ImageView23\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView24 != null : "fx:id=\"ImageView24\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView25 != null : "fx:id=\"ImageView25\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView31 != null : "fx:id=\"ImageView31\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView32 != null : "fx:id=\"ImageView32\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView33 != null : "fx:id=\"ImageView33\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView34 != null : "fx:id=\"ImageView34\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView35 != null : "fx:id=\"ImageView35\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView41 != null : "fx:id=\"ImageView41\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView42 != null : "fx:id=\"ImageView42\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView43 != null : "fx:id=\"ImageView43\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView44 != null : "fx:id=\"ImageView44\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView45 != null : "fx:id=\"ImageView45\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView51 != null : "fx:id=\"ImageView51\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView52 != null : "fx:id=\"ImageView52\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView53 != null : "fx:id=\"ImageView53\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView54 != null : "fx:id=\"ImageView54\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert ImageView55 != null : "fx:id=\"ImageView55\" was not injected: check your FXML file 'Sample.fxml'.";
    	        assert Enemy_field != null : "fx:id=\"Enemy_field\" was not injected: check your FXML file 'Sample2.fxml'.";
    	        
    	}
    	


  
    
}
    	
    
 
    

    

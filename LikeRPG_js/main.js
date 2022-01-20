'use strict'


const Display_Init = 0;   //初期画面（フィールド画面）
const Display_Field=0;    //フィールド画面
const Display_Enemy=1;    //エンカウント画面
const Display_Battle=2;   //バトル画面（エンカウント画面と同じ）
const Display_After=3;    //リザルト画面


//HTMLのcanvasにに設定したID
const ca = document.getElementById("main");
const g = ca.getContext("2d");




const hero_x_init =2;     //勇者初期位置X座標
const hero_y_init =1;     //hero's initial location y


const FONT ="15px Meiryo";
const MSGBOX_1_X=30;
const MSGBOX_1_Y=270;
const MSGBOX_2_X=30;
const MSGBOX_2_Y=295;
const MSGBOX_3_X=13;

//Hero情報
const Hero_HP_init=20;
const Hero_ATK_init=5;

//Enemy情報　４体
const Enemy_NAME_init = ["きいろのあくま","危ないやつ","危険なやつ","大魔王"];
const Enemy_HP_init = [10,10,15,999];  //4体分の敵のHP
const Enemy_ATK_init = [2,5,3,999];    //4体分の敵の攻撃力


//let count_timer=0;

//let x =0;

//イメージ情報
let imgField;   //フィールド地形
let imgHero;    //フィールド地形＋勇者画像
let imgMsgbox;  //
let imgWin;     //勝利時の画像
let imgLose;    //敗北時画像

let imgEnemy=[];  //敵の画像格納配列
let imgEnemy_1;   //1~4を配列に格納する
let imgEnemy_2;
let imgEnemy_3;
let imgEnemy_4;
let numEnemy;     //配列の要素番号


let msgBox_1=null;  //メッセージ表示変数
let msgBox_2=null;  //基本的に２行で表示する

let msgBox_3="";    //コマンド選択用の矢印
let msgBox_pointer=270  //コマンド選択用の矢印の初期位置

let enemy_name;
let enemy_hp;
let enemy_atk;
let hero_hp;
let hero_atk;


//バトル中の画面管理変数
let cmd_battle=false;
let cmd_status=false;

//バトル終了フラグ
let ended_battle=false;

//バトル結果
let result_battle=false;



let display_location = Display_Init;
let hero_x =2;
let hero_y =2;


//フィールド描写
function drawField(){

      for(let x =0;x<=4;x++){

          for(let y=0;y<=4;y++){

              if(x===hero_x && y===hero_y){
                  g.drawImage(imgHero, x*80,y*48);

              }else{
                  g.drawImage(imgField,x*80,y*48);
                }

              }

          }

    msgBox_1="方向キーでフィールドを移動してみてね";
    msgBox_2="どこでも移動できるよ"
}


//敵出現時
  function drawEnemy()
{
    g.drawImage(imgEnemy [numEnemy],0,0);


    msgBox_1=enemy_name+"が現れた!";
    msgBox_2="Enterを押してね";


    //勇者情報を初期化
    hero_hp=Hero_HP_init;
    hero_atk=Hero_ATK_init;

    //敵情報を初期化
    enemy_name=Enemy_NAME_init[numEnemy];
    enemy_hp=Enemy_HP_init[numEnemy];
    enemy_atk=Enemy_ATK_init[numEnemy];
}


  function drawBattle()
{


      if(msgBox_pointer===270 && cmd_battle===true && cmd_status===true){

          hero_hp-=enemy_atk;
          enemy_hp-=hero_atk;

          msgBox_1="勇者の攻撃!!"+enemy_name+"に"+hero_atk+"のダメージ!!";
          msgBox_2="敵の攻撃!! 勇者に"+enemy_atk+"のダメージ!!";
          msgBox_3="";


            cmd_battle=false;


        }else if(msgBox_pointer===295 && cmd_battle===true && cmd_status===true){
            msgBox_1="逃げられそうにない";
            msgBox_2="";
            msgBox_3="";
            cmd_battle=false;

        }

        //どちらかのHPが０だった場合
          if(hero_hp<=0 || enemy_hp<=0){

                if (hero_hp<=0){
                    result_battle=false;
                }else{
                    result_battle=true;
                }

                  ended_battle=true;
}

}




  function drawResult()
{

      if(result_battle){

        g.drawImage(imgWin,0,0);
        msgBox_1="勇者は"+enemy_name+"を倒した!!"
        msgBox_2="闘いに勝利した!!"
        msgBox_3="";

      }else{

        g.drawImage(imgLose,0,0);
        msgBox_1="勇者は負けてしまった"
        msgBox_2="";
        msgBox_3="";

      }
}


  function drawMsg(){

    g.font = FONT;
    g.drawImage(imgMsgbox,0,240);
    g.fillText(msgBox_1,MSGBOX_1_X,MSGBOX_1_Y);
    g.fillText(msgBox_2,MSGBOX_2_X,MSGBOX_2_Y);
    g.fillText(msgBox_3,MSGBOX_3_X,msgBox_pointer);

  }


//バトル情報を初期化
  function reset()
  {
      //バトルで使用する関数を全てリセット
      ended_battle=false;
      result_battle=false;
      cmd_battle=false;
      cmd_status=false;
      msgBox_pointer=270;

  }


//
function gameTimer()
{
  //count_timer++;


  //画面によってい処理を分岐
  switch (display_location) {
    case 0:
      drawField();
      break;
    case 1:
      drawEnemy();
      break;
    case 2:
      drawBattle();
      break;
    case 3:
      drawResult();
      break;
    }

    drawMsg();




//ボタンが押された時のイベント
  window.onkeydown = function(keyev)
{
  let k = keyev.key;

  switch (display_location) {

    //フィールド画面
    case 0:

        if(k==="ArrowUp" || k==="ArrowDown" || k==="ArrowLeft" || k==="ArrowRight"){

            if(k==="ArrowLeft") hero_x--;
            if(k==="ArrowUp") hero_y--;
            if(k==="ArrowRight") hero_x++;
            if(k==="ArrowDown") hero_y++;

              //画面をはみ出たら移動せずにエンカウント抽選もしない
              if(hero_x<0){
                  hero_x=0;
                  return;

                }else if(hero_x===5){
                  hero_x=4;
                  return;

                }else if(hero_y<0){
                  hero_y=0;
                  return;

                }else if(hero_y===5){
                  hero_y=4;
                  return;
                }

                //エンカウント抽選
                if(6<Math.floor( Math.random() * 11 )) {

                //画面切り替え
                display_location=Display_Enemy;

                //Enemy抽選
                numEnemy=Math.floor( Math.random() * 4 );
              }
            }

      break;

      //エンカウント時
      case 1:

          if(k==="Enter") {
            msgBox_1 ="  たたかう";
            msgBox_2 ="  にげる";
            msgBox_3="▶︎";

            //画面切り替え;
            display_location=Display_Battle;
          }

          break;

      //バトル中
      case 2:

                  //Enter かつバトル終了後
                if(k==="Enter" && ended_battle===true){

                  //画面切り替え
                  display_location=Display_After;
                  return;



                      //Enterかつバトル終了かつ、コマンド選択中
                      }else if(k==="Enter" && cmd_battle===false && cmd_status===false){

                      cmd_battle=true;
                      cmd_status=true;

                      //Enterかつ、”たたかう”または”にげる”コマンド実行後のメッセージ表示中
                      }else if (k==="Enter" && cmd_battle===false && cmd_status===true){

                        msgBox_1 ="  たたかう";
                        msgBox_2 ="  にげる";
                        msgBox_3="▶︎";

                        cmd_status=false;

                      }

                      //上下キー入力かつ、コマンド選択中
                      if(cmd_status===false &&(k==="ArrowUp" || k==="ArrowDown"))
                          switch (msgBox_pointer) {
                            case 270:
                              msgBox_pointer=295;
                              break;
                            case 295:
                              msgBox_pointer=270;
                              break;
                    }
            break;

      //バトル後
      case 3:
                if(k==="Enter"){
                    display_location = Display_Field;
                    reset();
                    return;

                }



              }






  }




//console.log(count_timer);

}


window.onload = function()
{

//  const ca = document.getElementById("main");
//  const g = ca.getContext("2d");

  imgField = new Image(); imgField.src = "img/grass.jpeg";
  imgHero = new Image(); imgHero.src = "img/hero.jpeg";
  imgMsgbox = new Image(); imgMsgbox.src = "img/msgbox.png";
  imgWin = new Image(); imgWin.src = "img/win.jpeg";
  imgLose = new Image(); imgLose.src = "img/lose.png";
  imgEnemy_1 = new Image(); imgEnemy_1.src = "img/TEKI01.jpeg";
  imgEnemy_2 = new Image(); imgEnemy_2.src = "img/TEKI02.jpeg";
  imgEnemy_3 = new Image(); imgEnemy_3.src = "img/TEKI03.jpeg";
  imgEnemy_4 = new Image(); imgEnemy_4.src = "img/TEKI04.jpeg";
  imgEnemy=[imgEnemy_1,imgEnemy_2,imgEnemy_3,imgEnemy_4];



  setInterval( function() {gameTimer()},60);


}

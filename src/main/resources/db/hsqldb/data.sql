-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
INSERT INTO users(username,password,enabled) VALUES ('pacoJeje','paquito',TRUE);
INSERT INTO players(id,first_name,last_name,description,username,points) VALUES ('1','Paco','Lopez','jugador gracioso','pacoJeje',0);
INSERT INTO oca_game(id,name,players,reward,in_game) VALUES ('1','LopezGame','2','10',false);
INSERT INTO oca_board(id,background,width,height) VALUES ('1','',800,800);
INSERT INTO oca_turn(id,dice,player_id,turn,i) VALUES ('1','0',1,'0','0');
INSERT INTO oca_piece(id,player_id,board_id,position,penalization) VALUES('1',1,1,4,0);

INSERT INTO parchis_board(id,background,width,height) VALUES ('1','',800,800);
INSERT INTO square(id,color,position,board_id) VALUES (0,0,104,1);
INSERT INTO parchis_piece(id,color,player_id,can_move,board_id,in_start,square_id,name) VALUES (1,0,1,true,1,true,0,'Ficha 1');
INSERT INTO parchis_game(id,name,players,reward,in_game) VALUES ('1','LopezGame','2','10',false);
INSERT INTO parchis_turn(id,dice1,dice2,dices_available,is_throwed,player_id,turn,i,repeat_turn) VALUES (1,0,0,0,false,1,0,0,false);

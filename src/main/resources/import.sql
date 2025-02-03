
-- Insert game lists
INSERT INTO TB_GAME_LIST (name) VALUES ('Adventure and RPG');
INSERT INTO TB_GAME_LIST (name) VALUES ('Platform Games');

INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Mass Effect Trilogy', 4.8, 2012, 'Role-playing (RPG), Shooter', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png', 'Embark on an epic journey with Commander Shepard in this space-faring RPG series.', 'Join Commander Shepard as they battle against an ancient alien threat while making choices that will shape the galaxy. The trilogy blends sci-fi action with deep character interaction and moral decisions. Experience an unforgettable adventure across multiple planets with combat, diplomacy, and exploration.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Red Dead Redemption 2', 4.7, 2018, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/2.png', 'Step into the wild west in this critically acclaimed open-world adventure game.', 'In Red Dead Redemption 2, you play as Arthur Morgan, a member of the Van der Linde gang, exploring the American frontier in a sprawling open world. The game offers deep storytelling, complex characters, and immersive gameplay as you face the challenges of life in the dying days of the wild west.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('The Witcher 3: Wild Hunt', 4.7, 2014, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/3.png', 'Embark on a dark fantasy adventure as Geralt of Rivia in this award-winning RPG.', 'Play as Geralt, a monster hunter known as a Witcher, in a vast world filled with monsters, magic, and political intrigue. The Witcher 3: Wild Hunt offers a rich narrative where every decision matters, with breathtaking landscapes and challenging quests that keep you engaged for hours.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Sekiro: Shadows Die Twice', 3.8, 2019, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/4.png', 'Become a samurai in this punishing action game set in feudal Japan.','Sekiro: Shadows Die Twice challenges players with unforgiving combat and a gripping story. As a shinobi named Wolf, you must rescue your kidnapped lord and exact revenge on those who wronged you. Master the art of swordplay, stealth, and strategy to survive in a beautifully brutal world.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Ghost of Tsushima', 4.6, 2012, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/5.png', 'Fight to defend Tsushima Island in this samurai action-adventure game.', 'Ghost of Tsushima follows Jin Sakai, a samurai struggling to protect his island from a Mongol invasion. The game blends traditional samurai combat with stealth mechanics and an open-world environment, offering a visually stunning experience of feudal Japan.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Super Mario World', 4.7, 1990, 'Platform', 'Super Nintendo Entertainment System (SNES), PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/6.png', 'Join Mario and Luigi in this iconic 2D platformer to save Princess Toadstool.', 'Super Mario World introduces new abilities and power-ups as Mario sets off to rescue Princess Toadstool from Bowser. Explore diverse worlds, defeat enemies, and uncover hidden secrets in this legendary classic that redefined platform gaming.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Hollow Knight', 4.6, 2017, 'Platform', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/7.png', 'Delve into the depths of a forgotten kingdom in this dark action-platformer.', 'Hollow Knight is an atmospheric action-platformer set in the ruins of Hallownest, a kingdom plagued by corruption. As a silent knight, explore vast caverns, fight deadly enemies, and uncover secrets in this challenging and beautifully hand-drawn world.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Ori and the Blind Forest', 4, 2015, 'Platform', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/8.png', 'Embark on a heartwarming journey through a magical forest in this emotionally charged platformer.', 'Ori and the Blind Forest tells the story of a young guardian spirit and their journey through a mystical forest. With beautiful hand-drawn artwork, challenging puzzles, and an emotional narrative, Ori offers a touching experience in a world of stunning visual beauty.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Cuphead', 4.6, 2017, 'Platform', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/9.png', 'Experience vintage cartoon action in this challenging platformer with a unique art style.','Cuphead is a run-and-gun platformer with a retro 1930s cartoon aesthetic. Play as Cuphead or Mugman as they battle through whimsical worlds filled with difficult bosses and tricky platforming segments. Its tough difficulty and beautiful hand-drawn animations make it a standout title.');
INSERT INTO TB_GAME (title, score, release_year, genre, platforms, img_url, short_description, long_description) VALUES ('Sonic CD', 4, 1993, 'Platform', 'Sega CD, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/10.png', 'Join Sonic on an adventure to save the future in this classic platformer.', 'Sonic CD follows Sonic the Hedgehog as he races through time to defeat Dr. Robotnik and save the future. With time travel mechanics, new characters, and a fast-paced gameplay style, Sonic CD remains one of the iconic entries in the Sonic franchise.');

INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (1, 1, 0);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (1, 2, 1);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (1, 3, 2);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (1, 4, 3);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (1, 5, 4);

INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (2, 6, 0);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (2, 7, 1);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (2, 8, 2);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (2, 9, 3);
INSERT INTO TB_BELONGING (game_list_id, game_id, position) VALUES (2, 10, 4);
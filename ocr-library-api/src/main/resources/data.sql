
/*------------------------------------------------------------------------------------------------------------------------------------*/
/*LIBRARY_ENTITY*/
INSERT INTO LIBRARY_ENTITY (ID, ADRESS, NAME, EMAIL, PHONE_NUMBER) VALUES (1,'2 rue Marc Sangnier, 91330 Yerres', 'CEC-Yerres Médiathèque Danielle Bianu', 'bibyerres@vyvs.fr', '0169488200');


/*------------------------------------------------------------------------------------------------------------------------------------*/
/*BOOK_ENTITY*/
INSERT INTO BOOK_ENTITY (ID, QUANTITY, AUTHOR, IMAGE_LINK, PAGE_NUMBER, PUBLISHING, STATUS, TITLE, TYPE, LIBRARY_ENTITY_ID, SUMMARY) VALUES (1, 1,'Thibault Geoffray','https://zupimages.net/up/20/40/sfns.jpg',176,'Marabout','disponible','Mes recettes healthy #2','Santé',1,'Mincir grâce aux 80 nouvelles recettes de Thibault Geoffray, le coach sportif n°1 d’Instagram ! Des recettes toujours aussi simples, rapides et délicieuses et toutes les clés pour transformer sa silhouette tout en prenant soin de sa santé !
	Toasts de patate douce et œuf poché, mini burgers thon courgette, bowl veggie quinoa, douceurs glacées au peanut butter ou smoothie purple… Le secret de ses recettes ? Ultra faciles, peu d’ingrédients (seulement les meilleurs) avec une place toujours essentielle donnée aux fruits et légumes !
	Grâce à ce nouveau livre de Thibault Geoffray, commencez ou continuez votre prise en main tout en vous régalant sans jamais vous priver !');


INSERT INTO BOOK_ENTITY (ID, QUANTITY, AUTHOR, IMAGE_LINK, PAGE_NUMBER, PUBLISHING, STATUS, TITLE, TYPE, LIBRARY_ENTITY_ID, SUMMARY) VALUES (2, 4, 'Major Mouvement', 'https://zupimages.net/up/20/40/9xho.jpg', 224,'Marabout', 'disponible','MAJOR MOUVEMENT : Mes 10 clés pour un corps en bonne santé', 'Santé',1,'Mal au dos, aux genoux, surpoids, tendinites à répétition, fatigue… la sédentarité est responsable de bien des maux. Vous avez envie d’en finir avec les douleurs multiples et de retrouver un corps en bonne santé, musclé et tonique ? La clé, c’est le mouvement  ! Major Mouvement, kinésithérapeute passionné par son métier, spécialisé en thérapie manuelle, est là pour vous aider à vous reprendre en main  : déjouant les idées reçues, il vous donne les outils concrets pour vous construire un corps qui vous plaît, solide et en bonne santé !');

INSERT INTO BOOK_ENTITY (ID, QUANTITY, AUTHOR, IMAGE_LINK, PAGE_NUMBER, PUBLISHING, STATUS, TITLE, TYPE, LIBRARY_ENTITY_ID, SUMMARY) VALUES (3, 3, 'Hélène Tavelle', 'https://zupimages.net/up/20/40/uam4.jpg', 172,'Independently published','disponible','Déborah: La Rencontre Interdite','Roman',1,'Déborah a une vie rangée, sans histoires. A 40 ans, mariée et mère d une ado, elle affiche un parcours professionnel brillant. Lors d’un dépôt de plainte à l hôtel de police, un coup de foudre avec Anthony, un séduisant officier, bouleverse son existence. Hélas, tous deux doivent se voir en secret. Sa métamorphose en une femme attirante ne fait qu accroître la folie violente de ce mari dont elle a très peur. Déborah, elle, vibre minute par minute dans l attente des appels d Anthony et de leurs rendez-vous ardents. Absences, vacances, ruptures, les séparations successives font monter crescendo leur passion brûlante. Leur amour résistera-t-il à la jalousie féroce de son mari ? Hélène Tavelle a aussi publié Un Amour de Confinement, Quatre et Echappées Belles.');

INSERT INTO BOOK_ENTITY (ID, QUANTITY, AUTHOR, IMAGE_LINK, PAGE_NUMBER, PUBLISHING, STATUS, TITLE, TYPE, LIBRARY_ENTITY_ID, SUMMARY, WAITING48HDATE) VALUES (4, 1,'Hélène Tavelle', 'https://zupimages.net/up/20/40/qz7z.jpg', 301,'Independently published','disponible','Echappées Belles: Nouvelles','Roman',1,'Aujourd’hui, c’est fini, j’arrête de passer à côté de ma vie ! Les héroïnes de ces Nouvelles, regroupées sous le titre de Echappées Belles, ont toutes en commun de rompre avec leur passé, souvent du jour au lendemain, pour enfin assouvir leurs désirs. Ces ruptures se font sans compromis et débouchent sur maints rebondissements, belles promesses, rencontres étonnantes et détonantes. Ce sont des portraits sensibles qui nous jouent la mélodie d’amour sur un ton mi-grave, mi-badin. Hélène Tavelle a aussi publié Déborah - La Rencontre Interdite, Quatre, Un Amour de Confinement.', '2020-10-31T16:43:21.450');

INSERT INTO BOOK_ENTITY (ID, QUANTITY, AUTHOR, IMAGE_LINK, PAGE_NUMBER, PUBLISHING, STATUS, TITLE, TYPE, LIBRARY_ENTITY_ID, SUMMARY) VALUES (5, 3,'Lisa Jewell', 'https://zupimages.net/up/20/40/j1zm.jpg', 319,'Hauteville','disponible','On se reverra','Roman',1,'Les souvenirs, c’est comme les cadavres : tôt ou tard, ils refont surface.
Qui est cet homme assis sur la plage en pleine tempête, sur le lieu d’un crime commis vingt ans plus tôt ? Il n’a pas de nom, pas de manteau, et a perdu la mémoire. Alice prend l’inconnu sous son aile et décide de l’héberger, sans savoir qu’il va bouleverser sa vie à jamais.
Au même moment, dans la banlieue de Londres, Lily attend en vain le retour de l’homme qu’elle vient d’épouser et dont la police tarde à signaler la disparition. Parviendra-t-elle à retrouver celui pour qui elle a tout abandonné ?');

INSERT INTO BOOK_ENTITY (ID, QUANTITY, AUTHOR, IMAGE_LINK, PAGE_NUMBER, PUBLISHING, STATUS, TITLE, TYPE, LIBRARY_ENTITY_ID, SUMMARY) VALUES (6, 4,'Cyril Lignac','https://zupimages.net/up/20/40/odod.jpg', 112, 'La Martinière', 'disponible', 'Fait maison - numéro 1','Cuisine',1,'Envie d une cuisine maison savoureuse et rapide ? En direct de sa cuisine, Cyril Lignac te propose 45 recettes salées et/ou sucrées pour mettre un peu de peps dans ton quotidien.
Un risotto aux coquillettes, un poisson au four à l huile vierge et aux petits légumes ou encore une fabuleuse tarte aux fraises ou des petits pots de crème à la vanille... Tu vas te régaler en toute simplicité !');

INSERT INTO BOOK_ENTITY (ID, QUANTITY, AUTHOR, IMAGE_LINK, PAGE_NUMBER, PUBLISHING, STATUS, TITLE, TYPE, LIBRARY_ENTITY_ID, SUMMARY) VALUES (7, 1, 'Lilian Almeras', 'https://zupimages.net/up/20/40/sz9q.jpg', 180, 'AMPHORA', 'disponible', 'Rugby Combat System', 'Sport',1, 'La violence dans le rugby et les graves traumatismes qui peuvent en découler apparaissent aujourd hui comme le problème central de ce sport. Les affrontements directs et les percussions sont en effet devenus des actions prépondérantes du jeu moderne. Ces dernières années, la puissance des chocs, aux conséquences parfois dramatiques, se retrouve accrue par la mise en confrontation d athlètes aux gabarits et poids sans commune mesure. De plus, le jeu est souvent devenu stéréotypé, ennuyeux et laborieux, démuni de tout l esprit créatif qui l animait auparavant.');



/*------------------------------------------------------------------------------------------------------------------------------------*/
/*USER_ENTITY*/
INSERT INTO USER_ENTITY (ID, ROLE, ADRESS, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USER_NAME) VALUES (1, 'USER','50 rue de Corebeil, 91250 Saintry-sur-Seine', 'benoitnerin@gmail.com', 'Benoît', 'Nérin', '$2a$10$iia25oC57WJLKGUoO49Epu2uC6UZp.CnHyVlK7cFfClXZ9vbz07Y6','0671406428','Benoit');
INSERT INTO USER_ENTITY (ID, ROLE, ADRESS, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USER_NAME) VALUES (2, 'USER','50 rue de Corebeil, 91250 Saintry-sur-Seine', 'benoitnerin@gmail.com', 'Benoît', 'Nérin', '$2a$10$iia25oC57WJLKGUoO49Epu2uC6UZp.CnHyVlK7cFfClXZ9vbz07Y6','0671406428','Benoit2');
INSERT INTO USER_ENTITY (ID, ROLE, ADRESS, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USER_NAME) VALUES (3, 'USER','50 rue de Corebeil, 91250 Saintry-sur-Seine', 'ophelienerin@yahoo.fr', 'Ophélie', 'Nérin', '$2a$10$iia25oC57WJLKGUoO49Epu2uC6UZp.CnHyVlK7cFfClXZ9vbz07Y6', '0683740067','Ophelie');
INSERT INTO USER_ENTITY (ID, ROLE, ADRESS, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USER_NAME) VALUES (4, 'USER','', '', 'Invité', 'Invité', '$2a$10$PA6nx5C2vOYFiuTotoHsbudiMf31pK9Iio5giuXHmNJTScp0NTkRW', '','invite');

/*------------------------------------------------------------------------------------------------------------------------------------*/
/*LOAN_ENTITY*/
INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (1, 'En cours ou demande de prêt', '2020-09-14T16:43:21.450', '', '2020-08-14T16:43:21.450',6,1);
--INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (1, 'En cours de prêt', '2020-10-31T16:43:21.450', '', '2020-09-27T16:43:21.450',5,1);
--INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (3, 'En cours de prêt', '2020-10-31T16:43:21.450', '', '2020-09-27T16:43:21.450',1,1);
--
--INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (2, 'En cours de prêt', '2020-10-31T17:55:20.180', '', '2020-09-30T17:55:20.180',3,2);
--INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (5, 'En cours de prêt', '2020-10-31T17:55:20.180', '', '2020-09-30T17:55:20.180',2,2);
--
--INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (6, 'En cours de prêt', '2020-10-31T17:55:20.180', '', '2020-09-30T17:55:20.180',4,3);
--INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (7, 'En cours de prêt', '2020-10-31T17:55:20.180', '', '2020-09-30T17:55:20.180',5,3);
--INSERT INTO LOAN_ENTITY (ID, STATUS, END_BORROWING_DATE, PROLONGATION_DATE, START_BORROWING_DATE, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (8, 'En cours de prêt', '2020-10-31T17:55:20.180', '', '2020-09-30T17:55:20.180',6,3);

/*-----------------------------*/
--INSERT INTO BOOK_ENTITY_USER_WAITING_LINE (BOOK_ENTITY_ID, USER_WAITING_LINE) VALUES (4, 'Ophelie');
--INSERT INTO RESERVATION_REQUEST_ENTITY (ID, STARTING_DATE, STATUS, BOOK_ENTITY_ID, USER_ENTITY_ID) VALUES (1, '2020-10-31T17:55:20.180', 'Demande de réservation en cours', 4, 3);
/*-----------------------------*/
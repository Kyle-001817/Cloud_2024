create table utilisateur (
    id_utilisateur serial primary key,
    nom_utilisateur varchar(100),
    prenom_utilisateur varchar(100),
    ville VARCHAR(50),
    contact VARCHAR(50),
    mail VARCHAR(100),
    mdp varchar(100)
);
insert into utilisateur(nom_utilisateur, prenom_utilisateur, ville, contact, mail, mdp) values ('Rakoto','Jean','Antananarivo','+261 34 01 437 24','jean@gmail.com','jean');

create table admin (
    id_admin serial primary key,
    nom_admin varchar(50),
    email varchar(50),
    mdp varchar(50)
);

create table categorie (
    id_categorie serial primary key,
    nom_categorie varchar(50)
);
insert into categorie(nom_categorie) values('Voiture de sport');
insert into categorie(nom_categorie) values('Poids lourd');
insert into categorie(nom_categorie) values('Voiture legere');

create table marque (
    id_marque serial primary key,
    nom_marque varchar(50)
);

create table etat (
    id_etat serial primary key,
    nom_etat varchar(50)
);

create table entretien (
    id_entretien serial primary key,
    changement varchar(50),
    date_entretien date
);

create table detail (
    id_detail serial primary key,
    nom_modele varchar(50),
    puissance float,
    carburant varchar(50),
    nom_moteur varchar(50),
    id_etat int references etat(id_etat),
    id_entretien int references entretien(id_entretien),
    prix decimal,
    commission float,
    kilometrage int,
    boite_vitesse varchar(50),
    date_sortie date,
    commentaire varchar(100)
);

create table photo (
    id_photo serial primary key,
    lien_photo varchar(100)
);

create table voiture (
    id_voiture serial primary key,
    id_categorie int references categorie(id_categorie),
    id_marque int references marque(id_marque),
    id_detail int references detail(id_detail),
    id_photo int references photo(id_photo),
    disponibilite int
);

create table annonce (
    id_annonce serial primary key,
    id_utilisateur int references utilisateur(id_utilisateur),
    id_voiture int references voiture(id_voiture),
    date_annonce timestamp,
    accepte_annonce int
);

create table interaction (
    id_interaction serial primary key,
    id_annonce int references annonce(id_annonce),
    id_utilisateur int references utilisateur(id_utilisateur),
    favoris int
);
create table ville
(
    id_ville serial primary key,
    nom varchar(50)
);
insert into ville(nom) values('Antananarivo');
insert into ville(nom) values('Majunga');
insert into ville(nom) values('Fianarantsoa');
insert into ville(nom) values('Tamatave');

INSERT INTO marque(nom_marque) VALUES('BMW');
INSERT INTO marque(nom_marque) VALUES('Toyota');
INSERT INTO marque(nom_marque) VALUES('Audi');
INSERT INTO marque(nom_marque) VALUES('Mercedes');
INSERT INTO marque(nom_marque) VALUES('Mazda');
INSERT INTO marque(nom_marque) VALUES('Ford');
INSERT INTO marque(nom_marque) VALUES('Kia');
INSERT INTO marque(nom_marque) VALUES('Jeep');
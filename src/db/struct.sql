CREATE DATABASE IF NOT EXISTS tho4_atd;
USE tho4_atd;

DROP TABLE IF EXISTS ParkeerMoment;
DROP TABLE IF EXISTS Parkeerplaats;
DROP TABLE IF EXISTS GebruikteOnderdelen;
DROP TABLE IF EXISTS WerkzaamheidOnderdelen;
DROP TABLE IF EXISTS Werkzaamheid;
DROP TABLE IF EXISTS Onderhoud;
DROP TABLE IF EXISTS Voorraad;
DROP TABLE IF EXISTS Onderdeel;
DROP TABLE IF EXISTS Leverancier;
DROP TABLE IF EXISTS Auto;
DROP TABLE IF EXISTS Monteur;
DROP TABLE IF EXISTS Klant;
DROP TABLE IF EXISTS Account;

CREATE TABLE Account (
  account_id INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL UNIQUE ,
  password VARCHAR(256) NOT NULL,
  creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  modification_time DATETIME ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (account_id)
);

CREATE TABLE Klant (
  klant_id INT(11) NOT NULL AUTO_INCREMENT,
  account_id INT(11) NOT NULL,
  voornaam VARCHAR(64) NOT NULL,
  achternaam VARCHAR(64) NOT NULL,
  adres VARCHAR(64),
  plaats VARCHAR(64),
  telnr VARCHAR(10),
  PRIMARY KEY (klant_id),
  FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE Monteur (
  monteur_id INT(11) NOT NULL AUTO_INCREMENT,
  account_id INT(11) NOT NULL,
  voornaam VARCHAR(64) NOT NULL,
  achternaam VARCHAR(64) NOT NULL,
  adres VARCHAR(64),
  plaats VARCHAR(64),
  telnr VARCHAR(10),
  salaris DECIMAL(10,2),
  PRIMARY KEY (monteur_id),
  FOREIGN KEY (account_id) REFERENCES Account(account_id)
);
CREATE TABLE Auto (
  auto_id INT(11) NOT NULL AUTO_INCREMENT,
  klant_id INT(11) NOT NULL,
  merk VARCHAR(64) NOT NULL,
  type VARCHAR(64) NOT NULL,
  kenteken VARCHAR(10) NOT NULL,
  aantalkm INT(10),
  PRIMARY KEY (auto_id),
  FOREIGN KEY (klant_id) REFERENCES Klant(klant_id)
);

CREATE TABLE Leverancier (
  leverancier_id INT(11) NOT NULL AUTO_INCREMENT,
  naam VARCHAR(64) NOT NULL,
  besteladres VARCHAR(64) NOT NULL,
  PRIMARY KEY (leverancier_id)
);

CREATE TABLE Onderdeel (
  onderdeel_id INT(11) NOT NULL AUTO_INCREMENT,
  artikelnr VARCHAR(64) NOT NULL,
  prijs DECIMAL(10,2) NOT NULL ,
  merk VARCHAR(64),
  type VARCHAR(64),
  PRIMARY KEY (onderdeel_id)
);

CREATE TABLE Voorraad (
  onderdeel_id INT(11) NOT NULL,
  leverancier_id INT(11) NOT NULL,
  aantal INT(4) NOT NULL,
  minaantal INT(4) NOT NULL,
  PRIMARY KEY (onderdeel_id, leverancier_id),
  FOREIGN KEY (onderdeel_id) REFERENCES Onderdeel(onderdeel_id),
  FOREIGN KEY (leverancier_id) REFERENCES Leverancier(leverancier_id)
);

CREATE TABLE Onderhoud (
  onderhoud_id INT(11) NOT NULL AUTO_INCREMENT,
  monteur_id INT(11) NOT NULL,
  auto_id INT(11) NOT NULL,
  begindatum DATE NOT NULL,
  einddatum DATE NOT NULL,
  beschrijving VARCHAR(256),
  PRIMARY KEY (onderhoud_id),
  FOREIGN KEY (monteur_id) REFERENCES Monteur(monteur_id),
  FOREIGN KEY (auto_id) REFERENCES Auto(auto_id)
);

CREATE TABLE Werkzaamheid (
  werkzaamheid_id INT(11) NOT NULL AUTO_INCREMENT,
  onderhoud_id INT(11) NOT NULL,
  beschrijving VARCHAR(64) NOT NULL,
  PRIMARY KEY (werkzaamheid_id),
  FOREIGN KEY (onderhoud_id) REFERENCES Onderhoud(onderhoud_id)
);

CREATE TABLE GebruikteOnderdelen (
  werkzaamheid_id INT(11) NOT NULL,
  onderdeel_id INT(11) NOT NULL,
  aantal INT(4) NOT NULL ,
  PRIMARY KEY (werkzaamheid_id, onderdeel_id),
  FOREIGN KEY (werkzaamheid_id) REFERENCES Werkzaamheid(werkzaamheid_id),
  FOREIGN KEY (onderdeel_id) REFERENCES Onderdeel(onderdeel_id)
);

CREATE TABLE Parkeerplaats (
  parkeerplaats_id INT(11) NOT NULL AUTO_INCREMENT,
  uurprijs DECIMAL(10, 2) NOT NULL,
  beschikbaar BOOLEAN NOT NULL,
  PRIMARY KEY (parkeerplaats_id)
);

CREATE TABLE ParkeerMoment (
  parkeerplaats_id INT(11) NOT NULL,
  auto_id INT(11) NOT NULL,
  begindatum DATETIME NOT NULL,
  einddatum DATETIME NOT NULL,
  PRIMARY KEY (parkeerplaats_id, auto_id),
  FOREIGN KEY (parkeerplaats_id) REFERENCES Parkeerplaats(parkeerplaats_id),
  FOREIGN KEY (auto_id) REFERENCES Auto(auto_id)
);

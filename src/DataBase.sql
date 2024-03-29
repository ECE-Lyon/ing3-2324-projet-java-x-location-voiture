DROP DATABASE IF EXISTS projetx ;
CREATE DATABASE IF NOT EXISTS projetx;

USE projetx;

-- Table Voiture
CREATE TABLE IF NOT EXISTS Voiture (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marque VARCHAR(100),
    modele VARCHAR(100),
    annee INT,
    couleur VARCHAR(50)
);

-- Table Client
CREATE TABLE IF NOT EXISTS Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    adresse VARCHAR(255),
    email VARCHAR(100),
    telephone VARCHAR(20)
);

-- Table Employé
CREATE TABLE IF NOT EXISTS Employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    poste VARCHAR(100),
    salaire DECIMAL(10, 2)
);

INSERT INTO Voiture (marque, modele, annee, couleur)
VALUES ('Renault', 'Clio5', 2018, 'bleu');


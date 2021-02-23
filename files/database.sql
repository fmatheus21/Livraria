CREATE DATABASE  IF NOT EXISTS `livraria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `livraria`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: livraria
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_pessoa_fisica` int NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `data_alteracao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `id_pessoa_fisica_UNIQUE` (`id_pessoa_fisica`),
  KEY `fk_pessoa_idx` (`id_pessoa_fisica`),
  CONSTRAINT `fk_pessoafisica_autor` FOREIGN KEY (`id_pessoa_fisica`) REFERENCES `pessoa_fisica` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,1,'2021-02-20 21:21:16','2021-02-20 21:21:16'),(2,2,'2021-02-20 21:30:55','2021-02-20 21:30:55'),(4,4,'2021-02-22 21:58:16','2021-02-22 21:58:16');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editora`
--

DROP TABLE IF EXISTS `editora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editora` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_pessoa_juridica` int NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `data_alteracao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `id_pessoa_juridica_UNIQUE` (`id_pessoa_juridica`),
  KEY `fk_pessoa__idx` (`id_pessoa_juridica`),
  CONSTRAINT `fk_pessoa_juridica_` FOREIGN KEY (`id_pessoa_juridica`) REFERENCES `pessoa_juridica` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editora`
--

LOCK TABLES `editora` WRITE;
/*!40000 ALTER TABLE `editora` DISABLE KEYS */;
INSERT INTO `editora` VALUES (2,2,'2021-02-22 01:29:11','2021-02-22 01:29:11'),(3,7,'2021-02-22 22:00:36','2021-02-22 22:00:36');
/*!40000 ALTER TABLE `editora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_editora` int NOT NULL,
  `isbn` varchar(15) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `data_publicacao` date NOT NULL,
  `preco` decimal(8,2) NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `data_alteracao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_editora_idx` (`id_editora`),
  CONSTRAINT `fk_editora` FOREIGN KEY (`id_editora`) REFERENCES `editora` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro_autor`
--

DROP TABLE IF EXISTS `livro_autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livro_autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_livro` int NOT NULL,
  `id_autor` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_autor_idx` (`id_autor`),
  KEY `fk_livro__idx` (`id_livro`),
  CONSTRAINT `fk_autor` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `fk_livro_` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro_autor`
--

LOCK TABLES `livro_autor` WRITE;
/*!40000 ALTER TABLE `livro_autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `livro_autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro_critica`
--

DROP TABLE IF EXISTS `livro_critica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livro_critica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_livro` int NOT NULL,
  `nome` varchar(70) NOT NULL,
  `critica` mediumtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_livro_idx` (`id_livro`),
  CONSTRAINT `fk_livro_critica` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro_critica`
--

LOCK TABLES `livro_critica` WRITE;
/*!40000 ALTER TABLE `livro_critica` DISABLE KEYS */;
/*!40000 ALTER TABLE `livro_critica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `data_alteracao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'2021-02-20 21:21:16','2021-02-20 21:21:16'),(2,'2021-02-20 21:30:55','2021-02-20 21:30:55'),(5,'2021-02-22 01:29:11','2021-02-22 01:29:11'),(10,'2021-02-22 21:58:16','2021-02-22 21:58:16'),(11,'2021-02-22 22:00:36','2021-02-22 22:00:36');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa_fisica`
--

DROP TABLE IF EXISTS `pessoa_fisica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa_fisica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_pessoa` int NOT NULL,
  `nome` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `id_pessoa_UNIQUE` (`id_pessoa`),
  CONSTRAINT `fk_pessoa_fisica` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa_fisica`
--

LOCK TABLES `pessoa_fisica` WRITE;
/*!40000 ALTER TABLE `pessoa_fisica` DISABLE KEYS */;
INSERT INTO `pessoa_fisica` VALUES (1,1,'ROBERT C MARTIN'),(2,2,'SPAFFORD GEORGE'),(4,10,'CECÍLIA MARY FISCHER RUBIRA');
/*!40000 ALTER TABLE `pessoa_fisica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa_juridica`
--

DROP TABLE IF EXISTS `pessoa_juridica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa_juridica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_pessoa` int NOT NULL,
  `razao_social` varchar(70) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`),
  UNIQUE KEY `id_pessoa_UNIQUE` (`id_pessoa`),
  CONSTRAINT `fk_pessoa_juridica` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa_juridica`
--

LOCK TABLES `pessoa_juridica` WRITE;
/*!40000 ALTER TABLE `pessoa_juridica` DISABLE KEYS */;
INSERT INTO `pessoa_juridica` VALUES (2,5,'STARLIN ALTA EDITORA E CONSULTORIA - EIRELI','04713695000100'),(7,11,'EDITORA CIENCIA MODERNA LTDA','32221186000103');
/*!40000 ALTER TABLE `pessoa_juridica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-22 22:57:57

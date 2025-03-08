-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: aitrcms
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin123','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `credits` int NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data`
--

DROP TABLE IF EXISTS `data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data`
--

LOCK TABLES `data` WRITE;
/*!40000 ALTER TABLE `data` DISABLE KEYS */;
/*!40000 ALTER TABLE `data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `organizer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'2025-02-10 00:00:00.000000','Annual Tech Conference','TechCon 2025','Tech Community');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (3,'rajiv@university.com','Rajiv Sharma','AIML'),(5,'anil@university.com','Anil Kumar','AIML');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_award`
--

DROP TABLE IF EXISTS `faculty_award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_award` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(255) DEFAULT NULL,
  `award_name` varchar(255) DEFAULT NULL,
  `awarded_by` varchar(255) DEFAULT NULL,
  `award_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_award`
--

LOCK TABLES `faculty_award` WRITE;
/*!40000 ALTER TABLE `faculty_award` DISABLE KEYS */;
INSERT INTO `faculty_award` VALUES (1,'Dr. Anjali Mehta','Best Researcher','IIT Delhi','2024-08-10'),(2,'Dr. Rakesh Verma','Innovative Educator','NIT Trichy','2024-07-05'),(3,'Dr. Neha Sharma','Excellence in Teaching','IIT Bombay','2024-06-20'),(4,'Dr. Amit Gupta','Outstanding Contribution','IIT Madras','2024-05-15');
/*!40000 ALTER TABLE `faculty_award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_conference`
--

DROP TABLE IF EXISTS `faculty_conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_conference` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(255) DEFAULT NULL,
  `conference_name` varchar(255) DEFAULT NULL,
  `paper_title` varchar(255) DEFAULT NULL,
  `presentation_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_conference`
--

LOCK TABLES `faculty_conference` WRITE;
/*!40000 ALTER TABLE `faculty_conference` DISABLE KEYS */;
INSERT INTO `faculty_conference` VALUES (1,'Dr. Anjali Mehta','AI Symposium','AI in Healthcare','2024-09-10'),(2,'Dr. Rakesh Verma','Blockchain Summit','Blockchain Security','2024-08-15'),(3,'Dr. Neha Sharma','Cloud Expo','Cloud Computing Innovations','2024-07-20');
/*!40000 ALTER TABLE `faculty_conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_fdp`
--

DROP TABLE IF EXISTS `faculty_fdp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_fdp` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(255) DEFAULT NULL,
  `program_name` varchar(255) DEFAULT NULL,
  `organized_by` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `certificate_link` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `fdp_title` varchar(255) DEFAULT NULL,
  `organizer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_fdp`
--

LOCK TABLES `faculty_fdp` WRITE;
/*!40000 ALTER TABLE `faculty_fdp` DISABLE KEYS */;
INSERT INTO `faculty_fdp` VALUES (1,'Dr. Anjali Mehta','AI & ML Workshop','IIT Bombay','2024-03-02','2024-03-05',NULL,NULL,NULL,NULL),(2,'Dr. Rakesh Verma','Cybersecurity Seminar','NIT Trichy','2024-04-10','2024-04-15',NULL,NULL,NULL,NULL),(3,'Dr. Neha Sharma','Cloud Computing Conference','IIT Delhi','2024-05-20','2024-05-25',NULL,NULL,NULL,NULL),(4,'Dr. Amit Gupta','IoT Innovations Workshop','IIT Madras','2024-06-15','2024-06-20',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `faculty_fdp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_patent`
--

DROP TABLE IF EXISTS `faculty_patent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_patent` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(255) DEFAULT NULL,
  `patent_title` varchar(255) DEFAULT NULL,
  `patent_number` varchar(255) DEFAULT NULL,
  `application_date` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `filing_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_patent`
--

LOCK TABLES `faculty_patent` WRITE;
/*!40000 ALTER TABLE `faculty_patent` DISABLE KEYS */;
INSERT INTO `faculty_patent` VALUES (1,'Dr. Anjali Mehta','AI-Based Diagnostic Tool','AI2024098','2024-02-15','Approved',NULL),(2,'Dr. Rakesh Verma','Blockchain Security System','BCS2024123','2024-03-10','Pending',NULL),(3,'Dr. Neha Sharma','Cloud Storage Optimization','CSO2024056','2024-04-05','Approved',NULL),(4,'Dr. Amit Gupta','IoT Smart Device','IoT2024789','2024-05-01','Filed',NULL);
/*!40000 ALTER TABLE `faculty_patent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_research_paper`
--

DROP TABLE IF EXISTS `faculty_research_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_research_paper` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publication_date` varchar(255) DEFAULT NULL,
  `journal_name` varchar(255) DEFAULT NULL,
  `co_authors` varchar(255) DEFAULT NULL,
  `abstract_text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_research_paper`
--

LOCK TABLES `faculty_research_paper` WRITE;
/*!40000 ALTER TABLE `faculty_research_paper` DISABLE KEYS */;
INSERT INTO `faculty_research_paper` VALUES (1,'Dr. Anjali Mehta','Quantum Computinhhg','2024-01-10','Journal of Quantum Physics','Dr. Rakesh Verma',NULL),(2,'Dr. Rakesh Verma','Blockchain Security','2024-06-20','Cybersecurity Journal','Dr. Anjali Mehta',NULL),(3,'Dr. Neha Sharma','Cloud Computing Innovations','2024-07-15','Journal of Cloud Tech','Dr. Amit Gupta',NULL),(4,'Dr. Amit Gupta','IoT and Smart Cities','2024-08-05','IoT Research Journal','Dr. Neha Sharma',NULL);
/*!40000 ALTER TABLE `faculty_research_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financial_resources`
--

DROP TABLE IF EXISTS `financial_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `financial_resources` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capital_expenses` double DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `operational_expenses` double DEFAULT NULL,
  `salary_expenditure` double DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financial_resources`
--

LOCK TABLES `financial_resources` WRITE;
/*!40000 ALTER TABLE `financial_resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `financial_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ipr_research`
--

DROP TABLE IF EXISTS `ipr_research`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ipr_research` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `project_title` varchar(255) NOT NULL,
  `funding_agency` varchar(255) NOT NULL,
  `principal_investigator` varchar(255) DEFAULT NULL,
  `amount_funded` decimal(15,2) DEFAULT NULL,
  `status` enum('COMPLETED','ONGOING') NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ipr_research`
--

LOCK TABLES `ipr_research` WRITE;
/*!40000 ALTER TABLE `ipr_research` DISABLE KEYS */;
/*!40000 ALTER TABLE `ipr_research` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `placements`
--

DROP TABLE IF EXISTS `placements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `placements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `package_amount` double NOT NULL,
  `year` int NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjc11afmg46abhtw7smigudxnc` (`student_id`),
  CONSTRAINT `FKjc11afmg46abhtw7smigudxnc` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `placements`
--

LOCK TABLES `placements` WRITE;
/*!40000 ALTER TABLE `placements` DISABLE KEYS */;
/*!40000 ALTER TABLE `placements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year` int NOT NULL,
  `cgpa` double DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (3,'alice.johnson@example.com','Alice Johnson',1,5,'AIML','B.Tech'),(10,'gaurav@gmail.com','gaurav',2,9,'AIML','btech');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_certificate`
--

DROP TABLE IF EXISTS `student_certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_certificate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `certificate_name` varchar(255) DEFAULT NULL,
  `issued_by` varchar(255) DEFAULT NULL,
  `issue_date` varchar(255) DEFAULT NULL,
  `certificate_link` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_certificate`
--

LOCK TABLES `student_certificate` WRITE;
/*!40000 ALTER TABLE `student_certificate` DISABLE KEYS */;
INSERT INTO `student_certificate` VALUES (1,'Aarav Mehta','Java Programming','Oracle','2024-04-15','https://example.com/certificates/aarav_java.pdf',NULL),(2,'Sanya Kapoor','Web Development','Coursera','2024-05-20','https://example.com/certificates/sanya_webdev.pdf',NULL),(3,'Ishaan Sharma','Machine Learning','Udemy','2024-03-10','https://example.com/certificates/ishaan_ml.pdf',NULL),(4,'Riya Gupta','Data Science','IBM','2024-02-25','https://example.com/certificates/riya_datascience.pdf',NULL);
/*!40000 ALTER TABLE `student_certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_hackathon`
--

DROP TABLE IF EXISTS `student_hackathon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_hackathon` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `certificate_link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_hackathon`
--

LOCK TABLES `student_hackathon` WRITE;
/*!40000 ALTER TABLE `student_hackathon` DISABLE KEYS */;
INSERT INTO `student_hackathon` VALUES (1,'Aarav Mehta','CodeQuest','2024-08-15','1st Place','https://example.com/certificates/aarav_codequest.pdf'),(2,'Sanya Kapoor','TechTalks','2024-09-10','Runner-Up','https://example.com/certificates/sanya_techtalks.pdf'),(3,'Ishaan Sharma','Hackathon Heroes','2024-07-22','2nd Place','https://example.com/certificates/ishaan_hackheroes.pdf'),(4,'Riya Gupta','InnoFest','2024-06-18','Winner','https://example.com/certificates/riya_innofest.pdf');
/*!40000 ALTER TABLE `student_hackathon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_internship`
--

DROP TABLE IF EXISTS `student_internship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_internship` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `certificate_link` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_internship`
--

LOCK TABLES `student_internship` WRITE;
/*!40000 ALTER TABLE `student_internship` DISABLE KEYS */;
INSERT INTO `student_internship` VALUES (1,'Aarav Mehta','Google','Software Intern','2024-06-01','2024-08-31','https://example.com/certificates/aarav_google.pdf',NULL),(2,'Sanya Kapoor','Amazon','Web Developer Intern','2024-05-01','2024-07-31','https://example.com/certificates/sanya_amazon.pdf',NULL),(3,'Ishaan Sharma','Microsoft','AI Intern','2024-04-01','2024-06-30','https://example.com/certificates/ishaan_microsoft.pdf',NULL),(4,'Riya Gupta','Facebook','Data Analyst Intern','2024-03-01','2024-05-31','https://example.com/certificates/riya_facebook.pdf',NULL);
/*!40000 ALTER TABLE `student_internship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_placement`
--

DROP TABLE IF EXISTS `student_placement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_placement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `joining_date` varchar(255) DEFAULT NULL,
  `offer_letter_link` varchar(255) DEFAULT NULL,
  `Package` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_placement`
--

LOCK TABLES `student_placement` WRITE;
/*!40000 ALTER TABLE `student_placement` DISABLE KEYS */;
INSERT INTO `student_placement` VALUES (2,'Sanya Kapoor','Amazon','2024-08-15','https://example.com/offers/sanya_amazon.pdf','20.00'),(3,'Ishaan Sharma','Microsoft','2024-07-10','https://example.com/offers/ishaan_microsoft.pdf','25.00'),(4,'Riya Gupta','Facebook','2024-06-20','https://example.com/offers/riya_facebook.pdf','18.00');
/*!40000 ALTER TABLE `student_placement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_research_paper`
--

DROP TABLE IF EXISTS `student_research_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_research_paper` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publication_date` varchar(255) DEFAULT NULL,
  `journal_name` varchar(255) DEFAULT NULL,
  `co_authors` varchar(255) DEFAULT NULL,
  `abstract` varchar(255) DEFAULT NULL,
  `paper_link` varchar(255) DEFAULT NULL,
  `paper_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_research_paper`
--

LOCK TABLES `student_research_paper` WRITE;
/*!40000 ALTER TABLE `student_research_paper` DISABLE KEYS */;
INSERT INTO `student_research_paper` VALUES (1,'Aarav Mehta','AI in Healthcare','2024-03-15','International Journal of AI','Sanya Kapoor','Exploring AI applications in healthcare.',NULL,NULL),(2,'Sanya Kapoor','Web Security Enhancements','2024-04-10','Journal of Cybersecurity','Ishaan Sharma','Analysis of modern web security protocols.',NULL,NULL),(3,'Ishaan Sharma','Machine Learning Algorithms','2024-02-20','ML Research Journal','Riya Gupta','Comparative study of ML algorithms.',NULL,NULL),(4,'Riya Gupta','Data Science in Marketing','2024-01-30','Journal of Data Science','Aarav Mehta','Impact of data science on marketing strategies.',NULL,NULL);
/*!40000 ALTER TABLE `student_research_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_sports`
--

DROP TABLE IF EXISTS `student_sports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_sports` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `sport_name` varchar(255) DEFAULT NULL,
  `achievement` varchar(255) DEFAULT NULL,
  `event_date` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_sports`
--

LOCK TABLES `student_sports` WRITE;
/*!40000 ALTER TABLE `student_sports` DISABLE KEYS */;
INSERT INTO `student_sports` VALUES (1,'Aarav Mehta','Football','Captain - Winning Team','2024-11-15',NULL),(2,'Sanya Kapoor','Basketball','Best Player Award','2024-10-10',NULL),(3,'Ishaan Sharma','Badminton','Runner-Up','2024-09-05',NULL),(4,'Riya Gupta','Table Tennis','Gold Medal','2024-08-25',NULL);
/*!40000 ALTER TABLE `student_sports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year` int NOT NULL,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6jiqckumc6tm0h9otqbtqhgnr` (`course_id`),
  CONSTRAINT `FK6jiqckumc6tm0h9otqbtqhgnr` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-08 14:51:21

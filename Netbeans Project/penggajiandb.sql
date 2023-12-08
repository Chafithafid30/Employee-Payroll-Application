/*
SQLyog Professional v10.42 
MySQL - 5.5.5-10.1.25-MariaDB : Database - penggajiandb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`penggajiandb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `penggajiandb`;

/*Table structure for table `jabatan` */

DROP TABLE IF EXISTS `jabatan`;

CREATE TABLE `jabatan` (
  `id_jabatan` int(6) NOT NULL AUTO_INCREMENT,
  `nama_jabatan` varchar(30) NOT NULL,
  `gaji_pokok` double NOT NULL,
  `deskripsi` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_jabatan`)
) ENGINE=InnoDB AUTO_INCREMENT=888021 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Data for the table `jabatan` */

insert  into `jabatan`(`id_jabatan`,`nama_jabatan`,`gaji_pokok`,`deskripsi`) values (888001,'Presiden Direktur',300000000,'Mengatur dan memimpin sebuah perusahaan'),(888002,'Executive (GM)',25000000,'Membantu Direktur Utama dalam memimpin dan mengarahkan perusahaan'),(888003,'General Manager',20000000,'Mengatur dan memanage bagian-bagian perusahaan'),(888004,'Manager Keuangan',10000000,'Mengepalai bidang keuangan perusahaan'),(888005,'Manager Personalia',10000000,'Kepala bidang SDM'),(888006,'Kepala HRD',10000000,'Mengepalai HRD'),(888007,'Manager Produksi',10000000,'Mengepalai bagian produksi'),(888008,'Manager Marketing',10000000,'Mengepalai bagian pemasaran'),(888009,'Staf Keuangan',2000000,'Staf bidang keuangan'),(888010,'Staf HRD',2000000,'Staf bidang HRD'),(888011,'Staf Marketing',2000000,'Staf bagian pemasaran'),(888012,'Kepala Pengadaan',3000000,'Mengepalai bidang pengadaan'),(888013,'Kepala Keamanan',3000000,'Mengepalai bidang keamanan'),(888014,'Kepala Gudang',3000000,'Mengepalai dan mengatur sistem pergudangan'),(888015,'Security A',2000000,'Petugas keamanan tingkat 1'),(888016,'Security B',1500000,'Petugas keamanan tingkat 2'),(888017,'Security C',1000000,'Petugas keamanan tingkat 3'),(888018,'IT Support',5000000,'Menangani segala urusan dibidang IT'),(888019,'Database Administrator',7000000,'Mengatur dan Mengurus basis data perushaaan');

/*Table structure for table `karyawan` */

DROP TABLE IF EXISTS `karyawan`;

CREATE TABLE `karyawan` (
  `id_user` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `tempat_lahir` varchar(30) NOT NULL,
  `tanggal_lahir` varchar(50) NOT NULL,
  `agama` varchar(20) DEFAULT NULL,
  `id_jabatan` int(6) NOT NULL,
  `status` enum('Admin','User') NOT NULL,
  `kontak` varchar(50) DEFAULT NULL,
  `alamat` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=52003 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Data for the table `karyawan` */

insert  into `karyawan`(`id_user`,`nama`,`jenis_kelamin`,`tempat_lahir`,`tanggal_lahir`,`agama`,`id_jabatan`,`status`,`kontak`,`alamat`) values (52001,'Noctis Lucis Caelum','Laki-Laki','Insomnia','30 Agustus 1996','Kristen',888001,'Admin','081223334444','Insomnia'),(52002,'Lunafreya nox Fleuret','Perempuan','Tenebrae','04 September 1992','Kristen',888002,'User','085885885885','Insomnia');

/*Table structure for table `pengguna` */

DROP TABLE IF EXISTS `pengguna`;

CREATE TABLE `pengguna` (
  `id_user` int(5) NOT NULL AUTO_INCREMENT,
  `kata_sandi` varchar(32) NOT NULL DEFAULT 'masuk',
  `nama` varchar(50) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=52003 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Data for the table `pengguna` */

insert  into `pengguna`(`id_user`,`kata_sandi`,`nama`) values (52001,'masuk','Noctis Lucis Caelum'),(52002,'masuk','Lunafreya nox Fleuret');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

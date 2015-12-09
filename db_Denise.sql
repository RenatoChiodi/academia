SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Denise` ;
USE `Denise` ;

-- -----------------------------------------------------
-- Table `Denise`.`tb_paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Denise`.`tb_paciente` (
  `id_paciente` INT NOT NULL AUTO_INCREMENT,
  `nm_paciente` VARCHAR(45) NOT NULL,
  `sexo_paciente` VARCHAR(1) NOT NULL,
  `dt_nasc_paciente` DATE NOT NULL,
  PRIMARY KEY (`id_paciente`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

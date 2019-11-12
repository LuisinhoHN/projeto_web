SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Moeda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Moeda` (
  `id_moeda` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `moeda_para_RS` DOUBLE NOT NULL,
  `RS_para_moeda` DOUBLE NOT NULL,
  PRIMARY KEY (`id_moeda`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `cidade` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `pais` VARCHAR(45) NOT NULL,
  `moeda_id` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_Usuario_Moeda1_idx` (`moeda_id` ASC),
  CONSTRAINT `fk_Usuario_Moeda1`
    FOREIGN KEY (`moeda_id`)
    REFERENCES `mydb`.`Moeda` (`id_moeda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tipo_receita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tipo_receita` (
  `id_tipo_receita` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_tipo_receita`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Receita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Receita` (
  `id_receita` INT NOT NULL AUTO_INCREMENT,
  `data_receita` DATE NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `intervalo` VARCHAR(45) NULL,
  `usuario_id` INT NOT NULL,
  `tipo_receita_id` INT NOT NULL,
  `moeda_id` INT NOT NULL,
  PRIMARY KEY (`id_receita`),
  INDEX `fk_Receita_Usuario1_idx` (`usuario_id` ASC),
  INDEX `fk_Receita_Tipo_receita1_idx` (`tipo_receita_id` ASC),
  INDEX `fk_Receita_Moeda1_idx` (`moeda_id` ASC),
  CONSTRAINT `fk_Receita_Usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receita_Tipo_receita1`
    FOREIGN KEY (`tipo_receita_id`)
    REFERENCES `mydb`.`Tipo_receita` (`id_tipo_receita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receita_Moeda1`
    FOREIGN KEY (`moeda_id`)
    REFERENCES `mydb`.`Moeda` (`id_moeda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Forma_pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Forma_pagamento` (
  `id_forma_pagamento` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id_forma_pagamento`),
  INDEX `fk_Forma_pagamento_Usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_Forma_pagamento_Usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria_despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria_despesa` (
  `id_categoria_despesa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id_categoria_despesa`),
  INDEX `fk_Categoria_despesa_Usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_Categoria_despesa_Usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tipo_despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tipo_despesa` (
  `id_tipo_despesa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_tipo_despesa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Despesa` (
  `id_despesa` INT NOT NULL AUTO_INCREMENT,
  `data_despesa` DATE NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `intervalo` VARCHAR(45) NULL,
  `parcelas` INT NULL,
  `usuario_id` INT NOT NULL,
  `forma_pagamento_id` INT NOT NULL,
  `categoria_despesa_id` INT NOT NULL,
  `tipo_despesa_id` INT NOT NULL,
  `moeda_id` INT NOT NULL,
  PRIMARY KEY (`id_despesa`),
  INDEX `fk_Despesa_Usuario_idx` (`usuario_id` ASC),
  INDEX `fk_Despesa_Forma_pagamento1_idx` (`forma_pagamento_id` ASC),
  INDEX `fk_Despesa_Categoria_despesa1_idx` (`categoria_despesa_id` ASC),
  INDEX `fk_Despesa_Tipo_despesa1_idx` (`tipo_despesa_id` ASC),
  INDEX `fk_Despesa_Moeda1_idx` (`moeda_id` ASC),
  CONSTRAINT `fk_Despesa_Usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Despesa_Forma_pagamento1`
    FOREIGN KEY (`forma_pagamento_id`)
    REFERENCES `mydb`.`Forma_pagamento` (`id_forma_pagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Despesa_Categoria_despesa1`
    FOREIGN KEY (`categoria_despesa_id`)
    REFERENCES `mydb`.`Categoria_despesa` (`id_categoria_despesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Despesa_Tipo_despesa1`
    FOREIGN KEY (`tipo_despesa_id`)
    REFERENCES `mydb`.`Tipo_despesa` (`id_tipo_despesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Despesa_Moeda1`
    FOREIGN KEY (`moeda_id`)
    REFERENCES `mydb`.`Moeda` (`id_moeda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Meta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Meta` (
  `id_meta` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `percentual_poupanca` DOUBLE NOT NULL,
  `investimento_mensal` DOUBLE NOT NULL,
  `subtotal_parcelas` INT NOT NULL,
  `parcelas_previstas_total` INT NOT NULL,
  `subtotal_valor` DOUBLE NOT NULL,
  `valor_total` DOUBLE NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id_meta`),
  INDEX `fk_Meta_Usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_Meta_Usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Frase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Frase` (
  `Id_frase` INT NOT NULL AUTO_INCREMENT,
  `frase` VARCHAR(200) NOT NULL,
  `autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id_frase`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
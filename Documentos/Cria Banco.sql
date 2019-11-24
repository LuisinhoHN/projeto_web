SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` ;
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
  `saldo` INT NULL,
  `cidade` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `pais` VARCHAR(45)  NULL,
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
  `tipo_receita_id` INT NULL,
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
   `parcelas_a_pagar` INT NULL,
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

/* Script Inserts ---------------------------------------------------------------------------------------------------------------- 
	Inserção de dados nas tabelas. e.g Cadastro de Usuarios, receitas, despesas, metas etc.
*/

-- Frase -------------------------------
USE mydb;
INSERT INTO Frase (id_frase, frase, autor) VALUES
	(1,"Seu dinheiro cresce na medida em que você cresce", "T. Harv Eker"),
	(2,"Só o tolo confunde valor com preço", "Antônio Machado"),
	(3,"Nunca gaste seu dinheiro antes de recebê-lo", "Thomas Jefferson"),
	(4,"Se uma pessoa adquire a atitude correta em relação 
		ao dinheiro, isso ajudará a endireitar quase todas as
		outras áreas de sua vida", "Billy Graham"),
    (5,"Economia, frequentemente, não tem relação com o total de 
		dinheiro gasto, mas com a sabedoria empregada ao gastá-lo", "Henry Ford"),
	(6,"Devagar! Quem mais corre tropeça!", "William Shakespeare"),
	(7,"Dinheiro é o oposto do clima. Niguém fala sobre ele, 
		mas todo mundo faz algo a respeito", "Rebecca Johnson"),
    (8,"Cuidado com as pequenas despesas; um pequeno vazamento 
		afundará um grande navio.", "Benjamin Franklin"),
	(9,"Se você almeja ser rico, pense em poupar assim como você 
		pensa em ganhar", "Benjamin Franklin"),
	(10,"Dinheiro é o oposto do clima. Ninguém fala sobre ele, 
		mas todo mundo faz algo a respeito", "Warren Buffett");
        
-- Tipo_despesa -------------------------------
INSERT INTO Tipo_despesa (id_tipo_despesa, nome) VALUES
	(1,"Fixa"),
	(2,"Variável"),
	(3,"Temporária");

-- Tipo_receita -------------------------------
INSERT INTO Tipo_receita (id_tipo_receita, nome) VALUES
	(1,"Fixa"),
	(2,"Variável");
    
    
-- Moeda ------------------------------- Valores com base na cotação do dia 10/11
INSERT INTO Moeda (id_moeda, nome, moeda_para_RS, RS_para_moeda) VALUES
	(1,"Real", 1.00, 1.00);
    

-- Usuario ---------------------------------------
INSERT INTO Usuario (id_usuario, nome, email, senha, data_nascimento, cidade, estado, pais, moeda_id) VALUES
	(1, "adm", "adm@gmail.com", "adm", "2000-11-17", 	NULL, NULL, NULL, 1);  

-- Categoria_despesa -------------------------------
INSERT INTO Categoria_despesa (id_categoria_despesa, nome, descricao, usuario_id) VALUES
(1,"Alimentação", "Refeiçoes e lanches", 1),
(2,"Moradia", "Manutenção de equipamentos do domicílio", 1),
(3,"Educação", "Gastos com livros, eventos e palestras", 2),
(4,"Lazer", "Passeios, Viagens", 3),
(5,"Saúde", "Plano de saúde e consultas extras", 2),
(6,"Transporte", "", 3),
(7, "Comunicação ", "Celulares, Telefonia", 3);
    
-- Receita ---------------------------------------
INSERT INTO Receita (id_receita, data_receita, descricao, valor, intervalo, usuario_id, tipo_receita_id, moeda_id) VALUES
	(1, "2016-11-01", "Salário", 5500.00, "Mensal", 1, 1, 1);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- MySQL Script generated by MySQL Workbench
-- Sat Mar 21 11:15:00 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema testithappens1304
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema testithappens1304
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testithappens1304` DEFAULT CHARACTER SET utf8 ;
USE `testithappens1304` ;

-- -----------------------------------------------------
-- Table `testithappens1304`.`filial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testithappens1304`.`filial` (
  `codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testithappens1304`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testithappens1304`.`produto` (
  `codigo` INT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `sequencial` INT NULL,
  `codigo_barras` VARCHAR(45) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testithappens1304`.`estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testithappens1304`.`estoque` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `filial_codigo` INT NOT NULL,
  `produto_codigo` INT NOT NULL,
  `quantidade` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_estoque_filial_idx` (`filial_codigo` ASC) VISIBLE,
  INDEX `fk_estoque_produto1_idx` (`produto_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_estoque_filial`
    FOREIGN KEY (`filial_codigo`)
    REFERENCES `testithappens1304`.`filial` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_estoque_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `testithappens1304`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testithappens1304`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testithappens1304`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  `endereco` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testithappens1304`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testithappens1304`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(100) NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testithappens1304`.`pedido_estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testithappens1304`.`pedido_estoque` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `filial_codigo` INT NOT NULL,
  `observacao` VARCHAR(45) NULL,
  `valor` VARCHAR(45),
  `forma_pagamento` VARCHAR(45) NOT NULL,
  `entrada` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pedido_estoque_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_pedido_estoque_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_pedido_estoque_filial1_idx` (`filial_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_estoque_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `testithappens1304`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_estoque_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `testithappens1304`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_estoque_filial1`
    FOREIGN KEY (`filial_codigo`)
    REFERENCES `testithappens1304`.`filial` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testithappens1304`.`item_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testithappens1304`.`item_pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pedido_estoque_id` INT NOT NULL,
  `produto_codigo` INT NOT NULL,
  `quantidade` INT NULL,
  `valor_unitario` FLOAT NULL,
  `valor_total` FLOAT NULL,
  `status` VARCHAR(45),
  PRIMARY KEY (`id`),
  INDEX `fk_item_pedido_pedido_estoque1_idx` (`pedido_estoque_id` ASC) VISIBLE,
  INDEX `fk_item_pedido_produto1_idx` (`produto_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_item_pedido_pedido_estoque1`
    FOREIGN KEY (`pedido_estoque_id`)
    REFERENCES `testithappens1304`.`pedido_estoque` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_pedido_produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `testithappens1304`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- -----------------------------------------------------
-- data
-- -----------------------------------------------------

-- clientes
INSERT INTO `testithappens1304`.`cliente` (`nome`, `email`, `telefone`, `endereco`) VALUES ('Quenildo Moreira', 'quenildo@email.com', '98982123432', 'rua dos afogados 123');
INSERT INTO `testithappens1304`.`cliente` (`nome`, `email`, `telefone`, `endereco`) VALUES ('Pedro Paulo Picolo', 'ppp@email.com', '98991324565', 'rua 1 quadra 3 casa 2');

-- usuarios
INSERT INTO `testithappens1304`.`usuario` (`matricula`, `senha`, `nome`) VALUES ('123123', '321321', 'José Worker');
INSERT INTO `testithappens1304`.`usuario` (`matricula`, `senha`, `nome`) VALUES ('666666', '11001111', 'Energilson Freitas');

-- filiais
INSERT INTO `testithappens1304`.`filial` (`codigo`) VALUES (1);
INSERT INTO `testithappens1304`.`filial` (`codigo`) VALUES (60);
INSERT INTO `testithappens1304`.`filial` (`codigo`) VALUES (13);
INSERT INTO `testithappens1304`.`filial` (`codigo`) VALUES (12);
INSERT INTO `testithappens1304`.`filial` (`codigo`) VALUES (20);

-- produtos
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (44, 'leite', '232', '789007766');
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (12, 'pão', '112', '789123311');
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (7993, 'ovo', '000', '7896655665');
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (11, 'arroz', '001', '789101010');
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (10, 'feijão', '002', '789111111');
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (9, 'banana', '003', '7891121212');
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (8, 'iorguti', '004', '789131313');
INSERT INTO `testithappens1304`.`produto` (`codigo`, `descricao`, `sequencial`, `codigo_barras`) VALUES (7, 'languiça', '005', '789141414');

-- estoques
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (60, 44, 12313);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (60, 7993, 0);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (60, 10, 10);

INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (1, 12, 12);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (1, 44, 25);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (1, 11, 1234);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (1, 9, 30);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (1, 10, 555);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (1, 7, 123);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (1, 7993, 120);

INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (13, 7993, 1000);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (13, 10, 500);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (13, 7993, 120);
INSERT INTO `testithappens1304`.`estoque` (`filial_codigo`, `produto_codigo`, `quantidade`) VALUES (13, 7993, 120);
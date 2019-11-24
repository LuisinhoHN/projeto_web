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
	(1,"Real", 1.00, 1.00),
	(2,"Dolar Americano", 3.35, 0.30),
    (3,"Euro", 3.65, 0.27),
    (4,"Iene", 0.03, 31.89);
    

-- Usuario ---------------------------------------
INSERT INTO Usuario (id_usuario, nome, email, senha, data_nascimento, cidade, estado, pais, moeda_id) VALUES
	(1, "Jonathan Romualdo", "jonathan@gmail.com", "jon123", "2000-11-27", "Recife", "PE", "Brasil", 1),
	(2, "Henrique Negromonte", "henrique@gmail.com", "luis123", "2000-11-27", "Recife", "PE", "Brasil", 1),
	(3, "Allan Santos", "allan@gmail.com", "santos123", "2000-11-17", 	NULL, NULL, "Brasil", 1);

  
-- Forma_pagamento ---------------------------------------
INSERT INTO Forma_pagamento (id_forma_pagamento, nome, descricao, usuario_id) VALUES 
	(1, "À vista", "Pago em espécie", 2),
    (2, "Cartão de Crédito", "", 2),
    (3, "Cartão de Débito BB", "Debita da Conta poupança ", 1),
    (4, "Cartão de Débito CAIXA", "Debita da Conta corrente", 2);
  

-- Categoria_despesa -------------------------------
INSERT INTO Categoria_despesa (id_categoria_despesa, nome, descricao, usuario_id) VALUES
(1,"Alimentação", "Refeiçoes e lanches", 1),
(2,"Moradia", "Manutenção de equipamentos do domicílio", 1),
(3,"Educação", "Gastos com livros, eventos e palestras", 2),
(4,"Lazer", "Passeios, Viagens", 3),
(5,"Saúde", "Plano de saúde e consultas extras", 2),
(6,"Transporte", "", 3),
(7, "Comunicação e Tecnologia", "Celulares, Tablets, pen drives etc.", 3);

    
-- Meta ---------------------------------------
INSERT INTO Meta (id_meta, nome, tipo, percentual_poupanca, investimento_mensal, subtotal_parcelas, 
	parcelas_previstas_total, subtotal_valor, valor_total, usuario_id) VALUES 
	(1, "Congresso - CSBC 2017", "Estática", 10, 200.00, 2, 5, 400.00, 1000.00, 3),
    (2, "Entrada no Spin LTZ", "Dinâmica", 30, 600.00, 12, 15, 7200.00, 9000.00, 2),
    (3, "Casa", "Dinâmica", 60, 2000.00, 18, 132, 36000.00, 300000.00, 1);
    
-- Despesa ---------------------------------------
INSERT INTO Despesa (id_despesa, data_despesa, descricao, valor, intervalo, parcelas, usuario_id,
	forma_pagamento_id, categoria_despesa_id, tipo_despesa_id, moeda_id) VALUES
	(1, "2016-11-01", "Água Mineral", 2.00, NULL, NULL, 1, 1, 1, 1, 1),
    (2, "2016-11-06", "Almoço", 12.50, NULL, NULL, 1, 2, 1, 2, 1),
    (3, "2016-10-25", "SmartPhone Moto G 4", 1200.00, "Mensal", 7, 2, 4, 7, 3, 1),
    (4, "2015-10-20", "SmartPhone Moto X Play", 1400.00, "Mensal", 5, 3, 3, 7, 3, 2);
    
-- Receita ---------------------------------------
INSERT INTO Receita (id_receita, data_receita, descricao, valor, intervalo, usuario_id, tipo_receita_id, moeda_id) VALUES
	(1, "2016-11-01", "Salário", 5500.00, "Mensal", 1, 1, 1),
    (2, "2016-11-21", "Criação de Site WEB", 100.00, NULL, 1, 2, 1),
    (3, "2016-11-22", "Venda de Notebook", 20000.00, NULL, 2, 2, 1),
    (4, "2016-10-17", "Aluguel de Imóvel", 150.00, NULL, 2, 1, 1);
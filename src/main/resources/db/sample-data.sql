INSERT INTO vendedores (cpf, nome, matricula) VALUES
('12345678909', 'João da Silva', 'MAT001'),
('98765432100', 'Maria Oliveira', 'MAT002'),
('45678912301', 'Carlos Pereira', 'MAT003'),
('32165498700', 'Ana Souza', 'MAT004'),
('74185296300', 'Paulo Lima', 'MAT005');

INSERT INTO clientes (nome, elegivel_para_desconto) VALUES
('Fernanda Costa', true),
('Rafael Gomes', false),
('Luciana Torres', true),
('Bruno Andrade', false),
('Juliana Castro', true),
('Marcelo Dias', false),
('Amanda Rocha', true),
('Pedro Martins', false),
('Camila Moraes', false),
('Tiago Ribeiro', true);

INSERT INTO patos (nome, id_mae) VALUES
-- Mães
('Pato Mãe 1', NULL),
('Pato Mãe 2', NULL),
('Pato Mãe 3', NULL),
('Pato Mãe 4', NULL),
('Pato Mãe 5', NULL),

-- Filhos da Mãe 1
('Pato Filho 1.1', 1),
('Pato Filho 1.2', 1),
('Pato Filho 1.3', 1),

-- Filhos da Mãe 2
('Pato Filho 2.1', 2),
('Pato Filho 2.2', 2),
('Pato Filho 2.3', 2),

-- Filhos da Mãe 3
('Pato Filho 3.1', 3),
('Pato Filho 3.2', 3),
('Pato Filho 3.3', 3),

-- Filhos da Mãe 4
('Pato Filho 4.1', 4),
('Pato Filho 4.2', 4),
('Pato Filho 4.3', 4),

-- Filhos da Mãe 5
('Pato Filho 5.1', 5),
('Pato Filho 5.2', 5),
('Pato Filho 5.3', 5),

-- Filhos do Filho 1.1
('Pato Filho do Filho 1.1.1', 6),
('Pato Filho do Filho 1.1.2', 6),
('Pato Filho do Filho 1.1.3', 6),

-- Filhos do Filho 2.1
('Pato Filho do Filho 2.1.1', 9),
('Pato Filho do Filho 2.1.2', 9),

-- Filhos do Filho 3.3
('Pato Filho do Filho 3.3.1', 14),

-- Filhos do Filho 4.2
('Pato Filho do Filho 4.2.1', 16),
('Pato Filho do Filho 4.2.2', 16);

INSERT INTO vendas (id_cliente, id_vendedor, data_venda, valor_total) VALUES
(1, 1, '2024-12-01 10:15:00', 300.00),
(2, 2, '2024-12-02 11:30:00', 250.00),
(3, 3, '2024-12-03 14:20:00', 150.00),
(4, 4, '2024-12-04 09:45:00', 200.00),
(5, 5, '2024-12-05 13:10:00', 180.00),
(6, 1, '2025-01-05 15:25:00', 220.00),
(7, 2, '2025-01-07 10:50:00', 270.00),
(8, 3, '2025-01-09 16:40:00', 320.00),
(9, 4, '2025-01-11 12:05:00', 260.00),
(10, 5, '2025-01-13 17:00:00', 210.00),
(1, 1, '2025-02-01 08:35:00', 400.00),
(2, 2, '2025-02-02 14:55:00', 140.00),
(3, 3, '2025-02-04 09:15:00', 160.00),
(4, 4, '2025-02-05 11:25:00', 120.00),
(5, 5, '2025-02-07 18:10:00', 310.00);

INSERT INTO venda_pato (id_venda, id_pato, preco_unitario) VALUES
(1, 6, 100.00),
(1, 7, 100.00),
(1, 8, 100.00),
(2, 9, 250.00),
(3, 10, 150.00),
(4, 11, 200.00),
(5, 12, 180.00),
(6, 13, 220.00),
(7, 14, 270.00),
(8, 15, 320.00),
(9, 16, 260.00),
(10, 17, 210.00),
(11, 18, 130.00),
(11, 19, 140.00),
(12, 21, 140.00),
(13, 22, 160.00),
(14, 24, 120.00),
(15, 25, 310.00);

UPDATE patos SET vendido = TRUE WHERE id IN (
    SELECT id_pato FROM venda_pato
);
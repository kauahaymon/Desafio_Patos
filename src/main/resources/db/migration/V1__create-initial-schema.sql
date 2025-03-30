-- Tabela de patos
CREATE TABLE patos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    id_mae INTEGER REFERENCES patos(id) ON DELETE SET NULL,
    vendido BOOLEAN DEFAULT FALSE
);

--Tabela de vendedores
CREATE TABLE vendedores (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(25) NOT NULL UNIQUE
);

-- Tabela de Clientes
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    elegivel_para_desconto BOOLEAN DEFAULT FALSE
);

-- Tabela de Vendas
CREATE TABLE vendas (
    id SERIAL PRIMARY KEY,
    id_cliente INTEGER NOT NULL REFERENCES clientes(id),
    id_vendedor INTEGER NOT NULL REFERENCES vendedores(id),
    data_venda TIMESTAMP NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL
);

-- Tabela de associação: Muitos Patos para Muitas Vendas
CREATE TABLE venda_pato (
    id SERIAL PRIMARY KEY,
    id_venda INTEGER NOT NULL REFERENCES vendas(id),
    id_pato INTEGER NOT NULL REFERENCES patos(id),
    preco_unitario DECIMAL(10,2) NOT NULL,
    UNIQUE(id_pato)
);
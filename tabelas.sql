CREATE TABLE Cargo (
    IDCargo INT AUTO_INCREMENT PRIMARY KEY,
    nome_cargo VARCHAR(255) NOT NULL
);

INSERT INTO Cargo (NomeCargo) VALUES
('Gerente'),
('Auxiliar'),
('Vendedor');

CREATE TABLE Funcionarios (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    CPF VARCHAR(14) UNIQUE NOT NULL,
    Salario DECIMAL(10, 2) NOT NULL,
    IDCargo INT NOT NULL,
    FOREIGN KEY (IDCargo) REFERENCES Cargo(IDCargo)
);

INSERT INTO Funcionarios (Nome, CPF, Salario, IDCargo) VALUES
('Leandro', '123.456.789-01', 3000.00, 1),
('Marcelo', '987.654.321-09', 2500.00, 3);

CREATE TABLE Produtos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Quantidade INT NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL
);

INSERT INTO Produtos (Nome, Quantidade, ValorUnitario) VALUES
('Produto1', 10, 25.00),
('Produto2', 20, 15.00),
('Produto3', 15, 30.00);
-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO public.tb_users (
    id,
    created_at,
    credit_card_number,
    email,
    name,
    password,
    updated_at,
    city,
    status,
    status_name
)
VALUES (
           '7e1a0e6d-3c4e-4c77-9c4d-3b4f5e9e7a1b', -- UUID Válido
           '2025-01-01 00:00:00', -- Timestamp
           '1234567812345678', -- Cartão de Crédito (Varchar)
           'luccas@email.com', -- E-mail
           'Luccas Carneiro', -- Nome
           'senhasegura123', -- Senha (Varchar, preferencialmente hash)
           '2025-01-01 00:00:00', -- Timestamp
           'João Monlevade', -- Cidade
           1, -- Status (Integer)
           'Ativo' -- Status Name (Varchar)
       );

INSERT INTO public.tb_users (
    id,
    created_at,
    credit_card_number,
    email,
    name,
    password,
    updated_at,
    city,
    status,
    status_name
)
VALUES (
           '9a0b0e6d-3c4e-4c77-9c4d-3b4f5e9e7a2b', -- UUID Válido
           '2025-01-01 00:00:00', -- Timestamp
           '1234567812345678', -- Cartão de Crédito (Varchar)
           'joao@email.com', -- E-mail
           'Joao Silva', -- Nome
           'senhasegura123', -- Senha (Varchar, preferencialmente hash)
           '2025-01-01 00:00:00', -- Timestamp
           'João Monlevade', -- Cidade
           1, -- Status (Integer)
           'Ativo' -- Status Name (Varchar)
       );

INSERT INTO public.role (name) VALUES
('ADMIN'),
('USER');

INSERT INTO public."user" (email, password_hash, full_name, created_at, updated_at) VALUES
('alice@example.com', 'hashed_password_1', 'Alice Johnson', now(), now()),
('bob@example.com', 'hashed_password_2', 'Bob Smith', now(), now()),
('carol@example.com', 'hashed_password_3', 'Carol White', now(), now()),
('dave@example.com', 'hashed_password_4', 'Dave Brown', now(), now()),
('eve@example.com', 'hashed_password_5', 'Eve Davis', now(), now());

INSERT INTO public.user_role (user_id, role_id) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2);

INSERT INTO public.address (street, city, zip, state, country, user_id) VALUES
('123 Main St', 'New York', '10001', 'NY', 'USA', 1),
('456 Elm St', 'Los Angeles', '90001', 'CA', 'USA', 2),
('789 Oak St', 'Chicago', '60601', 'IL', 'USA', 3),
('101 Pine St', 'Houston', '77001', 'TX', 'USA', 4),
('202 Maple St', 'Miami', '33101', 'FL', 'USA', 5);

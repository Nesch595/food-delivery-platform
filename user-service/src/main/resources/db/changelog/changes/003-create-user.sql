CREATE TABLE public."user" (
	id bigserial NOT NULL,
	email varchar(255) NOT NULL,
	password_hash varchar NOT NULL,
	full_name varchar NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_unique UNIQUE (email)
);
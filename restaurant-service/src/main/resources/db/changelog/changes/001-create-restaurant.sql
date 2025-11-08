CREATE TABLE public.restaurant (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	cuisine varchar NOT NULL,
	address varchar NOT NULL,
	CONSTRAINT restaurant_pk PRIMARY KEY (id)
);
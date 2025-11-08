CREATE TABLE public."role" (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT role_pk PRIMARY KEY (id),
	CONSTRAINT role_unique UNIQUE (name)
);
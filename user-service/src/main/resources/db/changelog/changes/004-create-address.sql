CREATE TABLE public.address (
	id bigserial NOT NULL,
	street varchar NOT NULL,
	city varchar NOT NULL,
	zip varchar NOT NULL,
	state varchar NULL,
	country varchar NULL,
	user_id int8 NOT NULL,
	CONSTRAINT address_pk PRIMARY KEY (id)
);

ALTER TABLE public.address ADD CONSTRAINT fk_address_user FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE RESTRICT ON UPDATE CASCADE;
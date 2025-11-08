CREATE TABLE public.dish (
	id bigserial NOT NULL,
	"name" varchar NOT NULL,
	description text NOT NULL,
	price int4 NOT NULL,
	image_url varchar NOT NULL,
	restaurant_id int8 NOT NULL,
	CONSTRAINT dish_pk PRIMARY KEY (id)
);

ALTER TABLE public.dish ADD CONSTRAINT restaurant_dish_fk FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id) ON DELETE RESTRICT ON UPDATE CASCADE;
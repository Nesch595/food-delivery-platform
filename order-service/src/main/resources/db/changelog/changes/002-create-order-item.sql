CREATE TABLE public.order_item (
	id bigserial NOT NULL,
	order_id int8 NOT NULL,
	dish_id int8 NOT NULL,
	quantity int4 DEFAULT 1 NOT NULL,
	price int4 NOT NULL,
	CONSTRAINT order_item_pk PRIMARY KEY (id)
);
ALTER TABLE public.order_item ADD CONSTRAINT order_item_fk FOREIGN KEY (order_id) REFERENCES public."order"(id) ON DELETE RESTRICT ON UPDATE CASCADE;
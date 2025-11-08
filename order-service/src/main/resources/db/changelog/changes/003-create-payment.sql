CREATE TABLE public.payment (
	id bigserial NOT NULL,
	order_id int8 NOT NULL,
	"method" varchar NOT NULL,
	amount int4 DEFAULT 1 NOT NULL,
	status varchar NOT NULL,
	CONSTRAINT payment_pk PRIMARY KEY (id)
);

ALTER TABLE public.payment ADD CONSTRAINT payment_order_fk FOREIGN KEY (order_id) REFERENCES public."order"(id) ON DELETE RESTRICT ON UPDATE CASCADE;
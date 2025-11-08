CREATE TABLE public."order" (
	id bigserial NOT NULL,
	status varchar NOT NULL,
	order_date timestamptz DEFAULT now() NOT NULL,
	user_id int8 NOT NULL,
	restaurant_id int8 NOT NULL,
	total_price int4 NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY (id)
);
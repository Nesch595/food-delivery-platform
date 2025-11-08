
CREATE TABLE public.user_role (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT user_role_pk PRIMARY KEY (user_id, role_id)
);

ALTER TABLE public.user_role ADD CONSTRAINT role_role_fk FOREIGN KEY (role_id) REFERENCES public."role"(id) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE public.user_role ADD CONSTRAINT user_role_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE RESTRICT ON UPDATE CASCADE;
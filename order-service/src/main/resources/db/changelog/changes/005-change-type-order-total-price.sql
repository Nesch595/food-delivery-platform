-- Изменить тип total_price по аналогии с order_item
ALTER TABLE public.order
ALTER COLUMN total_price TYPE NUMERIC(10,2),
ALTER COLUMN total_price SET DEFAULT 0.00,
ALTER COLUMN total_price SET NOT NULL;

-- Обновить foreign key в order_item
ALTER TABLE public.order_item
DROP CONSTRAINT IF EXISTS order_item_fk;

ALTER TABLE public.order_item
ADD CONSTRAINT order_item_order_fk
FOREIGN KEY (order_id) REFERENCES public.order(id)
ON DELETE CASCADE ON UPDATE CASCADE;
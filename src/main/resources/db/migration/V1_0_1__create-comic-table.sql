CREATE TABLE public.comic (
	comic_id varchar NOT NULL,
	name varchar NOT NULL,
	alternative_name varchar NULL,
	synopsis varchar NULL,
    created_at timestamp with time zone,
    deleted_at timestamp with time zone,
    updated_at timestamp with time zone,
    deleted boolean default false,
	CONSTRAINT comic_pk PRIMARY KEY (comic_id)
);
CREATE INDEX comic_comic_id_idx ON public.comic (comic_id,name,alternative_name);

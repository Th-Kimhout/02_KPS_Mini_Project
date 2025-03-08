--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
DROP SEQUENCE public.products_id_seq;
DROP TABLE public.products;
DROP TABLE public.limit_rows;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: limit_rows; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.limit_rows (
    limits integer NOT NULL
);


ALTER TABLE public.limit_rows OWNER TO postgres;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    product_name character varying(50) NOT NULL,
    product_unit_price double precision NOT NULL,
    product_quantity integer NOT NULL,
    imported_date date DEFAULT CURRENT_DATE
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Data for Name: limit_rows; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.limit_rows (limits) FROM stdin;
3
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, product_name, product_unit_price, product_quantity, imported_date) FROM stdin;
1	Apple iPhone 14	799.99	50	2025-03-06
2	Samsung Galaxy S23	899.99	30	2025-03-06
4	Dell XPS 13 Laptop	999.99	40	2025-03-06
5	Nike Air Force 1 Sneakers	90	200	2025-03-06
6	Sony PlayStation 5	499.99	70	2025-03-06
7	Apple MacBook Pro	1299	60	2025-03-06
8	Amazon Echo Dot 5th Gen	49.99	150	2025-03-06
9	Fitbit Charge 5	179.95	90	2025-03-06
10	Canon EOS Rebel T7 Camera	499.99	80	2025-03-06
11	GoPro HERO11 Black	399.99	100	2025-03-06
12	LG 55-inch 4K OLED TV	1499.99	25	2025-03-06
13	Apple	1.2	50	\N
14	Mango	1.75	60	\N
15	Pineapple	3	30	\N
3	Kimheng	1	1	2025-03-06
\.


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 14, true);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--


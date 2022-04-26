create table base_user (
   id number(19,0) not null,
    adresse varchar2(255 char),
    baseuser_role varchar2(255 char),
    email varchar2(255 char),
    enabled number(1,0),
    first_name varchar2(255 char),
    last_name varchar2(255 char),
    locked number(1,0),
    password varchar2(255 char),
    primary key (id)
)


create table bid (
   id number(19,0) not null,
    accepted number(1,0),
    amount number(19,0),
    quantity number(19,0),
    tstamp timestamp,
    base_user_id number(19,0),
    sale_id number(19,0),
    primary key (id)
)


create table category (
   id number(19,0) not null,
    category_description varchar2(255 char),
    category_name varchar2(255 char),
    primary key (id)
)


create table confirmation_token (
   id number(19,0) not null,
    confirmed_at timestamp,
    created_at timestamp not null,
    expired_at timestamp not null,
    token varchar2(255 char) not null,
    base_user_id number(19,0) not null,
    primary key (id)
)


create table lot (
   id number(19,0) not null,
    quantity_to_sell number(19,0),
    product_id number(19,0),
    primary key (id)
)


create table product (
   id number(19,0) not null,
    benefice_price number(19,0),
    product_name varchar2(255 char),
    stock number(19,0),
    base_user_id number(19,0),
    category_id number(19,0),
    primary key (id)
)


create table room (
   id number(19,0) not null,
    room_type varchar2(255 char),
    category_id number(19,0),
    primary key (id)
)


create table room_sales (
   room_id number(19,0) not null,
    sales_id number(19,0) not null
)


create table sale (
   id number(19,0) not null,
    closed number(1,0),
    end_tstamp timestamp,
    last_refresh timestamp,
    limited number(1,0),
    multiple_offer number(1,0),
    revocable number(1,0),
    start_price number(19,0),
    start_tstamp timestamp,
    lot_id number(19,0),
    primary key (id)
)


create table sale_bids (
   sale_id number(19,0) not null,
    bids_id number(19,0) not null
)


alter table room_sales
   add constraint one_sale_in_room unique (sales_id)


alter table sale_bids
   add constraint bid_unique unique (bids_id)


alter table bid
   add constraint fk_bid_user
   foreign key (base_user_id)
   references base_user


alter table bid
   add constraint fk_bid_sale
   foreign key (sale_id)
   references sale


alter table confirmation_token
   add constraint fk_token_user
   foreign key (base_user_id)
   references base_user


alter table lot
   add constraint fk_lot_product
   foreign key (product_id)
   references product


alter table product
   add constraint fk_product_user
   foreign key (base_user_id)
   references base_user


alter table product
   add constraint fk_product_category
   foreign key (category_id)
   references category


alter table room
   add constraint fk_room_category
   foreign key (category_id)
   references category


alter table room_sales
   add constraint fk_room_sale_
   foreign key (sales_id)
   references sale


alter table room_sales
   add constraint fk_sale_room_
   foreign key (room_id)
   references room


alter table sale
   add constraint fk_sale_lot
   foreign key (lot_id)
   references lot


alter table sale_bids
   add constraint fk_sale_bid_
   foreign key (bids_id)
   references bid


alter table sale_bids
   add constraint fk_bid_sale_
   foreign key (sale_id)
   references sale

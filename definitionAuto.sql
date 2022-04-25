   
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
Hibernate: 
    
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
Hibernate: 
    
    create table category (
       id number(19,0) not null,
        category_description varchar2(255 char),
        category_name varchar2(255 char),
        primary key (id)
    )
Hibernate: 
    
    create table confirmation_token (
       id number(19,0) not null,
        confirmed_at timestamp,
        created_at timestamp not null,
        expired_at timestamp not null,
        token varchar2(255 char) not null,
        base_user_id number(19,0) not null,
        primary key (id)
    )
Hibernate: 
    
    create table lot (
       id number(19,0) not null,
        quantity_to_sell number(19,0),
        product_id number(19,0),
        primary key (id)
    )
Hibernate: 
    
    create table product (
       id number(19,0) not null,
        benefice_price number(19,0),
        product_name varchar2(255 char),
        stock number(19,0),
        base_user_id number(19,0),
        category_id number(19,0),
        primary key (id)
    )
Hibernate: 
    
    create table room (
       id number(19,0) not null,
        room_type varchar2(255 char),
        category_id number(19,0),
        primary key (id)
    )
Hibernate: 
    
    create table room_sales (
       room_id number(19,0) not null,
        sales_id number(19,0) not null
    )
Hibernate: 
    
    create table sale (
       id number(19,0) not null,
        closed number(1,0),
        end_tstamp timestamp,
        limited number(1,0),
        multiple_offer number(1,0),
        revocable number(1,0),
        start_price number(19,0),
        start_tstamp timestamp,
        lot_id number(19,0),
        primary key (id)
    )
Hibernate: 
    
    create table sale_bids (
       sale_id number(19,0) not null,
        bids_id number(19,0) not null
    )
Hibernate: 
    
    alter table room_sales 
       add constraint UK_44m8fe2l878afsjixsaqj6i3 unique (sales_id)
Hibernate: 
    
    alter table sale_bids 
       add constraint UK_ih42o46u131agxnvgagqvrbuk unique (bids_id)
Hibernate: 
    
    alter table bid 
       add constraint FKhjbk0no9t45q9srhpg0jwm2yr 
       foreign key (base_user_id) 
       references base_user
Hibernate: 
    
    alter table bid 
       add constraint FK6bw6xdyc0157l3dmfnm50yq0g 
       foreign key (sale_id) 
       references sale
Hibernate: 
    
    alter table confirmation_token 
       add constraint FKmb8es901lk3tlts6uafdoy8mw 
       foreign key (base_user_id) 
       references base_user
Hibernate: 
    
    alter table lot 
       add constraint FKtbrbv1dwv55qso6v5idl8nb8r 
       foreign key (product_id) 
       references product
Hibernate: 
    
    alter table product 
       add constraint FK3vqe6s47voyoj196lckrx3hr3 
       foreign key (base_user_id) 
       references base_user
Hibernate: 
    
    alter table product 
       add constraint FK1mtsbur82frn64de7balymq9s 
       foreign key (category_id) 
       references category
Hibernate: 
    
    alter table room 
       add constraint FKllkgnps1iryk3347aokxwbxxm 
       foreign key (category_id) 
       references category
Hibernate: 
    
    alter table room_sales 
       add constraint FKnibltnpoyy7hlngk1ffqw8kxg 
       foreign key (sales_id) 
       references sale
Hibernate: 
    
    alter table room_sales 
       add constraint FKo71motw3fj3vrgbb21x5r1kux 
       foreign key (room_id) 
       references room
Hibernate: 
    
    alter table sale 
       add constraint FKqb9x16805p3m6uswr1vwoaggv 
       foreign key (lot_id) 
       references lot
Hibernate: 
    
    alter table sale_bids 
       add constraint FKbms5sum3kg3rhrgexvjuakmby 
       foreign key (bids_id) 
       references bid
Hibernate: 
    
    alter table sale_bids 
       add constraint FKpxhpdp28x2y4hfbbubl20mj1o 
       foreign key (sale_id) 
       references sale

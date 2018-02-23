create sequence seq_coffee
    increment by 1
    start with 1
    nomaxvalue
    nocycle
    cache 10;

create or replace public synonym seq_coffee for seq_coffee;

-- Table --
create table coffee
(
    seqnr           number not null
    , name      varchar2(200) not null
    , description      varchar2(2000)
    , price         number
    , primary key (seqnr) using index tablespace edb_index
)

create or replace public synonym coffee for coffee;
create or replace public synonym SEQ_coffee for SEQ_coffee;

-------------------------------------------------

create sequence seq_Orders
    increment by 1
    start with 1
    nomaxvalue
    nocycle
    cache 10;

create or replace public synonym seq_Orders for seq_Orders;


-- Table --
create table Orders
(
    seqnr           number not null
    , customername      varchar2(200) not null
    , customeraddress      varchar2(200) not null
    , phone      varchar2(200) not null
    , status         number
    , price         number
    , primary key (seqnr) using index tablespace edb_index
)


create or replace public synonym Orders for Orders;
create or replace public synonym SEQ_Orders for SEQ_Orders;

-------------------------------------------------

create sequence seq_OrderItem
    increment by 1
    start with 1
    nomaxvalue
    nocycle
    cache 10;

create or replace public synonym seq_OrderItem for seq_OrderItem;

-- Table --
create table OrderItem
(
    seqnr           number not null
    , coffee number
    , "order"         number
    , quantity         number
    , primary key (seqnr) using index tablespace edb_index
)

create or replace public synonym OrderItem for OrderItem;
create or replace public synonym SEQ_OrderItem for SEQ_OrderItem;

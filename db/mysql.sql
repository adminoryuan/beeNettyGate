te table bee_collectioninfo
(
    id          int auto_increment
        primary key,
    sys_memory  varchar(32) null,
    swap_memory varchar(32) null,
    disk        int         null,
    sys_cpu     varchar(32) null,
    us_cpu      varchar(32) null,
    io_cpu      varchar(32) null,
    userid      int         null,
    node        varchar(64) null,
    upitime     datetime    null
);

create table bee_node
(
    id         int auto_increment
        primary key,
    userid     int         null,
    Node       varchar(64) null,
    main_board text        null,
    disk       varchar(32) null,
    mem        varchar(32) null,
    cpu_count  int         null,
    cpu_core   int         null,
    date       datetime    null
);

create table bee_processinfo
(
    cid     int         null,
    pid     int         null,
    user    varchar(32) null,
    command varchar(64) null,
    mem     varchar(32) null,
    cpu     varchar(16) null
);

create table bee_user
(
    id       int auto_increment
        primary key,
    admin    varchar(32)  null,
    password varchar(32)  null,
    apikey   varchar(128) null,
    phone    varchar(32)  null
);

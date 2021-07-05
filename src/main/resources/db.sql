Create table users (
    id int not null auto_increment primary key ,
    name varchar(255) not null ,
    last_name varchar(255) not null,
    age integer not null,
    email varchar(255) not null unique,
    password varchar(255) not null
) engine InnoDB;

 CREATE table roles (
     id int not null auto_increment primary key unique ,
     name varchar(100) not null unique
 ) ENGINE InnoDB;

CREATE table user_roles (
    user_id int not null ,
    role_id int not null ,

    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (role_id) references roles(id),

    UNIQUE (user_id, role_id)
) ENGINE InnoDB;

INSERT into users values (1000,'Art','Mekss','29','art1@mail.ru','123');
# INSERT into users values (2,'Leo','Bogd','157','leo@mail.ru','1234');
# INSERT into users values (3,'Lia','12345');
insert into roles values (1,'ROLE_USER');
insert into roles values (2,'ROLE_ADMIN');

insert into user_roles values (1000,2);
insert into user_roles values (1000,1);
# insert into user_roles values (2,1);

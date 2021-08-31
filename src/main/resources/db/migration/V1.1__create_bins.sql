create table if not exists app_bins  (id bigint not null, contact_address varchar(255), contact_name varchar(255), contact_phone varchar(255), created_at timestamp, is_full integer not null, latitude float, longitude float, status integer not null, updated_at timestamp, primary key (id));

INSERT INTO app_bins (id, contact_name, contact_address, contact_phone, longitude, latitude,status,is_full, created_at, updated_at)
 values(1,'kwamekert','dansoman','+233244151506', '-0.2362','5.6063',1,0,'2021-08-30 12:37:37.385','2021-08-30 12:37:37.385');
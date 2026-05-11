insert into roles(nombre) values ('ROLE_ADMIN') on conflict do nothing;
insert into roles(nombre) values ('ROLE_COBRANZA') on conflict do nothing;
insert into roles(nombre) values ('ROLE_LECTOR') on conflict do nothing;
alter table TAG add constraint ck_tag_not_virgule check (nametag not like '%,,,%');
alter table TAG add constraint ck_tag_not_pts_virgule check (nametag not like '%;;;%');

alter table COLOR add constraint ck_color_not_pts_virgule check (namecolor not like '%;;;%');


alter table CLOTH_SIZE add constraint ck_CLOTH_SIZE_not_virgule check (namesize not like '%,,,%');
alter table CLOTH_SIZE add constraint ck_CLOTH_SIZE_not_pts_virgule check (namesize not like '%;;;%');

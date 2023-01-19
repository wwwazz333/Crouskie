alter table tag add constraint ck_tag_not_virgule check (nametag not like '%,,,%');
alter table tag add constraint ck_tag_not_pts_virgule check (nametag not like '%;;;%');

alter table color add constraint ck_color_not_pts_virgule check (namecolor not like '%;;;%');


alter table cloth_size add constraint ck_cloth_size_not_virgule check (namesize not like '%,,,%');
alter table cloth_size add constraint ck_cloth_size_not_pts_virgule check (namesize not like '%;;;%');

alter table book add column isBestSeller boolean;
update book
set isBestSeller=true;
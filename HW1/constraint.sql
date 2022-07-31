alter table students
    add constraint special_symbols_forbidden
        check ((name !~~ '%@%') AND (name !~~ '%#%') AND (name !~~ '%$%'));

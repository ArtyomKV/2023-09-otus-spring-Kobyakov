merge into authors (full_name)
    key (full_name)
    values ('Author_1'),
           ('Author_2'),
           ('Author_3');

merge into genres (name)
    key (name)
    values ('Genre_1'),
           ('Genre_2'),
           ('Genre_3');

merge into books (title, author_id, genre_id)
    key (title)
    values ('BookTitle_1', 1, 1),
           ('BookTitle_2', 2, 2),
           ('BookTitle_3', 3, 3);
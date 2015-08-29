SELECT movie_id, count(*) FROM primefaces.user_favorite_movie group by movie_id order by count(*) desc limit 5;

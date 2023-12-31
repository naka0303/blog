CREATE TABLE IF NOT EXISTS registered_user (
	user_id SERIAL NOT NULL PRIMARY KEY
	, username VARCHAR (255) NOT NULL
	, age INTEGER
	, email VARCHAR (255) NOT NULL
	, password VARCHAR (255) NOT NULL
	, auth VARCHAR (255) NOT NULL
	, created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
	, updated_at TIMESTAMPTZ DEFAULT NULL
	, deleted_at TIMESTAMPTZ DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS posted_blog (
	id SERIAL NOT NULL PRIMARY KEY
	, title VARCHAR (255) NOT NULL
	, content VARCHAR (3000) NOT NULL
	, user_id INTEGER NOT NULL
	, created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
	, updated_at TIMESTAMPTZ DEFAULT NULL
	, deleted_at TIMESTAMPTZ DEFAULT NULL
);



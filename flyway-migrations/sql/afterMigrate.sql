INSERT INTO USERS
	(USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD, NEIGHBORHOOD, BIO)
VALUES
	('nickhmlee','Nick','Lee','password','Brooklyn','I love NYC'),
	('severejetlag','Fred','Test','password','Queens','I heart NYC'),
	('nicklee','Chris','Test','password','Bronx','NYC is the best'),
	('nycfan','Andy','Test','password','Manhattan','GO NYC'),
	('nhl1013','Dan','Test','password','City Island','Boo Sox');


INSERT INTO POSTS
	(TITLE, FIRST_NAME, LAST_NAME, CONTACT_DETAILS, POST_BODY)
VALUES
	('Welcome to NYC','Nick','Lee','nickhmlee@gmail.com','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec mollis tortor ut arcu interdum, in ornare justo mattis. Curabitur ullamcorper eros ut elit tristique, eget blandit purus rutrum. Vestibulum vehicula nisl sed risus tristique, sit amet aliquet eros euismod. Nulla facilisi. Aenean ac vestibulum leo. Mauris diam purus, cursus ac turpis eget, iaculis vulputate ex. Donec molestie tincidunt justo, ut fringilla est ornare non.'),
	('Hello NYC','Dan','Santos','dsantos@gmail.com','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec mollis tortor ut arcu interdum, in ornare justo mattis. Curabitur ullamcorper eros ut elit tristique, eget blandit purus rutrum. Vestibulum vehicula nisl sed risus tristique, sit amet aliquet eros euismod. Nulla facilisi. Aenean ac vestibulum leo. Mauris diam purus, cursus ac turpis eget, iaculis vulputate ex. Donec molestie tincidunt justo, ut fringilla est ornare non.'),
	('Community meeting tonight at 7pm','Test','DaBest','nhl1013@gmail.com','Integer vulputate leo ut neque commodo, eget tincidunt lacus finibus. Curabitur in imperdiet quam. Nunc vitae est neque. Donec a dui at sem molestie euismod sit amet a eros. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras convallis erat elit, ut aliquam nulla ultrices quis. Integer imperdiet nibh ut libero vestibulum lacinia.'),
	('Moving to NYC?','Nick','Lee','severejetlag@gmail.com','Fusce sit amet rhoncus lorem, ac venenatis lorem. Phasellus egestas ornare dignissim. Sed condimentum, nibh id luctus vehicula, orci augue accumsan odio, cursus tincidunt ante tellus in arcu. Nam eleifend hendrerit fermentum. In maximus mi risus, non auctor quam ultrices eget. Integer sit amet urna sit amet dolor pharetra rhoncus eget feugiat nisi. Donec quis turpis egestas leo aliquam lobortis ac eget nibh. Proin malesuada iaculis velit non lobortis. Suspendisse potenti.');


	INSERT INTO POSTS
		(TITLE, FIRST_NAME, LAST_NAME, CONTACT_DETAILS, POST_BODY, APPROVED, VERIFIED)
	VALUES
		('Construction starting April 21st','Nick','Lee','nickhmlee@gmail.com','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec mollis tortor ut arcu interdum, in ornare justo mattis. Curabitur ullamcorper eros ut elit tristique, eget blandit purus rutrum. Vestibulum vehicula nisl sed risus tristique, sit amet aliquet eros euismod. Nulla facilisi. Aenean ac vestibulum leo. Mauris diam purus, cursus ac turpis eget, iaculis vulputate ex. Donec molestie tincidunt justo, ut fringilla est ornare non.','t','f'),
		('Hello NYC','Dan','Santos','dsantos@gmail.com','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec mollis tortor ut arcu interdum, in ornare justo mattis. Curabitur ullamcorper eros ut elit tristique, eget blandit purus rutrum. Vestibulum vehicula nisl sed risus tristique, sit amet aliquet eros euismod. Nulla facilisi. Aenean ac vestibulum leo. Mauris diam purus, cursus ac turpis eget, iaculis vulputate ex. Donec molestie tincidunt justo, ut fringilla est ornare non.','t','t'),
		('Community meeting tonight at 10pm','Jay','Smith','jays@gmail.com','Integer vulputate leo ut neque commodo, eget tincidunt lacus finibus. Curabitur in imperdiet quam. Nunc vitae est neque. Donec a dui at sem molestie euismod sit amet a eros. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras convallis erat elit, ut aliquam nulla ultrices quis. Integer imperdiet nibh ut libero vestibulum lacinia.','t','f'),
		('Construction in Brooklyn','Nick','Lee','nickhmlee@gmail.com','Fusce sit amet rhoncus lorem, ac venenatis lorem. Phasellus egestas ornare dignissim. Sed condimentum, nibh id luctus vehicula, orci augue accumsan odio, cursus tincidunt ante tellus in arcu. Nam eleifend hendrerit fermentum. In maximus mi risus, non auctor quam ultrices eget. Integer sit amet urna sit amet dolor pharetra rhoncus eget feugiat nisi. Donec quis turpis egestas leo aliquam lobortis ac eget nibh. Proin malesuada iaculis velit non lobortis. Suspendisse potenti.','t','t');

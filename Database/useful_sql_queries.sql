-- Returns all the communities and the
-- community creators
SELECT c.id, c.name, c.description, u.id, 
u.user_name, u.first_name, u.last_name, u.role_id, u.email
FROM communities c
INNER JOIN users u ON c.community_owner_id = u.id;

-- Returns all the communities a given user 
-- has subscribed to
SELECT c.id, c.name, c.description, c.community_owner_id,
IF(usc.user_id  IS NULL, FALSE, TRUE) as isRegistered 
FROM communities c
LEFT JOIN user_subscribed_communities usc ON ((usc.user_id =  3) 
AND (usc.community_id = c.id));

-- Returns all the events from the communities 
-- a given user is subscribed to
SELECT e.id, e.name, e.start, e.end,
u.id, u.user_name, u.first_name, u.last_name, u.role_id, u.email, 
c.id, c.name, c.description, c.community_owner_id  FROM events e 
INNER JOIN users u ON e.event_owner_id = u.id
INNER JOIN communities c ON e.community_id = c.id
INNER JOIN user_subscribed_communities usc ON ((usc.user_id =  3) 
AND (usc.community_id = c.id));

SELECT u.id, u.user_name, u.first_name, u.last_name, u.email,
r.id, r.name FROM users u
INNER JOIN roles r ON (r.id = u.role_id);
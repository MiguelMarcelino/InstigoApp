SELECT c.id, c.name, c.description, u.id, 
u.user_name, u.first_name, u.last_name, u.role_id, u.email
FROM communities c
INNER JOIN users u ON c.community_owner_id = u.id;

SELECT c.id, c.name, c.description, u.id, 
u.user_name, u.first_name, u.last_name, u.role_id, u.email,
IF(usc.user_id  IS NULL, FALSE, TRUE) as isRegistered 
FROM communities c
LEFT JOIN users u ON (c.community_owner_id = u.id)
LEFT JOIN user_subscribed_communities usc ON ((usc.user_id =  3) 
AND (usc.community_id = c.id));

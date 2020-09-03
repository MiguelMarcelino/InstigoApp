SELECT c.id, c.name, c.description, u.id, 
u.user_name, u.first_name, u.last_name, u.role_id, u.email
FROM communities c
INNER JOIN users u ON c.community_owner_id = u.id;
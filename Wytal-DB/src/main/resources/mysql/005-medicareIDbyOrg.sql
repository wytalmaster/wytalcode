 ALTER TABLE PERSON ADD ( STATE TINYINT NOT NULL DEFAULT 0);
 
 ALTER TABLE PERSON_ADDRESS  ADD ( STATE TINYINT NOT NULL DEFAULT 0);
 
 INSERT INTO RELATION_TYPE( NAME, DESCRIPTION,CREATED,UPDATED) VALUES ('Spouse','Spouse', NOW(),NOW());
 update address_type set description = 'Work' where addresstypeid=2;
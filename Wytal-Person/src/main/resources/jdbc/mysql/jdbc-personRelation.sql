101=INSERT INTO PERSON_RELATIONSHIP (PERSONID, RELATIONID,RELATEDPERSONID) VALUES ( ?,?,?) ON DUPLICATE KEY UPDATE RELATIONID=?
102=SELECT RELATIONID,RELATEDPERSONID FROM PERSON_RELATIONSHIP WHERE PERSONID=?
103=DELETE FROM PERSON_RELATIONSHIP WHERE PERSONID=? AND RELATIONID=?
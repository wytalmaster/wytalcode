101=INSERT INTO PERSON(ORGID,FIRSTNAME, LASTNAME,MIDDLENAME, OTHERNAME,DOB,GENDER,NATIONALID,SECRETKEY,STATE,CREATED,UPDATED) VALUES(?,?,?,?,?,?,?,?,?,0,NOW(),NOW())
102=UPDATE PERSON SET FIRSTNAME=?,LASTNAME=?,MIDDLENAME=?,OTHERNAME=?,DOB=?,GENDER=?,NATIONALID=?,STATE=0, UPDATED=NOW() WHERE ID=?
103=UPDATE PERSON SET STATE=1 WHERE ID = ?
104=SELECT ID,ORGID, FIRSTNAME, LASTNAME, MIDDLENAME, OTHERNAME, DOB,GENDER,NATIONALID,SECRETKEY,CREATED,UPDATED FROM PERSON WHERE ID=? AND STATE=0


105=SELECT * FROM (SELECT P.ID,O.ORGID,O.NAME,P.FIRSTNAME, P.LASTNAME,P.DOB,P.GENDER FROM PERSON P,ORGANIZATION O WHERE P.ORGID = O.ORGID %s ORDER BY %s  %s)X LIMIT ?,?
105A=AND P.ORGID=?
105B=AND P.LASTNAME LIKE ?
105C=AND P.FIRSTNAME LIKE ?
105D=AND P.DOB=?
105E=AND P.ORGID=? AND P.LASTNAME LIKE ?
105F=AND P.ORGID=? AND P.FIRSTNAME LIKE ?
105G=AND P.DOB=? AND P.LASTNAME LIKE ?
105H=AND P.DOB=? AND P.FIRSTNAME LIKE ?
105I=AND P.ORGID=? AND P.DOB=? 
105J=AND P.ORGID=? AND P.DOB=? AND P.LASTNAME LIKE ?
105K=AND P.ORGID=? AND P.DOB=? AND P.FIRSTNAME LIKE ?


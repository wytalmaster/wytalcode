101=INSERT INTO PERSON_ADDRESS ( PERSONID,ADDRESSTYPEID,ADDRESSID,ISPRIMARY,CREATED,UPDATED,STATE) VALUES(?,?,?,?,NOW(),NOW(),0)
102=UPDATE PERSON_ADDRESS SET ADDRESSTYPEID=?,ADDRESSID=?,ISPRIMARY=?,UPDATED=NOW(),STATE=0 WHERE PESRSON_ADDRESS_ID=?
103=SELECT PESRSON_ADDRESS_ID,ADDRESSTYPEID,ADDRESSID,ISPRIMARY, CREATED,UPDATED FROM PERSON_ADDRESS WHERE PERSONID=? AND STATE=0
104=UPDATE PERSON_ADDRESS SET STATE=1 WHERE PESRSON_ADDRESS_ID=? AND PERSONID=?

101=INSERT INTO ORGANIZATION_CONTACT(ORGID,CONTACTTYPEID,VALUE,STATE,CREATED,UPDATED) VALUES(?,?,?,?,NOW(),NOW())
102=UPDATE ORGANIZATION_CONTACT SET CONTACTYPEID=?,VALUE=?, STATE=?, UPDATED=NOW() WHERE ORG_CONTACT_ID=?
103=SELECT ORG_CONTACT_ID,CONTACTTYPEID,VALUE,STATE,CREATED,UPDATED FROM ORGANIZATION_CONTACT WHERE ORGID=? AND STATE=0
104=UPDATE ORGANIZATION_CONTACT SET STATE =1 WHERE ORG_CONTACT_ID=?

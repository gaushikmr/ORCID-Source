How to add a new identifier type
================================

There are steps:
- Add the database row
- Add the liquibase script to db-master.xml
- Update the test
- Add the description (optional)
- Add it to a resolver for link checking (optional)
- Add a normalizer (optional)

Add the database row using a liquibase script in this directory. 
(also add the primary_use column - either work or funding (for now) 
(also add the id_resolution_prefix column) 
Example: See identifier-type.pdb.xml

Update orcid-persistence/src/main/resources/db-master.xml.  
Example: `<include file="/db/updates/identifier-type-pdb.xml" />`

Add the description (optional). Add a line to orcid-core/src/main/resources/i18n/identifiers_en.properties.  
Example: `org.orcid.jaxb.model.record.WorkExternalIdentifierType.wosuid=wosuid\: Web of Science™ identifier`

Update the org.orcid.core.manager.IdentifierTypeManagerTest test. Add the new id to the list: 
`private List<String> v2Ids = Arrays.asList(new String[]{"pdb","kuid",”your-new-type”});`

To enable link checking, add it to org.orcid.core.utils.v3.identifiers.resolvers.Http200Resolver (only if it reliably returns a 200 if found)

Add a Normalizer in the org.orcid.core.utils.v3.identifiers package if required

Solr indexing will happen automatically.

Add the user entered id link translation (optional). Add a function to the typeMap in /orcid-web/src/main/webapp/static/javascript/script.js 	
example:	
	typeMap['kuid'] = function (id) {	
		return 'http://koreamed.org/SearchBasic.php?RID=' + encodeURIComponent(id);	
	};	
	









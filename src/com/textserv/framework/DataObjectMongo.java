package com.textserv.framework;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import com.mongodb.DBObject;

public class DataObjectMongo extends DataObject implements BSONObject, DBObject {

	private static final long serialVersionUID = -1196428170686800412L;
    private boolean _isPartialObject;

    public static String getMongoId(DataObject dataobject) {
    	String id;
    	if ( dataobject.get("_id") instanceof DataObject ) {
    		DataObject dId = dataobject.getDataObject("_id");
    		id = dId.getString("$oid");
    	} else {
    		id = dataobject.getString("_id");
    	}
    	return id;
    }
    @Override
	public boolean isPartialObject() {
        return _isPartialObject;
	}

	@Override
	public void markAsPartialObject() {
        _isPartialObject = true;
	}

	@Override
	public boolean containsField(String name) {
		return super.containsKey(name);
	}

	@Override
	public boolean containsKey(String name) {
        return containsField(name);
	}

	@Override
	public Object get(String name) {
		return super.get(name);
	}

	@Override
	public Object removeField(String name) {
        return remove( name );
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void putAll(Map m) {
		for ( Map.Entry entry : (Set<Map.Entry>)m.entrySet() ){
            put( entry.getKey().toString() , entry.getValue() );
        }
	}
	 
	@SuppressWarnings("rawtypes")
	@Override
	public Map toMap() {
        return new LinkedHashMap<String,Object>(this);
	}

	@Override
	public void putAll(BSONObject o) {
		for ( String k : o.keySet() ){
			put( k , o.get( k ) );
		}
	}
}

package concept.CentralAutomacao.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlTimestampAdapter extends XmlAdapter<String, Timestamp> {
	
	public String marshal(java.sql.Timestamp d) {
		return d.toString();
	}

	public Timestamp unmarshal(String v) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.sql.Timestamp sqlTimestamp = null;
		try {
			java.util.Date convertedDate = dateFormat.parse(v);
			sqlTimestamp = new java.sql.Timestamp(convertedDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return sqlTimestamp;
	}
}
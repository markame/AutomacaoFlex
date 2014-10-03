package concept.CentralAutomacao.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlDateAdapter extends XmlAdapter<String, Date> {
	public String marshal(java.sql.Date d) {
		return d.toString();
	}

	public Date unmarshal(String v) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try {
			java.util.Date convertedDate = dateFormat.parse(v);
			sqlDate = new java.sql.Date(convertedDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
}
package concept.CentralAutomacao.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Util {

//	private static final SimpleDateFormat formatoYYYYMMDDHifen = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SimpleDateFormat formatoDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");

	private static final SimpleDateFormat formatoDDMMYYYYSB = new SimpleDateFormat("ddMMyyyy");

	private static final SimpleDateFormat formatoMMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");

	private static final SimpleDateFormat formatoYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");

	private static final SimpleDateFormat formatoYYYYMMDDSB = new SimpleDateFormat("yyyyMMdd");

	private static final SimpleDateFormat formatoDDMMYYYYHHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private static final SimpleDateFormat formatoDDMMYYYYHHmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private static final SimpleDateFormat formatoHHmmDDMMYYYY = new SimpleDateFormat("HH:mm dd/MM/yyyy");

	private static final SimpleDateFormat formatoYYYYMMDDHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final SimpleDateFormat formatoHHmm = new SimpleDateFormat("HH:mm");

	private static final SimpleDateFormat formatoHH = new SimpleDateFormat("HH");

	private static final SimpleDateFormat formatomm = new SimpleDateFormat("mm");

	private static final SimpleDateFormat formatoDDMM = new SimpleDateFormat("dd/MM");

	private static final NumberFormat formatter = new DecimalFormat("#.##");

//	private static final long _5minutos = 5 * 60 * 1000;

	public static final int horasEmMilisegundos = 1000 * 60 * 60;

	public static final int diaEmMilissegundos = 24 * horasEmMilisegundos;

	public static final int MinutoEmMilisegundos = 1000 * 60;

	private static int pinSTART = 10000;
	private static int pinEND = 99999;

	public static Timestamp obterDataHoraDaString(String strDataHora) {
		try {
			return new Timestamp(formatoDDMMYYYYHHmmss.parse(strDataHora).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static Timestamp obterTimesamptDaStringYYYYMMDDHHmmss(String strDataHora) {
		try {
			return new Timestamp(formatoYYYYMMDDHHmmss.parse(strDataHora).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static Timestamp obterTimesamptDaStringDDMMYYYYHHmm(String strDataHora) {
		try {
			return new Timestamp(formatoDDMMYYYYHHmm.parse(strDataHora).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static String obterDataHoraAtual() {
		return formatoDDMMYYYYHHmmss.format((java.util.Date) new Timestamp(System.currentTimeMillis()));
	}

	public static String formatarTimestampHHmm(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoHHmm.format((java.util.Date) timestamp);
		}
	}

	public static String formatarTimestampHH(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoHH.format((java.util.Date) timestamp);
		}
	}

	public static String formatarTimestampMM(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatomm.format((java.util.Date) timestamp);
		}
	}

	public static String formatarDateHHmm(java.util.Date data) {
		if (data == null) {
			return null;
		} else {
			return formatoHHmm.format(data);
		}
	}

	public static String formatarTimeHHmm(Time hora) {
		if (hora == null) {
			return null;
		} else {
			return formatoHHmm.format(hora);
		}
	}

	public static String formatarTimestampDDMM(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoDDMM.format((java.util.Date) timestamp);
		}
	}

	public static String formatarTimestampDDMMYYYY(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoDDMMYYYY.format((java.util.Date) timestamp);
		}
	}

	public static String formatarDateDDMMYYYY(Date data) {
		if (data == null) {
			return null;
		} else {
			return formatoDDMMYYYY.format(data);
		}
	}

	public static String formatarDateDDMM(Date data) {
		if (data == null) {
			return null;
		} else {
			return formatoDDMM.format(data);
		}
	}

	public static String formatarDateYYYYMMDD(Date data) {
		if (data == null) {
			return null;
		} else {
			return formatoYYYYMMDD.format(data);
		}
	}

	public static String formatarDateYYYYMMDDSB(Date data) {
		if (data == null) {
			return null;
		} else {
			return formatoYYYYMMDDSB.format(data);
		}
	}

	public static String formatarTimestampDDMMYYYYHHmmss(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoDDMMYYYYHHmmss.format((java.util.Date) timestamp);
		}
	}

	public static String formatarTimestampDDMMYYYYHHmm(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoDDMMYYYYHHmm.format((java.util.Date) timestamp);
		}
	}

	public static String formatarTimestampHHmmDDMMYYYY(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoHHmmDDMMYYYY.format((java.util.Date) timestamp);
		}
	}

	public static String formatarTimestampYYYYMMDDSB(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoYYYYMMDDSB.format((java.util.Date) timestamp);
		}
	}

	public static String formatarTimestampYYYYMMDD(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return formatoYYYYMMDD.format((java.util.Date) timestamp);
		}
	}

	public static boolean stringVazia(String valor) {
		if (valor == null || valor.length() == 0 || valor.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static String formatarStringVazia(String valor) {
		if (stringVazia(valor)) {
			return "";
		}
		return valor;
	}

	public static void incluirClausulaNoWhere_AND(StringBuffer select, String clausula) {

		if (select.toString().toUpperCase().lastIndexOf("WHERE") > select.toString().toUpperCase().lastIndexOf("FROM")) {
			select.append(" AND (");
			select.append(clausula);
			select.append(") ");
		} else {
			select.append(" WHERE (");
			select.append(clausula);
			select.append(") ");
		}
	}

	public static void incluirClausulaNoWhere_OR(StringBuffer select, String clausula) {

		if (select.toString().toUpperCase().lastIndexOf("WHERE") > select.toString().toUpperCase().lastIndexOf("FROM")) {
			select.append(" OR (");
			select.append(clausula);
			select.append(") ");
		} else {
			select.append(" WHERE (");
			select.append(clausula);
			select.append(") ");
		}
	}

	public static void incluirCampoNoInsert(StringBuffer insert, String campo) {
		String str = insert.toString().toUpperCase();
		int posicaoParentese = str.substring(0, str.indexOf("VALUES")).indexOf(')');
		insert.insert(posicaoParentese, ", " + campo + " ");
		str = insert.toString().toUpperCase();
		posicaoParentese = str.lastIndexOf(')');
		insert.insert(posicaoParentese, " , ? ");
	}

	public static void incluirCampoNoUpdate(StringBuffer update, String campo) {
		String str = update.toString().toUpperCase();
		update.insert(str.lastIndexOf("WHERE"), ", " + campo + " = ? ");
	}

	public static long converterMsParaMinutosInteiros(long qtdMilisegundos) {
		return Math.round(qtdMilisegundos / 1000.0 / 60.0);
	}

	/**
	 * Converte uma quantidade de milisegundos para um numero de minutos ou
	 * horas e minutos.
	 * 
	 * @param qtdMilisegundosParados
	 * @return
	 */
	public static String formatarTempo(long qtdMilisegundosParados) {
		long qtdMinutosParados = Util.converterMsParaMinutosInteiros(qtdMilisegundosParados);
		qtdMinutosParados = (qtdMinutosParados < 0 ? 0 : qtdMinutosParados);
		if (qtdMinutosParados < 60) {
			return qtdMinutosParados + " min";
		}
		if (qtdMinutosParados < 1440) {
			long qtdHoras = qtdMinutosParados / 60;
			long qtdMinutos = qtdMinutosParados % 60;
			return qtdHoras + " h " + qtdMinutos + " min";
		} else {
			long qtdDias = qtdMinutosParados / (24 * 60);
			long qtdHoras = (qtdMinutosParados % (24 * 60)) / 60;
			long qtdMinutos = (qtdMinutosParados % (24 * 60)) % 60;
			return qtdDias + "d " + qtdHoras + "h " + qtdMinutos + "min";
		}
	}

	public static long formatarTempoEmMinutos(long qtdMilisegundosParados) {
		long qtdMinutosParados = Util.converterMsParaMinutosInteiros(qtdMilisegundosParados);
		return (qtdMinutosParados < 0 ? 0 : qtdMinutosParados);

	}

	public static String formatarNumero2Decimais(double numero) {
		return formatter.format(numero);
	}

	public static String formatarPercentual(double numero) {
		return formatter.format(numero * 100) + "%";
	}

	public static Date obterDateDaStringYYYYMMDD(String datastr) throws ParseException {
		return new java.sql.Date(formatoDDMMYYYY.parse(datastr).getTime());
	}

	public static Date obterDateDaStringDDMMYYYY(String datastr) throws ParseException {
		return new java.sql.Date(formatoDDMMYYYY.parse(datastr).getTime());
	}

	public static Date obterDateDaStringDDMMYYYYSB(String datastr) throws ParseException {
		return new java.sql.Date(formatoDDMMYYYYSB.parse(datastr).getTime());
	}

	public static Date obterDateDaStringMMDDYYYY(String datastr) throws ParseException {
		return new java.sql.Date(formatoMMDDYYYY.parse(datastr).getTime());
	}

	public static Time obterTimeDaStringHHmm(String horaStr) throws ParseException {
		if (horaStr == null || horaStr.trim().equals("")) {
			return null;
		}
		return new java.sql.Time(formatoHHmm.parse(horaStr).getTime());
	}

	public static Time obterTimeDaStringHH(String horaStr) throws ParseException {
		return new java.sql.Time(formatoHHmm.parse(horaStr + ":00").getTime());
	}

	public static Time obterTimeDoIntHH(int hora) throws ParseException {
		return new java.sql.Time(formatoHHmm.parse(hora + ":00").getTime());
	}

	public static Time obterTimeDoTimestamp(Timestamp dataHora) throws ParseException {
		return new java.sql.Time(dataHora.getTime());
	}

	public static String tratarCampoNulo(String txtValor) {
		if (txtValor == null) {
			return "";
		}
		return txtValor;
	}

	public static String gerarPINSenha() {
		long range = (long) pinEND - (long) pinSTART + 1;
		Random aRandom = new Random();
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + pinSTART);
		return String.valueOf(randomNumber);
	}

	public static String converterHHMM(long qtdMilisegundosParados) {
		long qtdMinutos = Util.converterMsParaMinutosInteiros(qtdMilisegundosParados);
		qtdMinutos = (qtdMinutos < 0 ? 0 : qtdMinutos);
		if (qtdMinutos < 60) {
			if (qtdMinutos < 10) {
				return "00:0" + qtdMinutos;
			} else {
				return "00:" + qtdMinutos;
			}
		} else {
			long tHoras = qtdMinutos / 60;
			long tMinutos = qtdMinutos % 60;
			String strHoras = "";
			String strMinutos = "";
			if (tHoras < 10) {
				strHoras = "0" + tHoras;
			} else {
				strHoras = String.valueOf(tHoras);
			}
			if (tMinutos < 10) {
				strMinutos = "0" + tMinutos;
			} else {
				strMinutos = String.valueOf(tMinutos);
			}
			return strHoras + ":" + strMinutos;
		}

	}

	public static String colocaPontos(String in, int jump) {
		String out = "";
		for (int i = 1; i < in.length() + 1; i++) {
			out += in.charAt(i - 1);
			if (i != 0 && i % jump == 0)
				out += ".";
		}
		return out;
	}

	public static boolean verificaSeEhHoje(Timestamp t) {
		if (t.getTime() / diaEmMilissegundos == (new java.util.Date()).getTime() / diaEmMilissegundos)
			return true;
		return false;
	}
	
	public static float arredondarNumero2Decimais(double numero) {
		return Float.parseFloat(formatter.format(numero));
	}
	
	public static String retiraEspeciais(String str){
		CharSequence cs = new StringBuilder(str.toUpperCase());
		return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static void main(String[] args) {
		final Calendar date = Calendar.getInstance();
		date.set(2014, Calendar.JANUARY, 31);
		date.add(Calendar.MONTH, 1);
		System.out.println(Util.formatarDateDDMMYYYY(new java.sql.Date(date.getTime().getTime())));
	}
}
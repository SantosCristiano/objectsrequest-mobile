package br.com.diebold.partsrequest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class DateTime {

    public static String Actually(String format) {

        Date date = new Date(System.currentTimeMillis());
        String dateString = new SimpleDateFormat(format).format(date);

        return dateString;

    }

    public static Calendar ActuallyCalendar() {

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(new Date(System.currentTimeMillis()));

        return dateCalendar;

    }

    public static Date ActuallyDate() {

        return new Date(System.currentTimeMillis());

    }

    public static Date calendarToDate(Calendar calendar) {

        return new Date(calendar.getTimeInMillis());

    }

    public static Date StringToDate(String value, String format) {

        try {
            if (value.contains("T")) {
                value = value.replace("-", "/").replace("T", " ").trim();
                String ano = value.substring(0, 4);
                String mes = value.substring(5, 7);
                String dia = value.substring(8, 10);
                String anoF = dia + "/" + mes + "/" + ano;
                value = value.replace(value.substring(0, 10), anoF);
            }
            SimpleDateFormat curFormater = new SimpleDateFormat(format);
            Date dateReturn = curFormater.parse(value);
            return dateReturn;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }

    public static Calendar dateToCalendar(Date date) {

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        return dateCalendar;

    }

    public static String CalendarToString(Calendar calendar, String format) {

        try {
            Date date = calendarToDate(calendar);
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return "";
        }

    }

    public static String DateToString(Date date, String format) {

        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return "";
        }

    }


    public static String EuaToBraString (String data, String DateFormatString) {
        // 9/23/2021 7:12:21 PM

        Integer dia = 0;
        Integer mes = 0;
        Integer ano = 0;
        Integer hora = 0;
        Integer minuto = 0;
        Integer segundos = 0;
        Date novaData = null;
        String[] diaMesAno = null;
        String[] horaMinSeg = null;
        String manhaTarde = "";

        try {


            String[] separandoTipos = data.split(" ");


            diaMesAno = separandoTipos[0].split("/");
            horaMinSeg = separandoTipos[1].split(":");
            manhaTarde = separandoTipos[2].trim();


            dia = Integer.valueOf(diaMesAno[1]);
            mes = Integer.valueOf(diaMesAno[0]);
            ano = Integer.valueOf(diaMesAno[2]);

            hora = Integer.valueOf(horaMinSeg[0]);
            minuto = Integer.valueOf(horaMinSeg[1]);
            segundos = Integer.valueOf(horaMinSeg[2]);


            if(manhaTarde.equals("PM")){
                hora = hora + 12;
            }

            novaData = new Date( ano-1900, mes-1, dia, hora, minuto, segundos);

        } catch (Exception e) {

            if(manhaTarde.equals("PM")){
                hora = hora - 12;
            }

            novaData = new Date( ano-1900, mes-1, dia, hora, minuto, segundos);

        }


        return DateToString(novaData, DateFormatString);


    }



    public static Calendar StringToCalendar(String value, String formatt) {
        if (value == null)
           return null;
        try {
            if (value.contains("T")) {
                value = value.replace("-", "/").replace("T", " ").trim();
                String ano = value.substring(0, 4);
                String mes = value.substring(5, 7);
                String dia = value.substring(8, 10);
                String anoF = dia + "/" + mes + "/" + ano;
                value = value.replace(value.substring(0, 10), anoF);
            }
            SimpleDateFormat curFormater = new SimpleDateFormat(formatt);
            Date dateObj = curFormater.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateObj);
            return calendar;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }

    public static boolean isCronologico(Date inicio, Date fim) {

        return fim.after(inicio);

    }


    public static boolean isDataFutura(Date data) {
        if (data != null) {
            return data.after(ActuallyDate());
        } else {
            return false;
        }

    }

    public static boolean estaNoIntervalo(Date data, int dias) {

        Date atual = ActuallyDate();

        long diff = data == null? 0 :  atual.getTime() - data.getTime();

        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays > dias;
    }

    public static boolean estaNoIntervaloMin(Date data, int minutes) {

        Date atual = ActuallyDate();

        long diff = atual.getTime() - data.getTime();

        long diffMinutes = diff / (60 * 1000) % 60;

        return diffMinutes >= minutes;
    }

}

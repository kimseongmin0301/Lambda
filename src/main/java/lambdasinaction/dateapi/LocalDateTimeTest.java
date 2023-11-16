package lambdasinaction.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class LocalDateTimeTest {

    public static void main(String[] args) {
        //LocalDate 사용
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate today2 = LocalDate.now();
        System.out.println(today.compareTo(today2)); //0

        System.out.println("년월일 : " + today.getYear()+ " " +
                today.getMonth() + " " + today.getMonthValue()+" "+
                today.getDayOfMonth());
        System.out.println("요일 :" + today.getDayOfWeek() + " " +
                today.getDayOfWeek().getValue());

        //특정 날짜를 지정해서 LocalDate 생성
        LocalDate endDay = LocalDate.of(2023, 12, 31);
        System.out.println("현재 기준 몇일 남아 있는지 "+ today.until(endDay, ChronoUnit.DAYS));
        System.out.println("현재 기준 몇주 남아 있는지 "+ today.until(endDay, ChronoUnit.WEEKS));
        System.out.println("현재 기준 몇달 남아 있는지 "+ today.until(endDay, ChronoUnit.MONTHS));

        System.out.println("현재 기준 2개월 후 : "+ today.plusMonths(2));
        System.out.println(DayOfWeek.SATURDAY.plus(3));

        //LocalTime 사용
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println("시분초 : " + now.getHour()+ "-" +
                now.getMinute()+"-"+now.getSecond()+"-"+now.getNano());

        LocalTime bedTime = LocalTime.of(23, 30);
        LocalTime wakeTime = bedTime.plusHours(7);
        System.out.println("기상 시간 " + wakeTime);

        //LocalDateTime 사용
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt);

        LocalDate d = dt.toLocalDate();
        System.out.println(d);
        LocalTime t = dt.toLocalTime();
        System.out.println(t);

        LocalDateTime dt2 =
                LocalDateTime.of(2018,1,23,15,30,40,0);
        System.out.println(dt2);

        //Formatting
        //Formatter 지정
        dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(dt);

        //포맷을 직접 지정한 Formatter 생성
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss E a", Locale.KOREA);
        //사용자가 생성한 Formatter 지정
        System.out.println(dt.format(formatter));

        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss E a", Locale.CHINESE);
        System.out.println(dt.format(formatter));
    }

}
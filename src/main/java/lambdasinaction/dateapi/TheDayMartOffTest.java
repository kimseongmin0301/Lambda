package lambdasinaction.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import static java.time.temporal.TemporalAdjusters.*;

public class TheDayMartOffTest {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
//        today = LocalDate.of(2023, 7, 24);
        System.out.println("현재날짜 = " + today);

        //with(TemporalAdjuster) TemporalAdjuster의 추상메서드 Temporal adjustInto(Temporal temporal)
//        today.with(new TemporalAdjuster() {
//            @Override
//            public Temporal adjustInto(Temporal temporal) {
//                return null;
//            }
//        });
        LocalDate martDayOff = today.with(temporal -> {
            //1. 기준을 되는 날짜를 구하기
            LocalDate theDay = LocalDate.from(temporal);
            //2. 두번째,네번째 일요일 날짜를 구하기
            LocalDate secondDay = theDay.with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
            LocalDate fourthDay = theDay.with(dayOfWeekInMonth(4, DayOfWeek.SUNDAY));
            //3. 기준날짜와 비교하기
            if (theDay.isBefore(secondDay)) {
                return secondDay;
            } else if (theDay.isBefore(fourthDay)) {
                return fourthDay;
            } else {
                return theDay.plusMonths(1).with(dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
            }
        });

        System.out.println("다음 Mart 쉬는 날은 언제인가요? " + martDayOff);
    }
}
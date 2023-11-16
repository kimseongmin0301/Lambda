package lambdasinaction._02stream.with.optional;

import java.util.Optional;

public class MobileTesterWithOptional {

    public static void main(String[] args) {
        ScreenResolution resolution = new ScreenResolution(750, 1334);
        DisplayFeatures dfeatures = new DisplayFeatures("4.7", Optional.of(resolution));
        Mobile mobile = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));

        MobileService mService = new MobileService();

        int width = mService.getMobileScreenWidth(Optional.of(mobile));
        System.out.println("Apple iPhone 6s Screen Width = " + width);

        Mobile mobile2 = new Mobile(2015001, "Apple", "iPhone 6s", Optional.empty());
        int width2 = mService.getMobileScreenWidth(Optional.of(mobile2));
        System.out.println("Apple iPhone 16s Screen Width = " + width2);

        // Optional의 orElseGet() 사용
        Optional<DisplayFeatures> optionalDisplayFeatures = mobile2.getDisplayFeatures();
        // orElseGet(Supplier) Supplier의 추상메서드 () -> T
        DisplayFeatures displayFeatures = optionalDisplayFeatures
                .orElseGet(() -> new DisplayFeatures("0.0", Optional.empty()));
        System.out.println("displayFeatures size = " + displayFeatures.getSize());

        // OrElseThrow
        displayFeatures = optionalDisplayFeatures
                .orElseThrow(() -> new RuntimeException("DisplayFeatures Object is null"));
    }

}

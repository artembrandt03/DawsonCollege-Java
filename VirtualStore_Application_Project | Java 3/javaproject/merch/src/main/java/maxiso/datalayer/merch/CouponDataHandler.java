package maxiso.datalayer.merch;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import maxiso.businesslayer.Coupon;
import maxiso.businesslayer.PercentageCoupon;
import maxiso.businesslayer.FixedCoupon;

public class CouponDataHandler {
    private String couponFile;

    public CouponDataHandler(String couponFile) {
        this.couponFile = couponFile;
    }

    public List<Coupon> loadCoupons() throws IOException {
        Path path = Paths.get(couponFile);
        List<Coupon> coupons = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String couponName = parts[0];
                String type = parts[1];
                double discount = Double.parseDouble(parts[2]);

                if (type.equals("Percentage")) {
                    PercentageCoupon coupon = new PercentageCoupon(couponName, discount);
                    coupons.add(coupon);
                } else if (type.equals("Fixed")) {
                    FixedCoupon coupon = new FixedCoupon(couponName, discount);
                    coupons.add(coupon);
                }
            }
        }

        return coupons;
    }
}

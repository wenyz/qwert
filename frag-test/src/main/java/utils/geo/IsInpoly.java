package utils.geo;

import java.util.ArrayList;
import java.util.List;

public class IsInpoly {

    public static boolean IsPtInPoly(double[] target, List<Double[]> polyPointList) {
        int iSum = 0,
                iCount;
        double dLon1, dLon2, dLat1, dLat2, dLon;
        double targetLon = target[0];
        double targetLat = target[1];
        if (polyPointList.size() < 3) return false;
        iCount = polyPointList.size();
        for (int i = 0; i < iCount; i++) {
            if (i == iCount - 1) {
                dLon1 = polyPointList.get(i)[0];
                dLat1 = polyPointList.get(i)[1];
                dLon2 = polyPointList.get(0)[0];
                dLat2 = polyPointList.get(0)[1];
            } else {
                dLon1 = polyPointList.get(i)[0];
                dLat1 = polyPointList.get(i)[1];
                dLon2 = polyPointList.get(i + 1)[0];
                dLat2 = polyPointList.get(i + 1)[1];
            }
            //以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
            if (((targetLat >= dLat1) && (targetLat < dLat2)) || ((targetLat >= dLat2) && (targetLat < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    //得到 A点向左射线与边的交点的x坐标：
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - targetLat)) / (dLat1 - dLat2);
                    if (dLon < targetLon)
                        iSum++;
                }
            }
        }
        if (iSum % 2 != 0)
            return true;
        return false;
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        List<Double[]> polyList = new ArrayList<>();
        polyList.add(new Double[]{121.296612, 31.134101});
        polyList.add(new Double[]{121.396612, 31.334101});
        polyList.add(new Double[]{121.496612, 31.534101});
        polyList.add(new Double[]{121.796612, 31.234101});

        for (int i = 0; i < 10e6; i++) {
            double[] target = {121.296612 + Math.random() * 0.01, 31.134101 + Math.random() * 0.01};
            IsPtInPoly(target, polyList);
            //  System.out.println(IsPtInPoly(target, polyList));
        }

        long endTime = System.currentTimeMillis();

        System.out.println("costTime : " + (endTime - startTime));

    }
}

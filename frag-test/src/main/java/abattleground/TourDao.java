//package abattleground;
//
//
//import com.mongodb.BasicDBList;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.BasicQuery;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
///**
// * mongo dao 复合查询
// * @author wenyz
// */
//@Repository("iTourDao")
//public class TourDao implements ITourDao {
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Override
//    public List<TourBasicInfo> pageTour(int start, int limit, String deviceId) {
//
//        DBObject queryObj = new BasicDBObject();
//        queryObj.put("deviceId", deviceId);
//
//        DBObject fieldObj = new BasicDBObject();
//        fieldObj.put("_id", true);
//        fieldObj.put("deviceId", true);
//        fieldObj.put("deviceType", true);
//        fieldObj.put("startTime", true);
//        fieldObj.put("endTime", true);
//        fieldObj.put("dateString", true);
//        fieldObj.put("startAddress", true);
//        fieldObj.put("endAddress", true);
//        fieldObj.put("mileage", true);
//
//        Query query = new BasicQuery(queryObj, fieldObj).skip(start).limit(limit).with(new Sort(Sort.Direction.DESC, "startTime"));
//
//        List<TourBasicInfo> results = mongoTemplate.find(query, TourBasicInfo.class, MongoConstants.TOUR_DATA_SET_KEY);
//
//        return results;
//    }
//
//    @Override
//    public TourDetailInfo getTourDetail(String uid) {
//        Criteria cc = Criteria.where("_id").is(uid);
//        Query query = new Query(cc);
//        TourDetailInfo result = mongoTemplate.findOne(query, TourDetailInfo.class, MongoConstants.TOUR_DATA_SET_KEY);
//
//        return result;
//    }
//
//    @Override
//    public List<TourBasicInfo> pageTourByTimeLineDevice(List<TourSearchCondition> searchConditions, int start, int limit) {
//
//        if (searchConditions == null || searchConditions.size() == 0) {
//            return null;
//        }
//        int deviceSize = searchConditions.size();
//        BasicDBList inDeviceList = new BasicDBList();
//
//        for (int i = 0; i < deviceSize; i++) {
//
//            BasicDBObject device = new BasicDBObject();
//            TourSearchCondition deviceTemp = searchConditions.get(i);
//            device.put("deviceId", deviceTemp.getDeviceId());
//
//            if (deviceTemp.getTimeLine() != null && deviceTemp.getTimeLine().size() > 0) {
//                int timeLineSize = deviceTemp.getTimeLine().size();
//                BasicDBList timeLines = new BasicDBList();
//
//
//                for (int j = 0; j < timeLineSize; j++) {
//                    TourSearchCondition.TimeDetail timeLineTemp = deviceTemp.getTimeLine().get(j);
//                    BasicDBObject frameTimeQuery = new BasicDBObject();
//                    BasicDBObject timeLineQuery = new BasicDBObject();
//                    timeLineQuery.put("$gte", timeLineTemp.getStartTime());
//                    timeLineQuery.put("$lte", timeLineTemp.getEndTime());
//                    frameTimeQuery.put("startTime", timeLineQuery);
//                    timeLines.add(frameTimeQuery);
//
//                }
//
//                device.put("$or", timeLines);
//            }
//
//            inDeviceList.add(device);
//        }
//
//        BasicDBObject queryObj = new BasicDBObject();
//        queryObj.put("$or", inDeviceList);
//
//        DBObject fieldObj = new BasicDBObject();
//        fieldObj.put("_id", true);
//        fieldObj.put("deviceId", true);
//        fieldObj.put("deviceType", true);
//        fieldObj.put("startTime", true);
//        fieldObj.put("endTime", true);
//        fieldObj.put("dateString", true);
//        fieldObj.put("startAddress", true);
//        fieldObj.put("endAddress", true);
//        fieldObj.put("mileage", true);
//
//        Query query = new BasicQuery(queryObj, fieldObj).skip(start).limit(limit).with(new Sort(Sort.Direction.DESC, "startTime"));
//
//        List<TourBasicInfo> results = mongoTemplate.find(query, TourBasicInfo.class, MongoConstants.TOUR_DATA_SET_KEY);
//
//        return results;
//    }
//
//}

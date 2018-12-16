package abattleground.stockana;

import abattleground.stockana.bean.CsvObj;

import java.io.*;
import java.util.*;

public class application {

    public static void main(String[] args) throws IOException {

        String filePth = "D:\\stockm.csv";

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePth),"gb2312"));

        List<CsvObj> anyLists = new ArrayList<>();

        String line = null;
        while ( (line = br.readLine()) != null){

            String[] ss=line.split(",");

            CsvObj temp = new CsvObj();
            temp.setOperationDateString(ss[0]+" " + ss[1]);
            temp.setOperationStockCode(ss[2]);
            temp.setOperationStockName(ss[3]);
            temp.setOperation("买入".equals(ss[4])?CsvObj.OperationType.BUY:"卖出".equals(ss[4])?CsvObj.OperationType.SOLD:CsvObj.OperationType.UNKNOWN);
            temp.setOperationCount(Integer.valueOf(ss[5]));
            temp.setOperationAvaragePrise(Float.valueOf(ss[6]));
            temp.setTotalPrise(Float.valueOf(ss[7]));
            temp.setOperationContractNumber(ss[8]);
            temp.setMarketType("沪Ａ".equals(ss[10])?CsvObj.MarketType.HUA:CsvObj.MarketType.SHENA);
            temp.setOperationAccount(ss[11]);

            anyLists.add(temp);

           // System.out.println(temp);
        }

       // System.out.println(anyLists.size());

        analyzeByOperation(anyLists);
        analyzeByDate(anyLists);

    }

    private static void analyzeByDate(List<CsvObj> anyLists) {

        String filterString = "20130925";
        Iterator<CsvObj> iterators = anyLists.iterator();
        while (iterators.hasNext()){
            CsvObj temp = iterators.next();
            if(filterString.compareTo(temp.getOperationDateString()) < 0){
                iterators.remove();
            }
        }

        System.out.println("新的队列的长度为：" + anyLists.size());

        analyzeByOperation(anyLists);


    }

    private static void analyzeByOperation(List<CsvObj> anyLists) {

        Iterator<CsvObj> iterators = anyLists.iterator();
        Map<String,Object> anyResult = new HashMap<>();
        anyResult.put("buy",Float.valueOf(0));
        anyResult.put("sold",Float.valueOf(0));

        while (iterators.hasNext()){

            CsvObj temp = iterators.next();

            if(CsvObj.OperationType.BUY == temp.getOperation()){
                anyResult.put("buy",(float)anyResult.get("buy") + temp.getTotalPrise());
            }else if(CsvObj.OperationType.SOLD == temp.getOperation()){
                anyResult.put("sold",(float)anyResult.get("sold") + temp.getTotalPrise());
            }
        }

        System.out.println("===========================================================================================");
        System.out.println("===============                       买卖统计                               ==============");
        System.out.println("===========================================================================================");
        System.out.println("一共买入金额： " + anyResult.get("buy"));
        System.out.println("一共卖出金额： " + anyResult.get("sold"));
        System.out.println("卖出-买入   ： " + ((float)anyResult.get("sold")-(float)anyResult.get("buy")));
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

    }
}

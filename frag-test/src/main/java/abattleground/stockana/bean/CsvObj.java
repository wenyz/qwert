package abattleground.stockana.bean;

import com.alibaba.fastjson.JSON;

import java.util.Date;

public class CsvObj {

    private String operationDateString;
    private String operationStockCode;
    private String operationStockName;
    private OperationType operation;

    private int operationCount;
    private float operationAvaragePrise;
    private float totalPrise;
    private String operationContractNumber;
    private String operationAccount;
    private MarketType marketType;

    public String getOperationDateString() {
        return operationDateString;
    }

    public void setOperationDateString(String operationDateString) {
        this.operationDateString = operationDateString;
    }

    public String getOperationStockCode() {
        return operationStockCode;
    }

    public void setOperationStockCode(String operationStockCode) {
        this.operationStockCode = operationStockCode;
    }

    public String getOperationStockName() {
        return operationStockName;
    }

    public void setOperationStockName(String operationStockName) {
        this.operationStockName = operationStockName;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public void setOperationCount(int operationCount) {
        this.operationCount = operationCount;
    }

    public float getOperationAvaragePrise() {
        return operationAvaragePrise;
    }

    public void setOperationAvaragePrise(float operationAvaragePrise) {
        this.operationAvaragePrise = operationAvaragePrise;
    }

    public float getTotalPrise() {
        return totalPrise;
    }

    public void setTotalPrise(float totalPrise) {
        this.totalPrise = totalPrise;
    }

    public String getOperationContractNumber() {
        return operationContractNumber;
    }

    public void setOperationContractNumber(String operationContractNumber) {
        this.operationContractNumber = operationContractNumber;
    }

    public String getOperationAccount() {
        return operationAccount;
    }

    public void setOperationAccount(String operationAccount) {
        this.operationAccount = operationAccount;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }

    public enum OperationType{
            BUY,SOLD,UNKNOWN

    }

    public enum MarketType{
        HUA,SHENA
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

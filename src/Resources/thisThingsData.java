package Resources;

import javafx.beans.property.*;

public class thisThingsData {
    private StringProperty value;
    public void setValue(String value) {
        valueProperty().setValue(value);
    }
    public String getValue() {
        return valueProperty().get();
    }
    public StringProperty valueProperty() {
        if (value == null) value = new SimpleStringProperty(this, "value");
        return value;
    }

    private IntegerProperty amount;
    public void setAmount(int value) {
        amountProperty().setValue(value);
    }
    public int getAmount() {
        return amountProperty().get();
    }
    public IntegerProperty amountProperty() {
        if (amount == null) amount = new SimpleIntegerProperty(this, "amount");
        return amount;
    }

    public void appendAmount() {
        setAmount(getAmount() + 1);
    }

    public thisThingsData(String value) {
        setValue(value);
        setAmount(0);
    }
}

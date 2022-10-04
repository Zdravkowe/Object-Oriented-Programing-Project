package bg.tu_varna.sit;

public interface Column {
    String getName();
    ColumnType getType();
    int getSize();
    void append(Object value);
    Object getValueAt(int index);
    void setValueAt(int index, Object value);
}

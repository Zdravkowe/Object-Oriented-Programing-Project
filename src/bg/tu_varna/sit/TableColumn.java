package bg.tu_varna.sit;

import java.util.LinkedList;
import java.util.List;

//колона от таблица
public class TableColumn implements Column {

    private List<Object> data;
    private String name;
    private ColumnType type;
    public TableColumn(){}

    public TableColumn(String name, ColumnType type){
        this.name = name;
        this.data = new LinkedList<>();
        this.type = type;
    }

    public TableColumn(String name, ColumnType type, int startingSize){
        this.name = name;
        this.data = new LinkedList<>();
        this.type = type;
        for (int i = 0; i < startingSize; i++) {
            data.add(null);
        }
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public ColumnType getType(){
        return type;
    }

    @Override
    public int getSize(){
        return data.size();
    }

    @Override
    public void append(Object value){
        data.add(value);
    }

    @Override
    public Object getValueAt(int index){
        return data.get(index);
    }

    @Override
    public void setValueAt(int index, Object value){
        data.set(index, value);
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }
}

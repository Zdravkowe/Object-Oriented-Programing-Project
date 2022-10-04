package bg.tu_varna.sit;

import exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;


public class Table {
    private String name;

    private List<Column> columns;

    public Table(){}

    public Table(String name, List<String> names, List<ColumnType> types){
        this.name = name;
        columns = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            columns.add(new TableColumn(names.get(i), types.get(i)));
        }
    }

    public String getName(){
        return this.name;
    }

    public int getColumnCount(){
        return columns.size();
    }

    public Row getRow(int index){
        if(getRowCount()<=index || index<0)
            return null;
        else{
            List<Object> dataList = new ArrayList<>();
            List<ColumnType> typeList = new ArrayList<>();
            for (Column i:columns) {
                dataList.add(i.getValueAt(index));
                typeList.add(i.getType());
            }
            return new Row(dataList, typeList);
        }
    }

    public List<String> getColumnNames(){
        List<String> names = new ArrayList<>();
        for (Column i:columns) {
            names.add(i.getName());
        }
        return names;
    }

    public List<ColumnType> getColumnTypes(){
        List<ColumnType> names = new ArrayList<>();
        for (Column i:columns) {
            names.add(i.getType());
        }
        return names;
    }

    public void insert(Row row) throws DatabaseException {
        if(row.getSize() != columns.size())
            throw new DatabaseException("Inserting "+row.getSize()+" values for "+columns.size()+" columns");

        for (int i=0; i<getColumnCount(); i++){
            if(!columns.get(i).getType().equals(row.getType(i)))
                throw new DatabaseException("com.company.Column "+i+" type doesn't match");
        }

        for (int i = 0; i < getColumnCount(); i++) {
            columns.get(i).append(row.getValue(i));
        }

    }

    public void addColumn(String name, ColumnType type){
        Column column = new TableColumn(name, type, getRowCount());
        columns.add(column);
    }


    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public int getRowCount(){
        return columns.get(0).getSize();
    }

    public String namesAsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Column i:columns) {
            stringBuilder.append(i.getName()).append(" | ");
        }
        return stringBuilder.toString();
    }

}

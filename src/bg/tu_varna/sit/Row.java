package bg.tu_varna.sit;

import java.util.List;

//ред от таблицата - не се съхранява във файл
public class Row {
    private List<Object> data;
    private List<ColumnType> types;

    public Row(List<Object> data, List<ColumnType> types) {
        this.data = data;
        this.types = types;
    }


    public Object getValue(int index){
        return data.get(index);
    }

    public ColumnType getType(int index){
        return types.get(index);
    }

    public int getSize(){
        return data.size();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i) == null){
                stringBuilder.append("NULL");
            }else {
                switch (types.get(i)) {
                    case INT:
                        stringBuilder.append((Integer) data.get(i));
                        break;
                    case DOUBLE:
                        stringBuilder.append((Double) data.get(i));
                        break;
                    case STRING:
                        stringBuilder.append((String) data.get(i));
                        break;
                }
            }
            stringBuilder.append(" | ");
        }
        return stringBuilder.toString();
    }

}

package bg.tu_varna.sit;

import exceptions.DatabaseException;

//factory клас на типа
public class StringToType {
    public ColumnType getType(String typeName)throws DatabaseException{
        switch (typeName.toUpperCase()){
            case "INT":
                return ColumnType.INT;
            case "DOUBLE":
                return ColumnType.DOUBLE;
            case "STRING":
                return ColumnType.STRING;
            default:
                throw new DatabaseException(typeName+" type doesn't exist");
        }
    }


    public Object getValue(ColumnType type, String value)throws DatabaseException{
        if(type==null)
            throw new DatabaseException("type is null");
        if(value.equals("NULL")){
            return null;
        }
        try {
            switch (type) {
                case INT:
                    return Integer.valueOf(Integer.parseInt(value));
                case DOUBLE:
                    return Double.valueOf(Double.parseDouble(value));
                case STRING:
                    return new String(value);
                default:
                    throw new DatabaseException("type doesn't exist");
            }
        }catch (NumberFormatException e){
            throw new DatabaseException("Incorrectly entered "+type);
        }
    }

}

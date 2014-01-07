package example.domain.example6.clob;

public interface ClobSerializer {

	String toClob(Object object);

	Object fromClob(String clob, String type);

}
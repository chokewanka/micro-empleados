package pe.com.chokewanka.springboot.micro.empleados.utils;

public class UtilConstants {

	public static final String EMPTY_STRING = "";
	public static final Long EMPTY_ID = 0L;
	
	public static final int IS_DELETED = 1;
	public static final int IS_NOT_DELETED = 0;
	
	public static final String DATE_FORMAT = "YYYY-MM-DD";
	
	public static String leftSideLike( String s ) {
		return '%'+s;
	}
	
	public static String rightSideLike( String s ) {
		return s+'%';
	}
	
	public static String bothSidesLike( String s ) {
		return '%'+s+'%';
	}
	
}

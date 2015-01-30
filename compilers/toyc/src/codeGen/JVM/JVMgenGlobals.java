/*
 * Sean Burns
 * 
 * ToyC Compiler
 */ 

package codegen.JVM;

public class JVMgenGlobals {
	
	public static String className = null;
	public final static String LABELTAG = "_label";
	
	public final static String INPUT_STREAM_OBJ = "InputStream";
	public final static String INPUT_READER_OBJ = "BufferedReader";
	public final static String OUTPUT_STREAM_OBJ = "PrintStream";
	
	public final static String ROOT_CLASS = "java/lang/Object";
	public final static String STRING_CLASS = "java/lang/String";
	public final static String STRING_OBJECT = "Ljava/lang/String;";
	
	public final static String SCANNER = "java/util/Scanner";
	public final static String BUFFEREDREADER = "java/io/BufferedReader";
	public final static String INPUTSTREAMREADER = "java/io/InputStreamReader";
	public final static String IOEXCEPTION = "java/io/IOException";
	public final static String STRINGTOKENIZER = "java/util/StringTokenizer";
	
	public final static String OUTPUT_FIELD_SPEC = "java/lang/System/out";
	public final static String OUTPUT_DESCRIPTOR = "Ljava/io/PrintStream;";
	public final static String INPUT_FIELD_SPEC = "java/lang/System/in";
	public final static String INPUT_DESCRIPTOR = "Ljava/io/InputStream;";
	
	public final static String OBJECT_CONSTRUCTOR = "java/lang/Object/<init>()V";
	public final static String INPUTSTREAM_CONSTRUCTOR = "java/io/InputStreamReader/<init>(Ljava/io/InputStream;)V";
	public final static String READER_CONSTRUCTOR = "java/io/BufferedReader/<init>(Ljava/io/Reader;)V";
	public final static String SCANNER_CONSTRUCTOR = "java/util/Scanner/<init>(Ljava/io/InputStream;)V";
	public final static String STRINGTOKENIZER_CONSTRUCTOR = "java/util/StringTokenizer/<init>(Ljava/lang/String;)V";
	public final static String PARSEINT_METHOD_SPEC = "java/lang/Integer/parseInt(Ljava/lang/String;)I";
	public final static String PARSEINT_METHOD = "java/lang/Integer/parseInt";
	public final static String READLINE_METHOD_SPEC = "java/io/BufferedReader/readLine()Ljava/lang/String;";
	public final static String READ_INT_METHOD_SPEC = "java/util/Scanner/nextInt()I";
	public final static String NEWLINE_METHOD_SPEC = "java/io/PrintStream/println()V";
	public final static String PRINT_INT_METHOD_SPEC = "java/io/PrintStream/print(I)V";
	public final static String PRINT_INT_NEWLINE_METHOD_SPEC = "java/io/PrintStream/println(I)V";
	public final static String PRINT_STRING_METHOD_SPEC = "java/io/PrintStream/print(Ljava/lang/String;)V";
	public final static String NEXT_TOKEN_METHOD_SPEC = "java/util/StringTokenizer/nextToken()Ljava/lang/String;";
}
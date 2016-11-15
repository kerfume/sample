package jp.kerfume.app.interf;

import java.io.Closeable;
import java.io.IOException;

public interface MyDaoInterface extends Closeable{
	void conect() throws IOException;
	void close() throws IOException;
}

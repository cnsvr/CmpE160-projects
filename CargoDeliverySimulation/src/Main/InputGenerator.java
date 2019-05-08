package Main;
import java.io.FileNotFoundException;
import java.io.PrintStream;
public class InputGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		int length = 1;
		int carcapacity = (int) (Math.random()*1000);
		carcapacity += 10;
		int station = (int) (Math.random()*100);
		station += 3;
		PrintStream ingen = new PrintStream(args[0]);
		ingen.println(length + " " + carcapacity + " " + station );
		for(int i = 0;i<(int) (Math.random()*200);i++) {
			int id = i;
			int initial = (int) (Math.random()*station);
			int dest = (int) (Math.random()*station);
			while(dest < initial) {
				dest = (int) (Math.random()*station);
			}
			int size = (int) (Math.random()*carcapacity);
			ingen.println(id + " " + initial + " " + dest + " " + size);
		}
		ingen.close();
	}

}

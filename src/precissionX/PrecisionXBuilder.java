package precissionX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import lisa.Piece;

public class PrecisionXBuilder {

	static private void importPiecesToPrecisionFile(ArrayList<Piece> pieces, String OutputFileName) {

		int loopLength = pieces.size();
		int defaultConnectors = 8;

		String program = "PRG_HDR (NAME=\"Serj complete\", STOR=R, LOCK=F, CHAN=8S, STEPS=17, TYPE=A)\n"
				+ "PRG_STEP (STEP=1, TYPE=MAP (STA=A, SPLY=V, LOCF=1, LOCL=12, VOL=100, SAMP=0, VSRC=0, SMPFL=0, RLD=1, RST=1, FILE=\"NF_8X12STRPS\", FSTR=R))\n"
				+ "PRG_STEP (STEP=2, TYPE=MAP (STA=B, SPLY=V, LOCF=1, LOCL=12, VOL=100, SAMP=0, VSRC=0, SMPFL=0, RLD=1, RST=1, FILE=\"NF_8X12STRPS\", FSTR=R))\n"
				+ "PRG_STEP (STEP=3, TYPE=MAP (STA=D, SPLY=V, LOCF=1, LOCL=12, VOL=100, SAMP=0, VSRC=0, SMPFL=0, RLD=1, RST=1, FILE=\"NF_8X12STRPS\", FSTR=R))\n"
				+ "PRG_STEP (STEP=4, TYPE=MAP (STA=E, SPLY=T, LOCF=1, LOCL=12, RLD=1, RST=1, FILE=\"LC200ROB BLACK\", FSTR=R))\n"
				+ "PRG_STEP (STEP=5, TYPE=LOOPON (FIX=T, CNT=" + loopLength + ", SVAR=0))\n" // number of pieces
				+ "PRG_STEP (STEP=6, TYPE=TIP (PRIM=F, STA=E, STRT=1, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=P, SROW=A, INCR=F, AINC=T, P384=F, FILE=\"TDD\", FSTR=R))\n"
				+ "PRG_STEP (STEP=7, TYPE=ASP (VOL=20, STA=D, STRT=1, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=P, SROW=A, INCR=F, AINC=T, P384=F, FILE=\"A000N140\", FSTR=R))\n"
				+ "PRG_STEP (STEP=8, TYPE=DISP (VOL=5, STA=B, STRT=1, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=A, SROW=A, INCR=T, AINC=T, P384=F, FILE=\"DP0481040\", FSTR=R))\n"
				+ "PRG_STEP (STEP=9, TYPE=LOOPON (FIX=T, CNT=" + defaultConnectors + ", SVAR=0))\n" // number of connectors
				+ "PRG_STEP (STEP=10, TYPE=TIP (PRIM=F, STA=E, STRT=1, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=P, SROW=B, INCR=F, AINC=T, P384=F, FILE=\"TDD\", FSTR=R))\n"
				+ "PRG_STEP (STEP=11, TYPE=ASP (VOL=5, STA=A, STRT=1, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=F, SROW=A, INCR=T, AINC=T, P384=F, FILE=\"A000N140\", FSTR=R))\n"
				+ "PRG_STEP (STEP=12, TYPE=DISP (VOL=5, STA=B, STRT=1, LOOP=1, LOOP2=0, LOOP3=0, SNGL=T, SLOC=F, SROW=A, INCR=F, AINC=F, P384=F, FILE=\"DP0481040\", FSTR=R))\n"
				+ "PRG_STEP (STEP=13, TYPE=LOOPOFF ())\n"
				+ "PRG_STEP (STEP=14, TYPE=TIP (PRIM=F, STA=E, STRT=8, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=P, SROW=B, INCR=F, AINC=T, P384=F, FILE=\"TDD\", FSTR=R))\n"
				+ "PRG_STEP (STEP=15, TYPE=ASP (VOL=10, STA=B, STRT=1, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=P, SROW=A, INCR=F, AINC=T, P384=F, FILE=\"A000N140\", FSTR=R))\n"
				+ "PRG_STEP (STEP=16, TYPE=DISP (VOL=10, STA=B, STRT=12, LOOP=0, LOOP2=0, LOOP3=0, SNGL=T, SLOC=F, SROW=H, INCR=F, AINC=F, P384=F, FILE=\"DP0481040\", FSTR=R))\n"
				+ "PRG_STEP (STEP=17, TYPE=LOOPOFF ())\n"
				+ "PRG_END ()";

		File file = new File(OutputFileName  + ".PGM");

		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(program);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String arg[]) {

		// should be determined by the project that is worked on
		File file = new File("/Users/serjsmor/Documents/project/LISA/Test\\Log.xml");

		XMLReader reader = new XMLReader(file);
		try {
			ArrayList<Piece> pieces = reader.connections();
			// should be recieved as a parameter through a button action
			String fileName = "PGM_TEST";  
			// output to PGM file
			PrecisionXBuilder.importPiecesToPrecisionFile(pieces, fileName);
			// show results map of connectors on screen
			for (Piece piece : pieces) {
				System.out.println(piece.toString());
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

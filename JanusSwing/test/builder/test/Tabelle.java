package builder.test;

import org.apache.log4j.Logger;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.swing.builder.DefaultGuiElementBuilder;
import org.junit.Assert;

public class Tabelle extends TestSinglePage{
    private static final Logger LOG = Logger.getLogger(Tabelle.class);

	public Tabelle() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		try {
			GuiElementBuilder elementBuilder = new DefaultGuiElementBuilder();
			String pagename = "TablePage";
			startTest(elementBuilder, pagename);
			
	
			

		} catch (Exception e) {
			LOG.error("Fehler",e);;
			Assert.fail("Exception " + e.getMessage());
		}
	}	
	
}

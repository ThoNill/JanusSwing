package builder.test;

import org.apache.log4j.Logger;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.swing.builder.DefaultGuiElementBuilder;
import org.junit.Assert;

public class Button extends TestSinglePage {
    private static final Logger LOG = Logger.getLogger(Button.class);

	public Button() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		try {
			GuiElementBuilder elementBuilder = new DefaultGuiElementBuilder();
			String pagename = "ButtonPage";
			startTest(elementBuilder, pagename);
			

		} catch (Exception e) {
			LOG.error("Fehler",e);;
			Assert.fail("Exception " + e.getMessage());
		}
	}


	
}

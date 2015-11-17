package builder.test;

import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.swing.builder.DefaultGuiElementBuilder;
import org.junit.Assert;

public class Button extends TestSinglePage {

	public Button() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		try {
			GuiElementBuilder elementBuilder = new DefaultGuiElementBuilder();
			String pagename = "ButtonPage";
			startTest(elementBuilder, pagename);
			

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception " + e.getMessage());
		}
	}


	
}

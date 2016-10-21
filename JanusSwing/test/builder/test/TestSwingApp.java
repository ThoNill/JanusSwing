package builder.test;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.janus.appbuilder.AppBuilder;
import org.janus.gui.basis.JanusApplication;
import org.janus.gui.basis.JanusPage;
import org.janus.gui.basis.JanusSession;
import org.janus.gui.basis.PageContext;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.swing.JFrameConnector;
import org.janus.gui.swing.builder.DefaultGuiElementBuilder;
import org.junit.Assert;

public class TestSwingApp {
    private static final Logger LOG = Logger.getLogger(TestSwingApp.class);

	public TestSwingApp() {
		
	}

	
	public static void main(String args[]) {
		try {
			GuiElementBuilder elementBuilder = new DefaultGuiElementBuilder();
			AppBuilder builder = new AppBuilder(elementBuilder) ;
			builder.setPageListe("data");
			JanusApplication app = builder.getApplication("testapp");
			
			JanusSession session = app.newContext();
			
			JanusPage login = session.searchPage("login");
			
			PageContext context = session.getPageContext(login);
			JFrameConnector frameConnector = (JFrameConnector)login.getGui();
			frameConnector.setContext(context);
	
			JFrame frame = frameConnector.getFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setSize(500,500);
			frame.setVisible(true);
		} catch (Exception e) {
			LOG.error("Fehler",e);;
			Assert.fail("Exception " + e.getMessage());
		}
	}	
}

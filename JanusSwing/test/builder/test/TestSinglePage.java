package builder.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

import org.janus.binder.BindWalker;
import org.janus.binder.gui.GuiBuilderWalker;
import org.janus.builder.BuilderWalker;
import org.janus.data.DataContext;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.JanusApplication;
import org.janus.gui.basis.JanusPage;
import org.janus.gui.basis.JanusSession;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.swing.JFrameConnector;
import org.janus.gui.swing.SessionInterface;
import org.janus.gui.swing.SwingBasisConnector;
import org.jdom2.Document;

import toni.druck.xml.XMLDocumentLoader;

public class TestSinglePage {

	public TestSinglePage() {
		super();
	}
	
	protected static void startTest(GuiElementBuilder elementBuilder,
			String pagename) {
		Document page = new XMLDocumentLoader()
				.createDocument("pages/" + pagename + ".xml");
		JanusApplication app = new JanusApplication("test");
		JanusPage dict = new JanusPage("test");

		BuilderWalker walker = new TestBuilderWalker(pagename);
		walker.setDict(dict);
		walker.walkAlong(page);
		
		BindWalker bindWalker = new TestBinderWalker();
		bindWalker.walkAlong(page);
		bindWalker.bind(dict);			
		
		GuiBuilderWalker guiWalker = new GuiBuilderWalker(elementBuilder);
		guiWalker.setDict(dict);
		guiWalker.walkAlong(page);
		app.addPage(dict);
		
		GuiComponent comp =guiWalker.getRoot();

		assertNotNull(comp);
		assertTrue(comp instanceof JFrameConnector);

		JanusSession session = new JanusSession(app);
		
		SessionInterface.setSession(session);
		
		
		DataContext context = session.createContextInSession(dict);
		
		for(GuiComponent c : guiWalker.getComponents()) {
			((SwingBasisConnector)c).setContext(context);
		}
		
		JFrame frame = ((JFrameConnector)comp).getFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(500,500);
		frame.setVisible(true);
	}	

}
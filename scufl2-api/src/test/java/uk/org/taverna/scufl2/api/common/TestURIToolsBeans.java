package uk.org.taverna.scufl2.api.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.org.taverna.scufl2.api.ExampleWorkflow;
import uk.org.taverna.scufl2.api.container.WorkflowBundle;
import uk.org.taverna.scufl2.api.core.BlockingControlLink;
import uk.org.taverna.scufl2.api.core.ControlLink;
import uk.org.taverna.scufl2.api.core.DataLink;
import uk.org.taverna.scufl2.api.core.Processor;
import uk.org.taverna.scufl2.api.iterationstrategy.CrossProduct;

public class TestURIToolsBeans {

	private static final String BUNDLE_URI = "http://ns.taverna.org.uk/2010/workflowBundle/28f7c554-4f35-401f-b34b-516e9a0ef731/";
	private static final String HELLOWORLD_URI = BUNDLE_URI
			+ "workflow/HelloWorld/";
	private static final String HELLO_URI = HELLOWORLD_URI + "processor/Hello/";
	private URITools uriTools = new URITools();
	private Scufl2Tools scufl2Tools = new Scufl2Tools();
	private WorkflowBundle wfBundle;

	@Before
	public void makeExampleWorkflow() {
		wfBundle = new ExampleWorkflow().makeWorkflowBundle();
	}

	@Test
	public void uriForConditional() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		ControlLink condition = wfBundle.getMainWorkflow().getControlLinks()
				.iterator().next();
		assertTrue(condition instanceof BlockingControlLink);
		URI uri = uriTools.uriForBean(condition);

		assertEquals(
				HELLOWORLD_URI
						+ "control?block=processor/Hello/&untilFinished=processor/wait4me/",
				uri.toASCIIString());
	}

	@Test
	public void uriForDatalink() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		List<DataLink> nameLinks = scufl2Tools.datalinksTo(hello
				.getInputPorts().getByName("name"));
		URI uri = uriTools.uriForBean(nameLinks.get(0));
		assertEquals(HELLOWORLD_URI
				+ "datalink?from=in/yourName&to=processor/Hello/in/name",
				uri.toASCIIString());
	}

	@Test
	public void uriForDatalinkWithMerge() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		List<DataLink> greetingLinks = scufl2Tools.datalinksFrom(hello
				.getOutputPorts().getByName("greeting"));
		URI uri = uriTools.uriForBean(greetingLinks.get(0));
		assertEquals(
				HELLOWORLD_URI
						+ "datalink?from=processor/Hello/out/greeting&to=out/results&mergePosition=0",
				uri.toASCIIString());
	}

	@Test
	public void uriForDispatchStack() throws Exception {
		URI uri = uriTools.uriForBean(wfBundle.getMainWorkflow()
				.getProcessors().getByName("Hello").getDispatchStack());
		assertEquals(HELLO_URI + "dispatchstack/", uri.toASCIIString());
	}

	@Test
	public void uriForDispatchStackLayer() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		URI uri = uriTools.uriForBean(hello.getDispatchStack().get(0));
		assertEquals(HELLO_URI + "dispatchstack/0/", uri.toASCIIString());
	}

	@Test
	public void uriForIterationStrategy() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		URI uri = uriTools.uriForBean(hello.getIterationStrategyStack().get(0));
		assertEquals(HELLO_URI + "iterationstrategy/0/", uri.toASCIIString());
	}

	@Test
	public void uriForIterationStrategyCross() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		CrossProduct crossProduct = (CrossProduct) hello
				.getIterationStrategyStack().get(0).getRootStrategyNode();
		URI uri = uriTools.uriForBean(crossProduct.get(0));
		assertEquals(HELLO_URI + "iterationstrategy/0/root/0/",
				uri.toASCIIString());
	}

	@Test
	public void uriForIterationStrategyRoot() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		URI uri = uriTools.uriForBean(hello.getIterationStrategyStack().get(0)
				.getRootStrategyNode());

		assertEquals(HELLO_URI + "iterationstrategy/0/root/",
				uri.toASCIIString());
	}

	@Test
	public void uriForIterationStrategyStack() throws Exception {
		URI uri = uriTools
				.uriForBean(wfBundle.getMainWorkflow().getProcessors()
						.getByName("Hello").getIterationStrategyStack());
		assertEquals(HELLO_URI +

		"iterationstrategy/", uri.toASCIIString());
	}

	@Test
	public void uriForProcessor() throws Exception {
		URI uri = uriTools.uriForBean(wfBundle.getMainWorkflow()
				.getProcessors().getByName("Hello"));
		assertEquals(HELLO_URI, uri.toASCIIString());
	}

	@Test
	public void uriForProcessorInPort() throws Exception {
		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		URI uri = uriTools.uriForBean(hello.getInputPorts().getByName("name"));
		assertEquals(HELLO_URI + "in/name", uri.toASCIIString());
	}

	@Test
	public void uriForProcessorOutPort() throws Exception {

		Processor hello = wfBundle.getMainWorkflow().getProcessors()
				.getByName("Hello");
		URI uri = uriTools.uriForBean(hello.getOutputPorts().getByName(
				"greeting"));
		assertEquals(HELLO_URI + "out/greeting", uri.toASCIIString());
	}

	@Test
	public void uriForProfile() throws Exception {
		URI uri = uriTools.uriForBean(wfBundle.getMainProfile());
		assertEquals(BUNDLE_URI + "profile/tavernaWorkbench/" + "",
				uri.toASCIIString());
	}

	@Test
	public void uriForWfBundle() throws Exception {
		URI uri = uriTools.uriForBean(wfBundle);
		assertEquals(BUNDLE_URI, uri.toASCIIString());
	}

	@Test
	public void uriForWorkflow() throws Exception {
		URI uri = uriTools.uriForBean(wfBundle.getMainWorkflow());
		assertEquals(HELLOWORLD_URI, uri.toASCIIString());
	}

	@Test
	public void uriForWorkflowInPort() throws Exception {
		URI uri = uriTools.uriForBean(wfBundle.getMainWorkflow()
				.getInputPorts().getByName("yourName"));
		assertEquals(HELLOWORLD_URI + "in/yourName",
				uri.toASCIIString());
	}

	@Test
	public void uriForWorkflowOutPort() throws Exception {
		URI uri = uriTools.uriForBean(wfBundle.getMainWorkflow()
				.getOutputPorts().getByName("results"));
		assertEquals(HELLOWORLD_URI + "out/results",
				uri.toASCIIString());
	}

}
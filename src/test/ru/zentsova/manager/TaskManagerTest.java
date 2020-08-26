package ru.zentsova.manager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockSettings;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import ru.zentsova.request.ARequest;
import ru.zentsova.request.BRequest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TaskManager.class)
public class TaskManagerTest {

	@Test
	public void testRun() throws Exception {
		TaskManager taskManagerSpy = spy(new TaskManager());

		ARequest aRequest1 = new ARequest(1);
		taskManagerSpy.run(aRequest1);

		verifyPrivate(taskManagerSpy, times(1)).invoke("submitRequest", aRequest1);
		List<Integer> existingARequests = Whitebox.getInternalState(taskManagerSpy, "existingARequests");
		List<Integer> existingBRequests = Whitebox.getInternalState(taskManagerSpy, "existingBRequests");
		Assert.assertEquals(1, existingARequests.size());
		Assert.assertEquals(0, existingBRequests.size());
		Map<Integer, Future<Integer>> futuresARequest = Whitebox.getInternalState(taskManagerSpy, "futuresARequest");
		Map<Integer, Future<Integer>> futuresBRequest = Whitebox.getInternalState(taskManagerSpy, "futuresBRequest");
		Assert.assertEquals(1, futuresARequest.size());
		Assert.assertEquals(0, futuresBRequest.size());

		BRequest bRequest1 = new BRequest(2);
		taskManagerSpy.run(bRequest1);
		verifyPrivate(taskManagerSpy, times(1)).invoke("submitRequest", bRequest1);
		existingARequests = Whitebox.getInternalState(taskManagerSpy, "existingARequests");
		existingBRequests = Whitebox.getInternalState(taskManagerSpy, "existingBRequests");
		Assert.assertEquals(1, existingARequests.size());
		Assert.assertEquals(1, existingBRequests.size());
		futuresARequest = Whitebox.getInternalState(taskManagerSpy, "futuresARequest");
		futuresBRequest = Whitebox.getInternalState(taskManagerSpy, "futuresBRequest");
		Assert.assertEquals(1, futuresARequest.size());
		Assert.assertEquals(1, futuresBRequest.size());
	}

}
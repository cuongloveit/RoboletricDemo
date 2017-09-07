package com.example.roboletricdemo;

import com.example.roboletricdemo.api.APIHelper;
import com.example.roboletricdemo.base.BaseUnitTest;
import com.example.roboletricdemo.model.Repo;
import io.reactivex.subscribers.TestSubscriber;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by lap00168 on 8/29/17.
 */

public class ApiTest extends BaseUnitTest {

  @Test
  public void getRepository() throws Exception {
    TestSubscriber testSubscriber = APIHelper.getApi().getRepositories().test();
    testSubscriber.assertComplete();
    testSubscriber.assertNoErrors();
    assertNotNull(testSubscriber.getEvents());
    List<Repo> repos = (List<Repo>) getData(testSubscriber);
    assertNotNull(repos);
    assertEquals(repos.size(), 100);
  }
}

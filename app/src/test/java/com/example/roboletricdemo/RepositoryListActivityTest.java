package com.example.roboletricdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.roboletricdemo.api.APIHelper;
import com.example.roboletricdemo.api.GithubService;
import com.example.roboletricdemo.base.BaseUnitTest;
import com.example.roboletricdemo.model.Repo;
import io.reactivex.internal.operators.flowable.FlowableError;
import io.reactivex.internal.operators.flowable.FlowableJust;
import io.reactivex.subscribers.TestSubscriber;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

/**
 * Created by lap00168 on 9/3/17.
 */

public class RepositoryListActivityTest extends BaseUnitTest {
  private ProgressBar pbLoading;
  private RepositoryListActivity activity;
  private RecyclerView rcRepo;
  private TextView tvMessage;
  private TestSubscriber testSubscriber;
  @Mock private GithubService githubService;

  @Before
  public void setUp() {
    activity = Robolectric.buildActivity(RepositoryListActivity.class).create().get();
    pbLoading = (ProgressBar) activity.findViewById(R.id.pbLoading);
    rcRepo = (RecyclerView) activity.findViewById(R.id.rcRepo);
    tvMessage = (TextView) activity.findViewById(R.id.tvMessage);
    testSubscriber = TestSubscriber.create();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void viewVisibilityWhenLoading() throws Exception {
    Assert.assertEquals(pbLoading.getVisibility(), View.VISIBLE);
    Assert.assertEquals(tvMessage.getVisibility(), View.GONE);
    Assert.assertEquals(rcRepo.getVisibility(), View.VISIBLE);
  }

  @Test
  public void viewVisibilityWhenLoadFailed() throws Exception {
    when(githubService.getRepositories())
        .thenReturn(new FlowableError<List<Repo>>(new FlowableJust<Throwable>(new Exception())));

    githubService.getRepositories().subscribe(testSubscriber);
    testSubscriber.assertError(Exception.class);
  }

  @Test
  public void shouldHideProgressBarWhenLoadRepositoriesSuccess() {
    when(githubService.getRepositories())
        .thenReturn(new FlowableJust<List<Repo>>(new ArrayList<Repo>()));

    Assert.assertEquals(pbLoading.getVisibility(), View.GONE);

    githubService.getRepositories().subscribe(testSubscriber);
    testSubscriber.assertNoErrors();
    Assert.assertNotNull(testSubscriber.getEvents().get(0));
  }
}


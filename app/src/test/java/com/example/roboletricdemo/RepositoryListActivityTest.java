package com.example.roboletricdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.roboletricdemo.api.GithubService;
import com.example.roboletricdemo.base.BaseUnitTest;
import com.example.roboletricdemo.di.AppComponent;
import com.example.roboletricdemo.di.AppModule;
import com.example.roboletricdemo.model.Repo;
import com.example.roboletricdemo.model.RepoSearchResponse;
import com.example.roboletricdemo.presenter.repository.RepositoryPresenter;
import com.example.roboletricdemo.view.repository.RepositoryAdapter;
import com.example.roboletricdemo.view.repository.RepositoryListActivity;
import io.reactivex.internal.operators.flowable.FlowableError;
import io.reactivex.internal.operators.flowable.FlowableJust;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by lap00168 on 9/3/17.
 */

public class RepositoryListActivityTest extends BaseUnitTest {

  @Rule public final DaggerMockRule<AppComponent> rule = new DaggerMockRule<>(
      AppComponent.class,
      new AppModule())
      .set(new DaggerMockRule.ComponentSetter<AppComponent>() {
        @Override public void setComponent(AppComponent component) {
          getApplication().setAppComponent(component);
        }
      });

  private ProgressBar pbLoading;
  private RepositoryListActivity activity;
  private RecyclerView rcRepo;
  private TextView tvMessage;

  @Mock GithubService githubService;

  //@Mock RepositoryPresenter repositoryPresenter;

  private ActivityController<RepositoryListActivity> activityController;

  @Before
  public void setUp() {
    activityController = Robolectric.buildActivity(RepositoryListActivity.class);
    activity = activityController.get();
  }

  private void initWidgets() {
    activityController.setup();
    pbLoading = (ProgressBar) activity.findViewById(R.id.pbLoading);
    rcRepo = (RecyclerView) activity.findViewById(R.id.rcRepo);
    tvMessage = (TextView) activity.findViewById(R.id.tvMessage);
  }

  @Test
  public void viewVisibilityAfterRequestRepositoryFailed() {
    when(githubService.getRepositories()).thenReturn(new FlowableError<List<Repo>>(new
        FlowableJust<Throwable>(new Exception("Error"))));
    initWidgets();
    assertNotNull(activity);
    assertEquals(pbLoading.getVisibility(), View.GONE);
    assertEquals(rcRepo.getVisibility(), View.GONE);
    assertEquals(tvMessage.getVisibility(), View.VISIBLE);
  }

  @Test
  public void viewVisibilityAfterRequestRepositorySuccess() {
    when(githubService.getRepositories())
        .thenReturn(new FlowableJust<List<Repo>>(new ArrayList<>(mockListRepos(4))));

    initWidgets();
    assertNotNull(activity);
    assertEquals(pbLoading.getVisibility(), View.GONE);
    assertEquals(rcRepo.getVisibility(), View.VISIBLE);
    assertEquals(tvMessage.getVisibility(), View.GONE);
    assertEquals(rcRepo.getChildCount(),4);
  }

  @Test
  public void tvMessageShouldShowError() {
    when(githubService.getRepositories()).thenReturn(new FlowableError<List<Repo>>(new
        FlowableJust<Throwable>(new Exception("Error"))));

    initWidgets();
    assertEquals(tvMessage.getText().toString(), "Error");
  }

  @After
  public void shouldeDettacthView() {
    activityController.destroy();
  }

  public List<Repo> mockListRepos(int size) {
    List<Repo> repos = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      repos.add(new Repo(i, "name", "full name", "description", null, i + 5));
    }

    return repos;
  }
}


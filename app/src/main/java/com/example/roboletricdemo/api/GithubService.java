package com.example.roboletricdemo.api;

import com.example.roboletricdemo.model.Repo;
import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lap00168 on 8/29/17.
 */

public interface GithubService {
  @GET("repositories")
  Flowable<List<Repo>> searchRepos();
}

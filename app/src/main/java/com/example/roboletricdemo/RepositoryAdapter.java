package com.example.roboletricdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.roboletricdemo.model.Repo;
import java.util.List;

/**
 * Created by lap00168 on 9/3/17.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

  private final List<Repo> repos;

  public RepositoryAdapter(List<Repo> repos) {
    this.repos = repos;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_repo, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.onBind(position);
  }

  @Override public int getItemCount() {
    if (repos == null) {
      return 0;
    }
    return repos.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView tvName;
    private TextView tvDescription;
    public ViewHolder(View itemView) {
      super(itemView);
      tvName = itemView.findViewById(R.id.tvName);
      tvDescription = itemView.findViewById(R.id.tvDescription);
    }

    public void onBind(int position) {
      Repo repo = repos.get(position);
      tvName.setText(repo.name);
      tvDescription.setText(repo.description);

    }
  }
}

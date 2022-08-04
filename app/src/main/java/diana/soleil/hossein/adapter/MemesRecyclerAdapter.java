package diana.soleil.hossein.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import diana.soleil.hossein.R;
import diana.soleil.hossein.model.Memes;

public class MemesRecyclerAdapter extends RecyclerView.Adapter<MemesRecyclerAdapter.MyViewHolder>  {
    Memes meme;
    Context context;
    ArrayList<Memes> memesArrayList;
    OnMemeListener onMemeListener;
    OnMemeLongClickListener onMemeLongClickListener;

    public MemesRecyclerAdapter(Context context, ArrayList<Memes> memesArrayList, OnMemeListener onMemeListener, OnMemeLongClickListener onMemeLongClickListener) {
        this.context = context;
        this.memesArrayList = memesArrayList;
        this.onMemeListener = onMemeListener;
        this.onMemeLongClickListener = onMemeLongClickListener;
    }

    @NonNull
    @Override
    public MemesRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater
                    .from(context);
                    View view = layoutInflater.inflate(R.layout.custom_recycler_view, parent,false);

        return new MemesRecyclerAdapter.MyViewHolder(view, onMemeListener, onMemeLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MemesRecyclerAdapter.MyViewHolder holder, int position) {
        meme = memesArrayList.get(position);
        String photo = meme.getUrl();
        Picasso.get().load(photo).into(holder.imageView);
        holder.nameTextView.setText(meme.getName());
    }

    @Override
    public int getItemCount() {
        return memesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView imageView;
        TextView nameTextView;
        OnMemeListener onMemeListener;
        OnMemeLongClickListener onMemeLongClickListener;

        public MyViewHolder(@NonNull View itemView, OnMemeListener onMemeListener, OnMemeLongClickListener onMemeLongClickListener) {
            super(itemView);
            this.onMemeListener = onMemeListener;
            imageView = itemView.findViewById(R.id.imageViewId);
            nameTextView = itemView.findViewById(R.id.nameTextViewId);
            this.onMemeLongClickListener = onMemeLongClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RecyclerView recyclerView = (RecyclerView) v.getParent();
            onMemeListener.onMemeClick(getAdapterPosition(), recyclerView.getId());
        }

        @Override
        public boolean onLongClick(View v) {
            RecyclerView recyclerView = (RecyclerView) v.getParent();
            onMemeLongClickListener.onMemeLongClick(getAdapterPosition(), recyclerView.getId());
            return true;
        }
    }
    public interface OnMemeListener {
        void onMemeClick(int position, int parentPosition);
    }
    public interface OnMemeLongClickListener {
        void onMemeLongClick(int position, int parentPosition);
    }
}

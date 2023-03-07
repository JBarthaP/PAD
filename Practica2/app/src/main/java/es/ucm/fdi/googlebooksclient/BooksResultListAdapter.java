package es.ucm.fdi.googlebooksclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BooksResultListAdapter extends RecyclerView.Adapter<BooksResultListAdapter.BookViewHolder> {

    private ArrayList<BookInfo> mBooksData;

    private LayoutInflater mInflater;

    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView bookItemView;

        private BooksResultListAdapter mAdapter;

        public BookViewHolder(View itemView, BooksResultListAdapter adapter) {

            super(itemView);
            // Get the layout
            bookItemView = itemView.findViewById(R.id.word);
            // Associate with this adapter
            this.mAdapter = adapter;
            // Add click listener, if desired
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public BooksResultListAdapter(Context context, ArrayList<BookInfo> mBooksData) {
        this.mBooksData = mBooksData;
    }

    @NonNull
    @Override
    public BooksResultListAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create view from layout
        View mItemView = mInflater.inflate(R.layout.element_recyclerview, parent, false);
        return new BookViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksResultListAdapter.BookViewHolder holder, int position) {
        // Retrieve the data for that position
        String mCurrent = mBooksData.get(position).toString();
        // Add the data to the view
        holder.bookItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mBooksData.size();
    }

    public void setBooksData(List<BookInfo> data) {
        mBooksData = new ArrayList<BookInfo>(data);
    }
}

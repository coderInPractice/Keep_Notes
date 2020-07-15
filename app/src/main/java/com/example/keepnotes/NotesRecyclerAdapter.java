package com.example.keepnotes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keepnotes.models.NotesDbHelper;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    Context mContext;
    Cursor mCursor;

    public NotesRecyclerAdapter(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }

    @Override
    public NotesRecyclerAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.notes_list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotesRecyclerAdapter.ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        final String title= mCursor.getString(mCursor.getColumnIndex(NotesDbHelper.NOTES_TITLE_KEY));
        final String content = mCursor.getString(mCursor.getColumnIndex(NotesDbHelper.NOTES_CONTENT_KEY));

        int id = mCursor.getInt(mCursor.getColumnIndex(NotesDbHelper.NOTES_ID));

        holder.title_tv.setText(title);
        holder.content_tv.setText(content);
        holder.itemView.setTag(id);

        mContext = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update_intent = new Intent(mContext,UpdateActivity.class);
                update_intent.putExtra("id", (int) v.getTag());
                update_intent.putExtra("note_title", title);
                update_intent.putExtra("content",content);

                v.getContext().startActivity(update_intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_tv, content_tv;
        public ViewHolder( View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_tv);
            content_tv = itemView.findViewById(R.id.content_tv);

        }
    }

}

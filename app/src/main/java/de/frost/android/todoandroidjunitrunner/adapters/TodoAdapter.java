package de.frost.android.todoandroidjunitrunner.adapters;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.frost.android.todoandroidjunitrunner.R;
import de.frost.android.todoandroidjunitrunner.model.Todo;
import de.frost.android.todoandroidjunitrunner.model.TodoDbHelper.TodoCursor;

/**
 * Created by david on 20.02.17.
 */

public class TodoAdapter extends CursorAdapter {

    public TodoAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);

        TodoHolder viewHolder = new TodoHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TodoHolder viewHolder = (TodoHolder) view.getTag();

        TodoCursor todoCursor = new TodoCursor(cursor);
        Todo todo = todoCursor.getTodo();

        viewHolder.description.setText(todo.getDescription());

        if (!TextUtils.isEmpty(todo.getImage())) {
            Picasso.with(context)
                    .load(todo.getImage())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(viewHolder.image);
        } else {
            viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        }

    }

    static class TodoHolder {
        TextView description;
        ImageView image;

        public TodoHolder(View view) {
            description = (TextView) view.findViewById(R.id.description);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}

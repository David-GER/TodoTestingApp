package de.frost.android.todoandroidjunitrunner.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.frost.android.todoandroidjunitrunner.R;
import de.frost.android.todoandroidjunitrunner.model.Todo;

/**
 * Created by david on 20.02.17.
 */

public class TodoAdapter extends ArrayAdapter<Todo> {
    public TodoAdapter(@NonNull Context context) {
        super(context, R.layout.item_todo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TodoHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.item_todo, parent, false);

            holder = new TodoHolder();
            holder.description = (TextView)row.findViewById(R.id.description);

            row.setTag(holder);
        }
        else {
            holder = (TodoHolder) row.getTag();
        }

        Todo todo = getItem(position);
        holder.description.setText(todo.getDescription());

        return row;
    }

    static class TodoHolder {
        TextView description;
    }
}

package com.st00.afir.mwk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zakaria_afir on 25/07/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mImageRessourceId;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int backgroundColor) {
        super(context, 0, objects);
        this.mImageRessourceId = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.englishTextView);

        // set this text on the english TextView
        englishTextView.setText(currentWord.getEnglishTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokTextView);

        miwokTextView.setText(currentWord.getMiwokTranslation());

        ImageView miwokImageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            miwokImageView.setImageResource(currentWord.getImage());
            miwokImageView.setVisibility(View.VISIBLE);
        } else {
            miwokImageView.setVisibility(View.GONE);
        }

        View textContainer = (View) listItemView.findViewById(R.id.textContainer);
        textContainer.setBackgroundColor(ContextCompat.getColor(getContext(), mImageRessourceId));

        // Return the whole list item layout (containing 2 TextViews )
        // so that it can be shown in the ListView
        return listItemView;
    }
}

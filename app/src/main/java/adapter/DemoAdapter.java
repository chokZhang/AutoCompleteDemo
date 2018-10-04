package adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.boyzhang.autocompletedemo.R;
import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompleteCallback;

import java.util.ArrayList;
import java.util.List;

import auto_complete.SimpleAutocompleteCallback;
import auto_complete.SimplePolicy;
import auto_complete.SimpleRecyclerViewPresenter;

/**
 * Created by boyzhang on 2018/10/3.
 */

public class DemoAdapter extends RecyclerView.Adapter <DemoAdapter.DemoViewHlder>{

    List<String> items;
    public Context context;

    private static final String TAG = "PopupItemRecyclerViewAd";

    public DemoAdapter(Context context){
        items = new ArrayList<>();
        this.context = context;
    }

    /*public static PopupItemRecyclerViewAdapter getPopupItemRecyclerViewAdapter(){
        if(instance == null){
            instance = new PopupItemRecyclerViewAdapter();
        }
        return instance;
    }*/

    public void setItemList(List<String> itemList){

        this.items.clear();
        this.items.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addItem(String content){
        items.add(content);
        Log.d(TAG, "addItem: content is " + content);
        notifyDataSetChanged();
    }

    public void clearItems(){
        items.clear();
    }


    @NonNull
    @Override
    public DemoAdapter.DemoViewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item, parent, false);
        return new DemoAdapter.DemoViewHlder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final DemoAdapter.DemoViewHlder holder, final int position) {
        final String nowContent = items.get(position);
        Log.d(TAG, "onBindViewHolder: content is " + nowContent);
        holder.content.setText(nowContent);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = nowContent + " 123";
                holder.content.setText(str);
                contentChange(position, str);
                Log.d(TAG, "onClick: clicked");

            }
        });
    }

    public void contentChange(int position, String newContent){
        items.set(position, newContent);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class DemoViewHlder extends RecyclerView.ViewHolder{
        public LinearLayout item_body;
        public EditText content;
        public Button button;

        public DemoViewHlder(View item_view, Context context){
            super(item_view);
            item_body = (LinearLayout) item_view;
            content = item_view.findViewById(R.id.main_item_content);

            AutocompleteCallback temp = new SimpleAutocompleteCallback();
            Drawable backgroundDrawable = new ColorDrawable(Color.WHITE);
            float elevation = 6f;
            Autocomplete.on(content)
                    .with(new SimplePolicy())
                    .with(temp)
                    .with(elevation)
                    .with(backgroundDrawable)
                    .with(new SimpleRecyclerViewPresenter(context))
                    .build();

            button = item_view.findViewById(R.id.addText);
        }
    }

}

package auto_complete;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.otaliastudios.autocomplete.RecyclerViewPresenter;

import java.util.ArrayList;
import java.util.List;

import adapter.PopupItemRecyclerViewAdapter;


public class SimpleRecyclerViewPresenter extends RecyclerViewPresenter<String> {

    private static final String TAG = "RecyclerViewPresenter";
    PopupItemRecyclerViewAdapter instance ;

    public SimpleRecyclerViewPresenter(Context context){
        super(context);
    }

    @Override
    protected RecyclerView.Adapter instantiateAdapter() {
        instance = new PopupItemRecyclerViewAdapter();
        instance.setItemClickListener(new PopupItemRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemticClick(View view, String content) {
                dispatchClick(content);
            }
        });
        Log.d(TAG, "instantiateAdapter: get instance");
        return instance;
    }

    @Override
    protected void onQuery(@Nullable CharSequence query) {
        String queryContent = query.toString();
        String[] strs = queryContent.split(" ");
        //查询词的列表
        List<String> queryList = new ArrayList<>();
        for(String str : strs){
            queryList.add(str);
        }
        Log.d(TAG, "onQuery: query's size is " + queryList.size());
        List<String> items = SentenceAutoCompleter.getInstance().executeValueQuery(queryList, false);
        String temp1 = "demo1";
        String temp2 = "demo2";
        String temp3 = "demo3";
        List<String> test = new ArrayList<>();
        test.add(temp1);
        test.add(temp2);
        test.add(temp3);
        instance.setItemList(test);
    }
}

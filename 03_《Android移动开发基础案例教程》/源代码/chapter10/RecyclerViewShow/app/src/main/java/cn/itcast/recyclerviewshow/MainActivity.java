package cn.itcast.recyclerviewshow;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Integer> mDatas;
    private HomeAdapter mAdapter;
    private int[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
    }
    protected void initData() {
        mDatas = new ArrayList<Integer>();
        for (int i = 1; i < 11; i++) {
            mDatas.add(i);
        }
        img = new int[]{
                R.drawable.iv1, R.drawable.iv2,
                R.drawable.iv3, R.drawable.iv4, R.drawable.iv5,
                R.drawable.iv6, R.drawable.iv7, R.drawable.iv8,
                R.drawable.iv9, R.drawable.iv10
        };
    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText("这是第"+mDatas.get(position).toString()+"个精灵");
            holder.iv.setImageResource(img[position]);
        }
        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView iv;
            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
                iv = (ImageView) view.findViewById(R.id.iv_num);
            }
        }
    }
}

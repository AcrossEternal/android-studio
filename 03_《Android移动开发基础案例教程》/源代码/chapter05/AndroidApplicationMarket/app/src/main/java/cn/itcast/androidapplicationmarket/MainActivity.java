package cn.itcast.androidapplicationmarket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    //需要适配的数据
    private String[] names = {"京东商城", "QQ", "QQ斗地主", "新浪微博", "天猫",
            "UC浏览器", "微信"};
    //图片集合
    private int[] icons = {R.drawable.jd, R.drawable.qq, R.drawable.dz,
            R.drawable.xl, R.drawable.tm, R.drawable.uc,R.drawable.wx};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ListView控件
        mListView = (ListView) findViewById(R.id.lv);
        //创建一个Adapter的实例
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        //设置Adapter
        mListView.setAdapter(mAdapter);
    }
    class MyBaseAdapter extends BaseAdapter {
        //得到item的总数
        @Override
        public int getCount() {
            //返回ListView Item条目的总数
            return names.length;
        }
        //得到Item代表的对象
        @Override
        public Object getItem(int position) {
            //返回ListView Item条目代表的对象
            return names[position];
        }
        //得到Item的id
        @Override
        public long getItemId(int position) {
            //返回ListView Item的id
            return position;
        }
        //得到Item的View视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.list_item,parent,false);
                holder = new ViewHolder();
                holder.mTextView = (TextView)convertView.findViewById
                        (R.id. item_tv);
                holder.imageView=(ImageView) convertView.findViewById(R.id.item_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTextView.setText(names[position]);
            holder.imageView.setBackgroundResource(icons[position]);
            return convertView;
        }
        class ViewHolder {
            TextView mTextView;
            ImageView imageView;
        }


    }
}

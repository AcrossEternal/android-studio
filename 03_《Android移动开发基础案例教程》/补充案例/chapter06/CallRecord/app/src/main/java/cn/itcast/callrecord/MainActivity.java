package cn.itcast.callrecord;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.callrecord.bean.Phone;
import cn.itcast.callrecord.db.PhoneDao;


@SuppressLint({"NewApi", "ResourceAsColor"})
public class MainActivity extends Activity implements OnClickListener {
    private ListView listView;
    private PhoneDao dao;
    private MyAdapter adapter;
    private Phone phoneNumber;
    private List<Phone> dialed, received, missed, dataList;
    private TextView tv_receivedCall, tv_missedCall, tv_dialedCalls;
    private RelativeLayout rl_receivedCall, rl_missedCall, rl_dialedCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl_receivedCall = (RelativeLayout) findViewById(R.id.rl_receivedCall);
        rl_missedCall = (RelativeLayout) findViewById(R.id.rl_missedCall);
        rl_dialedCalls = (RelativeLayout) findViewById(R.id.rl_dialedCalls);
        tv_receivedCall = (TextView) findViewById(R.id.tv_receivedCall);
        tv_missedCall = (TextView) findViewById(R.id.tv_missedCall);
        tv_dialedCalls = (TextView) findViewById(R.id.tv_dialedCalls);

        rl_receivedCall.setOnClickListener(this);
        rl_missedCall.setOnClickListener(this);
        rl_dialedCalls.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);

        dao = new PhoneDao(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataList = dao.queryAll();
        dialed = new ArrayList<Phone>();
        received = new ArrayList<Phone>();
        missed = new ArrayList<Phone>();
        if (dataList != null) {
            for (Phone p : dataList) {
                String classify = p.getClassify();
                if ("received".equals(classify)) {
                    received.add(new Phone(p.getPhone(), p.getDate()));
                } else if ("dialed".equals(classify)) {
                    dialed.add(new Phone(p.getPhone(), p.getDate()));
                } else if ("missed".equals(classify)) {
                    missed.add(new Phone(p.getPhone(), p.getDate()));
                }
            }
        }
        adapter = new MyAdapter(dialed);
        listView.setAdapter(adapter);
        setTextColor(R.color.gray, R.color.gray, R.color.white);
    }

    private class MyAdapter extends BaseAdapter {
        private List<Phone> list;

        public MyAdapter(List<Phone> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            phoneNumber = list.get(position);
            View item = convertView != null ? convertView : View.inflate(
                    MainActivity.this, R.layout.listview_item, null);
            TextView tv_number = (TextView) item.findViewById(R.id.tv_number);
            TextView tv_date = (TextView) item.findViewById(R.id.tv_date);
            tv_number.setText("电话号码:	" + phoneNumber.getPhone());
            tv_date.setText(phoneNumber.getDate());
            return item;
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_dialedCalls:
                adapter = new MyAdapter(dialed);
                listView.setAdapter(adapter);
                setTextColor(R.color.gray, R.color.gray, R.color.white);
                break;
            case R.id.rl_receivedCall:
                adapter = new MyAdapter(received);
                listView.setAdapter(adapter);
                setTextColor(R.color.white, R.color.gray, R.color.gray);
                break;
            case R.id.rl_missedCall:
                adapter = new MyAdapter(missed);
                listView.setAdapter(adapter);
                setTextColor(R.color.gray, R.color.white, R.color.gray);
                break;
        }
    }
    private void setTextColor(int one, int two, int three) {
        tv_receivedCall.setTextColor(getResources().getColor(one));
        tv_missedCall.setTextColor(getResources().getColor(two));
        tv_dialedCalls.setTextColor(getResources().getColor(three));
    }

}


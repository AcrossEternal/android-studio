package userregist.homework3.wdj.userregist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener{
    private ItemInfo itemInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        itemInfo =new ItemInfo("金剑",100,20,20);
        findViewById(R.id.rl).setOnClickListener(this);
        TextView mLifeTV=(TextView) findViewById(R.id.tv_life);
        TextView mNameTV=(TextView)findViewById(R.id.tv_name);
        TextView mSpeedTV=(TextView)findViewById(R.id.tv_speed);
        TextView mAttack=(TextView)findViewById(R.id.tv_attack);
        mLifeTV.setText("生命值+"+itemInfo.getLife());
        mNameTV.setText(itemInfo.getName()+"");
        mSpeedTV.setText("敏捷度+"+itemInfo.getSpeed());
        mAttack.setText("攻击力+"+itemInfo.getAttack());
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl:
            Intent intent=new Intent();
            intent.putExtra("equipment",itemInfo);
            setResult(1,intent);
            finish();
            break;
        }
    }
}

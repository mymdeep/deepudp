package deep.com.udpdeep;

import java.net.SocketException;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import deep.com.deepudp.DeepUdp;
import deep.com.deepudp.interfaces.UDPCallback;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText ed;
    private DeepUdp deepUdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.receive);
        ed = (EditText)findViewById(R.id.ed);
        try {
            deepUdp =  DeepUdp.getInstance().init("30.30.140.34",8222, 100);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        findViewById(R.id.r_begin).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            deepUdp.beginReceive(new UDPCallback() {
                @Override
                public void callback(String info) {
                    String header = MainActivity.this.getResources().getString(R.string.receive);
                    tv.setText(header+info);
                }
            });
            }
        });
        findViewById(R.id.r_end).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                deepUdp.end();
            }
        });
        findViewById(R.id.s_begin).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(ed.getText().toString())){
                    deepUdp.send(ed.getText().toString());
                }else {
                    Toast.makeText(MainActivity.this,"请输入发送内容",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}

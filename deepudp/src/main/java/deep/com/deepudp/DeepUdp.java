package deep.com.deepudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import deep.com.deepudp.interfaces.UDPCallback;
import deep.com.deepudp.log.Logger;

/**
 * Created by wangfei on 2018/1/3.
 */

public class DeepUdp {
    private  DatagramSocket datagramSocket;
    private  DatagramPacket datagramPacket;
    private  Handler uiHandler;

    private int size;
    private boolean isBegin = true;
    private String ip;
    private int port ;
    private boolean isInit = false;
    private static class LazyHolder {
        private static final DeepUdp INSTANCE = new DeepUdp();
    }
    private DeepUdp(){

    }
    public static final DeepUdp getInstance() {
        return LazyHolder.INSTANCE;
    }
    public DeepUdp init(String ip,int port,int size) throws SocketException {
        if (datagramSocket == null){
            datagramSocket = new DatagramSocket(port);
            datagramSocket.setBroadcast(true);
            datagramSocket.setReuseAddress(true);
        }


        this.port = port;
        this.ip = ip;
        this.size = size;
        if (uiHandler == null) {
            uiHandler = new Handler(Looper.getMainLooper());
        }
        return this;
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
    public void close() {
        isBegin = false;
        datagramSocket.close();
        datagramSocket = null;
    }
    public void beginReceive(final UDPCallback callback){

        isBegin = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isBegin){
                    byte[] message = new byte[100];
                    datagramPacket = new DatagramPacket(message,
                        message.length);
                    try {
                        datagramSocket.receive(datagramPacket);
                        final String info=new String(datagramPacket.getData());
                        if (!TextUtils.isEmpty(info)) {
                            uiHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.callback(info);
                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void end(){
        isBegin = false;
    }
    public void send(final String info){

        new Thread(new Runnable() {
            @Override
            public void run() {


                    try {

                        byte[] message = new byte[100];
                        datagramPacket =  new DatagramPacket(message, message.length,
                            InetAddress.getByName(ip), port);
                        String a =info;
                        datagramPacket.setData(a.getBytes());

                        datagramSocket.send(datagramPacket);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        }).start();
    }

}

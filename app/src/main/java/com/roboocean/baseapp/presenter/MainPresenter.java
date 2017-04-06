package com.roboocean.baseapp.presenter;

import com.hansion.h_socket.common.bean.AddressInfo;
import com.hansion.h_socket.common.bean.SocketMessage;
import com.hansion.h_socket.tcp.HTcpClient;
import com.hansion.h_socket.tcp.conn.TcpClientConfig;
import com.hansion.h_socket.tcp.data.SpecifiedStickPackageUtil;
import com.hansion.h_socket.tcp.data.StickPackageUtil;
import com.hansion.h_socket.tcp.listener.TcpClientListener;
import com.roboocean.baseapp.contract.MainContract;
import com.roboocean.baseapp.ui.MainActivity;
import com.roboocean.baseapp.utils.HLogUtil;

import static com.roboocean.baseapp.Constants.IP;
import static com.roboocean.baseapp.Constants.PORT;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/2/3 11:22
 */
public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.IMainPresenter, TcpClientListener {


    private StickPackageUtil stickHelper;
    private HTcpClient hTcpClient;

    @Override
    public void initTcpClient() {
        hTcpClient = HTcpClient.getInstance(new AddressInfo(IP, PORT));
        hTcpClient.addTcpClientListener(this);

        //粘包处理：添加包头0xAA 、 包尾0xBB
        byte[] headBytes = new byte[1];
        headBytes[0] = (byte) 0xAA;
        byte[] footBytes = new byte[1];
        footBytes[0] = (byte) 0xBB;
        stickHelper = new SpecifiedStickPackageUtil(headBytes, footBytes);
    }

    @Override
    public void connTcpServer() {
        hTcpClient.config(new TcpClientConfig.Builder()
//                .setStickPackageHelper(stickHelper)//粘包
//                    .setIsReconnect(true)   //自动重连
                .create());
        hTcpClient.connect();
    }

    @Override
    public void onConnected(HTcpClient hTcpClient) {
        hTcpClient.sendMsg("连接成功");

        HLogUtil.e("Tcp连接成功");
    }

    @Override
    public void onSended(HTcpClient hTcpClient, SocketMessage socketMessage) {
        HLogUtil.e("Tcp发送数据---十六进制形式："+socketMessage.getSourceDataHexString());
        HLogUtil.e("Tcp发送数据---字符串形式："+socketMessage.getSourceDataString());
    }

    @Override
    public void onDisconnected(HTcpClient hTcpClient, String s, Exception e) {
        HLogUtil.e("Tcp断开连接或连接失败");
    }

    @Override
    public void onReceive(HTcpClient hTcpClient, SocketMessage socketMessage) {
        HLogUtil.e("Tcp收到数据---十六进制形式："+socketMessage.getSourceDataHexString());
        HLogUtil.e("Tcp收到数据---字符串形式："+socketMessage.getSourceDataString());
    }
}

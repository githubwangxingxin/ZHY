package utils.db;

import com.eprobiti.trs.TRSConnection;
import com.eprobiti.trs.TRSException;
import com.eprobiti.trs.TRSResultSet;

import java.awt.*;
import java.util.Date;

/**
 * ���ݿ�����
 *
 * @author Hermes
 */
public class DBConnector {
    //todo �ڵ���ǰ���� ip ���� ���ݿ����� ���ʽ ���·��(desktop��countFile��detailFile��logPath)
//    private final static String DB_URL = "localhost";	  //TRSServer���ݿ�URL
    //private final static String DB_URL = "10.72.77.90";	  //TRSServer���ݿ�URL
    //private final static String DB_URL = "10.72.76.187";	  //TRSServer���ݿ�URL
//    private final static String DB_URL = "10.72.76.73";      //TRSServer���ݿ�URL
    	private final static String DB_URL = "10.72.76.89";	  //TRSServer���ݿ�URL
    private final static String DB_PORT = "8888";               //TRSServer�˿�
    private final static String DB_USERNAME = "system";            //TRSServer�û���
    private final static String DB_PASSWORD = "manager";         //TRSServer����
    public final static String serverTable = "AS$3$E$1";        //TRSServer���ݿ���
    public final static String groupType = "�ⲿ��վ";            //���ݿ�����  ��Ϊ  �ⲿ��վ���ڲ���վ��΢����΢��
    public final static String desktop = "C:\\Users\\11633\\Desktop\\";
//        public final static String biaodashi = desktop + "��Ϣ����\\0507�������ʽ.txt";        //�������ʽ
    public final static String biaodashi = desktop + "��Ϣ����\\���ʽ����0508.txt";        //�������ʽ
    public final static String countFile = desktop + "��Ϣ����\\������Ϣ\\" + groupType + "_������Ϣͳ��_" + (new Date().getMonth() + 1) + "��" + (new Date().getDate()) + "��" + (new Date()).getHours() + "ʱ_.xlsx";        //���������Ϣͳ��
    public final static String detailFile = desktop + "��Ϣ����\\������Ϣ\\" + groupType + "_������Ϣ��ϸ_" + (new Date().getMonth() + 1) + "��" + (new Date().getDate()) + "��" + (new Date()).getHours() + "ʱ_.xlsx";        //���������Ϣ��ϸ
    public final static String logPath = desktop + "��Ϣ����\\log.txt";        //���������Ϣ��ϸ

    /**
     * ��ȡ���ݿ�����
     *
     * @return
     */
    public TRSConnection getDBConnection() {
        TRSConnection conn = null;
        try {
            conn = new TRSConnection();
            conn.connect(DB_URL, DB_PORT, DB_USERNAME, DB_PASSWORD, serverTable);
            //int iBackupNum = conn.backupDatabases("demo3", "c:\\bak\\demo3.bak");
        } catch (TRSException e) {
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("ErrorString: " + e.getErrorString());
        }
        return conn;
    }

    /**
     * �ر����ݿ�����
     *
     * @param
     */
    public void closeConnection(TRSConnection conn) {
        try {
            if (conn != null) {
                if (!conn.isClosed()) {   //�жϵ�ǰ�������Ӷ������û�б��رվ͵��ùرշ���
                    conn.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ����
     *
     * @param args
     */
    public static void main(String args[]) {
        TRSConnection conn = null;
        TRSResultSet rs = null;
        DBConnector db = new DBConnector();
        try {
            conn = new TRSConnection();
            conn = db.getDBConnection();
            rs = new TRSResultSet();
            rs = conn.executeSelect("Demo3", "����=���Ʒ�", false);
            System.out.println("�������� " + rs.getRecordCount() + "����¼");
            for (int i = 0; i < 100 && i < rs.getRecordCount(); i++) {
                rs.moveTo(0, i);
                System.out.println("��" + i + "����¼");
                System.out.println(rs.getString("����"));
                System.out.println(rs.getString("���"));
                System.out.println(rs.getString("����"));
                System.out.println(rs.getString("����", "red"));
            }
        } catch (TRSException e) {
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("ErrorString: " + e.getErrorString());
        } finally {
            if (rs != null) rs.close();
            rs = null;

            if (conn != null) conn.close();
            conn = null;
        }
    }
}

package concurrent;

import com.nick.forum.dao.message.MessageDAOImpl;

import java.sql.SQLException;

public class NewThread implements Runnable{


    private int name;

    public NewThread(int name) {
        this.name = name;
    }

    @Override
    public void run() {
        MessageDAOImpl messageDAO = MessageDAOImpl.getInstance();

        System.out.println("Start " + name);
        try {
            System.out.println("size "+ name+ " " + messageDAO.getAllMessages().size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("End " + Thread.currentThread().getName());
        synchronized (this){
            notify();
        }
    }
}

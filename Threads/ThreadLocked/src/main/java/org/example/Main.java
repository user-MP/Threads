package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        FileSender fileSender=new FileSender();

        BookSender bookSender=new BookSender();

        fileSender.bookSender=bookSender;
        bookSender.fileSender=fileSender;

        FirstThread firstThread=new FirstThread();
        SecondThread secondThread=new SecondThread();

        firstThread.firstResource=fileSender;
        secondThread.secondResource=bookSender;

        firstThread.start();
        secondThread.start();

    }
}


class FirstThread extends Thread {
    FileSender firstResource;

    public void run() {

        synchronized (firstResource) {
            System.out.println(firstResource.sendData());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (firstResource.bookSender) {
                System.out.println(firstResource.bookSender.sendData());
            }
        }

    }
}

    class SecondThread extends Thread {

        BookSender secondResource;

        public void run() {

            System.out.println(secondResource.sendData());

            synchronized (secondResource) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (secondResource.fileSender) {
                    System.out.println(secondResource.fileSender.sendData());
                }
            }
        }

    }


    class FileSender {

        BookSender bookSender;

        public synchronized String sendData() {
            return bookSender.getData();
        }

        public synchronized String getData() {

            return "Getting file File.docx";
        }


    }


    class BookSender {

        FileSender fileSender;

        public synchronized String sendData() {
            return fileSender.getData();
        }


        public synchronized String getData() {

            return "Getting some book...";
        }


}

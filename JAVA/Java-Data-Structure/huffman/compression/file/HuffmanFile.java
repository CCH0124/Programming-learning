package huffman.compression.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;

import huffman.compression.HuffmanCode;

public class HuffmanFile {
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            byte[] huffmanByte = HuffmanCode.huffmanZip(b);
            fileOutputStream = new FileOutputStream(dstFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(huffmanByte);
            objectOutputStream.writeObject(HuffmanCode.huffmanCodes); // 解壓用
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void unzip(String zipFile, String dstFile){
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);
            byte[] huffmanBytes = (byte[])objectInputStream.readObject();
            Map<Byte,String> huffmanCodes =  (Map<Byte,String>)objectInputStream.readObject();

            byte[] bytes = HuffmanCode.decode(huffmanCodes, huffmanBytes);

            outputStream = new FileOutputStream(dstFile);
            outputStream.write(bytes);
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        String srcFile = "C:\\Users\\user\\Desktop\\Java-Data-Structure\\huffman\\compression\\file\\yuri.jpg";
        String dstFile = "C:\\Users\\user\\Desktop\\Java-Data-Structure\\huffman\\compression\\file\\yuri.zip";
        // zipFile(srcFile, dstFile);

        // System.out.println("壓縮成功");
        String unzip_File = "C:\\Users\\user\\Desktop\\Java-Data-Structure\\huffman\\compression\\file\\yuri_unzip.jpg";
        unzip(dstFile, unzip_File);
        System.out.println("解壓成功");
    }
}
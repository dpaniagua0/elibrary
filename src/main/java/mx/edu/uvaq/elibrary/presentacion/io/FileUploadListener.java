/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.io;

import org.apache.commons.fileupload.ProgressListener;

/**
 *
 * @author arcesino
 */
public class FileUploadListener implements ProgressListener
{
  private FileUploadStats fileUploadStats = new FileUploadStats();

  public void update(long pBytesRead, long pContentLength, int pItems) {
    fileUploadStats.setTotalSize(pContentLength);
    fileUploadStats.setBytesRead(pBytesRead);
  }
  
  public FileUploadStats getFileUploadStats()
  {
    return fileUploadStats;
  }

  public static class FileUploadStats
  {
    private long totalSize = 0;
    private long bytesRead = 0;
    private long startTime = System.currentTimeMillis();
    private String currentStatus = "none";

    public long getTotalSize()
    {
      return totalSize;
    }

    public void setTotalSize(long totalSize)
    {
      this.totalSize = totalSize;
    }

    public long getBytesRead()
    {
      return bytesRead;
    }

    public long getElapsedTimeInSeconds()
    {
      return (System.currentTimeMillis() - startTime) / 1000;
    }

    public String getCurrentStatus()
    {
      return currentStatus;
    }

    public void setCurrentStatus(String currentStatus)
    {
      this.currentStatus = currentStatus;
    }

    public void setBytesRead(long bytesRead)
    {
      this.bytesRead = bytesRead;
    }

    public void incrementBytesRead(int byteCount)
    {
      this.bytesRead += byteCount;
    }
  }
}